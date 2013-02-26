package com.stgutah.playground.imageUtils;

import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.imaging.jpeg.JpegProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.MetadataReader;
import com.drew.metadata.Tag;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Logger;

/**
 * Load an Image file.
 *
 * User: dqromney
 * Date: Jun 20, 2011
 * Time: 5:26:11 PM
 */
public class LoadImage {

    private static final java.util.logging.Logger LOGGER = Logger.getLogger(LoadImage.class.toString());

    private String imageFile = "pruvan.JPG";
    private String defaultDirectory;
    private String imagePathFile;
    private Metadata metadata;

    public static void main(String[] args) throws JpegProcessingException {
        LoadImage main = new LoadImage();
        main.init();
        main.execute();
    }

    public void init() throws JpegProcessingException {
        LOGGER.info("init() :: Enter");

        defaultDirectory = System.getProperty("user.dir");
        imagePathFile = defaultDirectory + "/src/com/stgutah/playground/imageUtils/" + imageFile;
        metadata = JpegMetadataReader.readMetadata(new File(imagePathFile));

        LOGGER.info("init() :: Exit");
    }

    public void execute() {
        LOGGER.info("execute() :: Enter");
        printImageTags(1, metadata);
        LOGGER.info("execute() :: Exit");
    }

    private void printImageTags(int approachCount, Metadata metadata)
    {
        System.out.println();
        System.out.println("*** APPROACH " + approachCount + " ***");
        System.out.println();
        // iterate over the exif data and print to System.out
        Iterator directories = metadata.getDirectoryIterator();
        while (directories.hasNext()) {
            Directory directory = (Directory)directories.next();
            Iterator tags = directory.getTagIterator();
            while (tags.hasNext()) {
                Tag tag = (Tag)tags.next();
                System.out.println(tag);
            }
            if (directory.hasErrors()) {
                Iterator errors = directory.getErrors();
                while (errors.hasNext()) {
                    System.out.println("ERROR: " + errors.next());
                }
            }
        }
    }

}
