package com.stgutah.playground.JSch;

import com.jcraft.jsch.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.Provider;

/**
 * Simple example of JSch.
 * <p/>
 * User: dqromney
 * Date: Oct 14, 2010
 * Time: 5:22:37 PM
 */
public class Simple {

    private MyLogger LOGGER;

    private JSch jsch;
    private String host;
    private int port;
    private String userName;
    private String path;
    private final static String KNOWN_HOSTS = "/Users/dqromney/.ssh/known_hosts";
    private final static String IDENTITY = "/Users/dqromney/.ssh/id_dsa";

    private void init() {

        LOGGER = new MyLogger();
        JSch.setLogger(LOGGER);
        jsch = new JSch();
        host = "demo.commercialpreservation.com";
        port = 22;
        userName = "lps";
        path = "/opt/FAS/LPS";
    }

    private void execute() {
        try {
            Session session = jsch.getSession(userName, host, port);
            jsch.addIdentity(IDENTITY, "password");
//            jsch.setKnownHosts(KNOWN_HOSTS);
//            HostKeyRepository hkrepo = jsch.getHostKeyRepository();
//            HostKey[] dsaHostKeyArray = hkrepo.getHostKey("demo.commercialpreservation.com", "ssh-dss");
//            HostKey[] rsaHostKeyArray = hkrepo.getHostKey("demo.commercialpreservation.com", "ssh-rsa");
//            HostKey hostKey;
//            if (dsaHostKeyArray.length > 0) {
//                hostKey = dsaHostKeyArray[0];
//            }
//            else {
//                hostKey = rsaHostKeyArray[0];
//            }

//            LOGGER.log(Logger.DEBUG, String.format("hostKey host[%2$s] type=[%1$s]", hostKey.getType(), hostKey.getHost()));
//            removeSunPKCS11Provider();

            // username and password will be given via UserInfo interface.
            UserInfo ui = new MyUserInfo();
            session.setUserInfo(ui);
//            session.getUserInfo().promptPassword("Enter your password");
//            session.getUserInfo().promptPassphrase("Enter your pass phrase");


            // default -> com.jcraft.jsch.UserAuthPublicKey
            // sun.security.provider.DSAPublicKeyImpl;
            LOGGER.log(Logger.DEBUG, String.format("-- BEFORE userauth.publickey=[" + session.getConfig("userauth.publickey") + "]"));
            //session.setConfig("userauth.publickey", "sun.security.provider.DSAPublicKeyImpl");
            LOGGER.log(Logger.DEBUG, String.format("-- AFTER userauth.publickey=[" + session.getConfig("userauth.publickey") + "]"));

            LOGGER.log(Logger.DEBUG, String.format("session.getUserInfo().toString()=" + session.getUserInfo().toString()));
            LOGGER.log(Logger.DEBUG, String.format("session.getHostKey()=[" + session.getHostKey() + "]"));

            LOGGER.log(Logger.DEBUG, String.format("session.getClientVersion()=[" + session.getClientVersion() + "]"));

            LOGGER.log(Logger.DEBUG, String.format("-- BEFORE session.getConfig(\"server_host_key\")=[" + session.getConfig("server_host_key") + "]"));
            session.setConfig("server_host_key", "ssh-dss,ssh-rsa");
            LOGGER.log(Logger.DEBUG, String.format("-- AFTER session.getConfig(\"server_host_key\")=[" + session.getConfig("server_host_key") + "]"));

            // If this flag is set to ``yes'', ssh(1) will never automatically add host keys to the
            // ~/.ssh/known_hosts file, and refuses to connect to hosts whose host key has changed.
            LOGGER.log(Logger.DEBUG, String.format("-- BEFORE StrictHostKeyChecking=[" + session.getConfig("StrictHostKeyChecking") + "]"));
            session.setConfig("StrictHostKeyChecking", "ask");
            LOGGER.log(Logger.DEBUG, String.format("-- AFTER StrictHostKeyChecking=[" + session.getConfig("StrictHostKeyChecking") + "]"));

            LOGGER.log(Logger.DEBUG, String.format("-- BEFORE HashKnownHosts=[" + session.getConfig("HashKnownHosts") + "]"));
            session.setConfig("HashKnownHosts", "no");
            LOGGER.log(Logger.DEBUG, String.format("-- AFTER HashKnownHosts=[" + session.getConfig("HashKnownHosts") + "]"));

            session.connect();

            Channel channel = session.openChannel("exec");
            ((ChannelExec) channel).setCommand("ls -al");

            channel.setInputStream(System.in);
            //channel.setInputStream(null);
            channel.setOutputStream(System.out);

            FileOutputStream fos = new FileOutputStream("/tmp/stderr");
            ((ChannelExec) channel).setErrStream(fos);
            //((ChannelExec) channel).setErrStream(System.err);

            InputStream in = channel.getInputStream();

            channel.connect();

            byte[] tmp = new byte[1024];
            while (true) {
                while (in.available() > 0) {
                    int i = in.read(tmp, 0, 1024);
                    if (i < 0) break;
                    System.out.print(new String(tmp, 0, i));
                }
                if (channel.isClosed()) {
                    System.out.println("exit-status: " + channel.getExitStatus());
                    break;
                }
                try {
                    Thread.sleep(1000);
                } catch (Exception ee) {
                }
            }
            channel.disconnect();
            session.disconnect();

        } catch (JSchException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    private void removeSunPKCS11Provider() {
        Provider[] providerArray = java.security.Security.getProviders();
        LOGGER.log(Logger.DEBUG, String.format("providerArray.length=[" + providerArray.length + "]"));
        for (Provider provider : providerArray) {
            LOGGER.log(Logger.DEBUG, String.format("Provider [" + provider.getName() + "]"));
            if (provider.getName().compareToIgnoreCase("SunPKCS11-Darwin") == 0) {
                java.security.Security.removeProvider(provider.getName());
                LOGGER.log(Logger.DEBUG, String.format("Removed [" + provider.getName() + "]"));
            }
        }
    }

    // Driver

    public static void main(String[] args) throws IOException {
        Simple main = new Simple();
        main.init();
        main.execute();
    }

}
