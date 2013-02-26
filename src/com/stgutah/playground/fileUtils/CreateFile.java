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
public class CreateFile {
    Logger log = Logger.getLogger(CreateFile.class.getName());
    private static final String DEFAULT_FILE_SEPARATOR = System.getProperty("file.separator");
    private String userDir;
    private String baseDir;
    private String srcDir;
    private String destDir;

    public void execute() throws IOException {
        File collectionPath = new File(destDir);
        File collectionPathFile = new File(destDir + "/collectionFile.txt");

        System.out.println(String.format("srcDir=[%1$s]", srcDir));
        System.out.println(String.format("destDir=[%1$s]", destDir));

        // Make sure file and directory don't exist prior to test
        deletePathFile(collectionPathFile.getPath());
        // Create a file that does not have an existing directory. ; Does not create directory.
        collectionPath.mkdir();
        collectionPathFile.createNewFile();

        // Make sure file and directory don't exist prior to test
        deletePathFile(collectionPathFile.getPath());
        // Create new non-existing path and file
        if(createNewPathFile(collectionPathFile.getPath())) {
            System.out.println(String.format("Collection Path and File created [%1$s].", collectionPathFile.getPath()));    
        }

        // Make sure file and directory don't exist prior to test
        deletePathFile(collectionPathFile.getPath());
    }

    public boolean createNewPathFile(String pPathFile) throws IOException {
        boolean result = false;
        File path = new File(getPathFromPathFile(pPathFile));
        File pathFile = new File(pPathFile);
        if(!path.exists()) {
            result = path.mkdir();
        }
        if(!pathFile.exists()) {
            result = pathFile.createNewFile();
        }
        return result;
    }

    public void deletePathFile(String pPathFile) throws IOException {
        File path = new File(getPathFromPathFile(pPathFile));
        if(path.exists()) {
            FileUtils.deleteDirectory(path);
        }
    }

    /**
     * Gets the path from a Path and File string.
     *
     * @param pPathFile the path and file, i.e. /home/foo/bar.txt
     * @return the path without the file name, i.e. /home/foo, if no path a './' is returned.
     */
    public String getPathFromPathFile(String pPathFile) {
        return pPathFile == null ? "." + DEFAULT_FILE_SEPARATOR :
                pPathFile.lastIndexOf(DEFAULT_FILE_SEPARATOR) == -1 ? "." + DEFAULT_FILE_SEPARATOR :
                        pPathFile.substring(0, pPathFile.lastIndexOf(DEFAULT_FILE_SEPARATOR));
    }

    /**
     * Gets the file name from a Path and File string; files must have extension.
     *
     * @param pPathFile the path and file, i.e. /home/foo/bar.txt
     * @return the file name without the path, i.e. bar.txt
     */
    public String getFilenameFromPathFile(String pPathFile) {
        return pPathFile == null ? "" :
                pPathFile.lastIndexOf(DEFAULT_FILE_SEPARATOR) == -1 ? "" :
                        pPathFile.lastIndexOf(".") == -1 ? "" :
                                pPathFile.substring(pPathFile.lastIndexOf(DEFAULT_FILE_SEPARATOR) + 1);
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
        CreateFile main = new CreateFile();
        main.init();
        main.execute();
    }
}
