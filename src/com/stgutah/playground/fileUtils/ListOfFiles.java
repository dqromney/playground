package com.stgutah.playground.fileUtils;

import java.util.*;
import java.io.*;

/**
 * Enumeration of a files.
 *
 * User: dqromney
 * Date: Apr 1, 2011
 * Time: 4:54:34 PM
 */
class ListOfFiles implements Enumeration {

    String[] listOfFiles;
    int current = 0;

    ListOfFiles(String[] listOfFiles) {
        this.listOfFiles = listOfFiles;
    }

    public boolean hasMoreElements() {
        if (current < listOfFiles.length)
            return true;
        else
            return false;
    }

    public Object nextElement() {
        InputStream is = null;

        if (!hasMoreElements())
            throw new NoSuchElementException("No more files.");
        else {
            try {
                String nextElement = listOfFiles[current];
                current++;
                is = new FileInputStream(nextElement);
            } catch (FileNotFoundException e) {
                System.out.println("ListOfFiles: " + e);
            }
        }
        return is;
    }
}