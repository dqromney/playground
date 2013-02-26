package com.stgutah.playground.systemProperties;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;
import java.util.Properties;
import java.util.TreeSet;
import java.util.Map;

/**
 * Shows system properties.  This must be an application. An applet can't get this information.
 * <p/>
 * User: dqromney
 * Date: Aug 30, 2010
 * Time: 3:34:37 PM
 */
public class SysPropertyList
{
    public static void main(String[] args)
    {
        JFrame window = new JFrame("System Properties");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setContentPane(new SysPropListGUI());
        window.pack();
        window.setVisible(true);
    }
}

/**
 * Panel to display the (limited) GUI intereface.
 */
class SysPropListGUI extends JPanel
{
    JTextArea jTextArea = new JTextArea(20, 40);

    /**
     * Constructor sets layout, adds component(s), sets values
     */
    public SysPropListGUI()
    {
        this.setLayout(new BorderLayout());
        this.add(new JScrollPane(jTextArea), BorderLayout.CENTER);

        //... Add property list data to text area.
//        for(Map.Entry entry : System.getProperties().entrySet())
//        {
//            jTextArea.append(String.format("%2$s = [%3$s]%1$s",
//                    System.getProperty("line.separator"),
//                    entry.getKey(),
//                    entry.getValue()));
//        }

        //... Add property list data to text area.
        Properties pr = System.getProperties();
        TreeSet propKeys = new TreeSet(pr.keySet());  // TreeSet sorts keys
        for(Iterator it = propKeys.iterator(); it.hasNext();)
        {
            String key = (String) it.next();
            jTextArea.append("" + key + "=" + pr.get(key) + "\n");
        }
    }
}

