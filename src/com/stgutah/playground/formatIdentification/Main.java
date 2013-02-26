package com.stgutah.playground.formatIdentification;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;
import java.util.ArrayList;

/**
 * Identifiers file types / mime types.
 *
 * User: dqromney
 * Date: May 24, 2010
 * Time: 11:50:07 AM
 */
public class Main
{
    List<File> inputFileList = null;

    public static void main (String[] args) throws IOException {
        Main main = new Main();
        main.init();
        main.execute();
    }

    private void init() {
        inputFileList = new ArrayList<File>(0);
    }

    private void execute() throws IOException
    {
        inputFileList.add(new File("/Users/dqromney/stgdev/playground/src/com/stgutah/playground/formatIdentification/TAGIH001.SCN"));
        inputFileList.add(new File("/Users/dqromney/stgdev/playground/src/com/stgutah/playground/formatIdentification/TAGKW004.SCN"));
        inputFileList.add(new File("/Users/dqromney/stgdev/playground/src/com/stgutah/playground/formatIdentification/TAGXZ001.SCN"));
        inputFileList.add(new File("/Users/dqromney/stgdev/playground/src/com/stgutah/playground/formatIdentification/DSCF0151.JPG"));
        inputFileList.add(new File("/Users/dqromney/stgdev/playground/src/com/stgutah/playground/formatIdentification/About Stacks.pdf"));
        inputFileList.add(new File("/Users/dqromney/stgdev/playground/src/com/stgutah/playground/formatIdentification/formats.txt"));

        for(File inputFile : inputFileList) {
            System.out.println("inputFile=" + inputFile == null ? "null" : inputFile.getCanonicalPath());
            FormatDescription formatDescription = FormatIdentification.identify(inputFile);
            String msg = MessageFormat.format("formatDescription=[{0}]", formatDescription == null ? "null" : formatDescription.toString());
            System.out.println(msg);
        }
    }
}

/**
OUTPUT:
/Users/dqromney/stgdev/playground/src/com/stgutah/playground/formatIdentification/TAGIH001.SCN
inputFile: /Users/dqromney/stgdev/playground/src/com/stgutah/playground/formatIdentification/formats.txt
formatDescription=[image	TIFF	Tag Image File Format (TIFF)	image/tiff,	tif,0		]
/Users/dqromney/stgdev/playground/src/com/stgutah/playground/formatIdentification/TAGKW004.SCN
formatDescription=[image	TIFF	Tag Image File Format (TIFF)	image/tiff,	tif,0		]
/Users/dqromney/stgdev/playground/src/com/stgutah/playground/formatIdentification/TAGXZ001.SCN
formatDescription=[image	TIFF	Tag Image File Format (TIFF)	image/tiff,	tif,0		]
/Users/dqromney/stgdev/playground/src/com/stgutah/playground/formatIdentification/DSCF0151.JPG
formatDescription=[image	JPEG	Joint Photographic Experts Group (JPG)	image/jpeg,	jpg,0		]
/Users/dqromney/stgdev/playground/src/com/stgutah/playground/formatIdentification/About Stacks.pdf
formatDescription=[doc	PDF	Portable Document Format (PDF)	application/pdf,	pdf,0		]
/Users/dqromney/stgdev/playground/src/com/stgutah/playground/formatIdentification/formats.txt
formatDescription=[null]
 */
