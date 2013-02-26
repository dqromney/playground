package com.stgutah.playground.fileUtils;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * FileUtils (Apache) ~ Move Directory test bed.
 *
 * User: dqromney
 * Date: Mar 24, 2011
 * Time: 12:02:30 PM
 */
public class MoveDirectory {
    Logger log = Logger.getLogger(MoveDirectory.class.getName());
    private static final String DEFAULT_FILE_SEPARATOR = System.getProperty("file.separator");
    private String userDir;
    private String baseDir;
    private String srcDir;
    private String destDir;

    public void execute() throws IOException {
        File moveDir = null;

        System.out.println(String.format("srcDir=[%1$s]", srcDir));
        System.out.println(String.format("destDir=[%1$s]", destDir));

        // Move srcDir to destDir; contains a file
        moveDir = new File(srcDir);
        FileUtils.moveDirectory(moveDir, new File(destDir));
        if(moveDir.exists()) {
            log.severe(String.format("Directory [%1$s] should NOT exist!", moveDir.getPath()));
        }
        if(new File(destDir).exists()) {
            log.info(String.format("Directory moved from [%1$s] to %2$s]", srcDir, destDir));
        }

        // Move destDir to srcDir
        moveDir = new File(destDir);
        FileUtils.moveDirectory(moveDir, new File(srcDir));
        if(moveDir.exists()) {
            log.severe(String.format("Directory [%1$s] should NOT exist!", moveDir.getPath()));
        }
        if(new File(srcDir).exists()) {
            log.info(String.format("Directory moved from [%1$s] to %2$s]", destDir, srcDir));
        }
    }


    public void init() {
        userDir = System.getProperty("user.dir");
        baseDir = String.format("%1$s%2$s%3$s", userDir, DEFAULT_FILE_SEPARATOR, "src/com/stgutah/playground/fileUtils");
        // Assure file separators are being repalced for system specific character
        baseDir = baseDir.replace('/', DEFAULT_FILE_SEPARATOR.charAt(0));

        srcDir = String.format("%1$s%2$s%3$s", baseDir, DEFAULT_FILE_SEPARATOR, "srcDir");
        destDir = String.format("%1$s%2$s%3$s", baseDir, DEFAULT_FILE_SEPARATOR, "destDir");
    }

    /**
     * Main driver.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        MoveDirectory main = new MoveDirectory();
        main.init();
        main.execute();
    }
}
