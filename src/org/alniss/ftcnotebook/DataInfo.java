package org.alniss.ftcnotebook;

import java.io.File;

public class DataInfo {
    public static final File slackInputFolder
            = new File(System.getProperty("user.dir")
            + File.separator + "data" + File.separator + "slackdata");
    public static final File intermediateFile
            = new File(System.getProperty("user.dir")
            + File.separator + "data" + File.separator + "intermediate.json");
    public static final File slackDebugInputFileA
            = new File(DataInfo.slackInputFolder.getAbsolutePath()
            + File.separator + "2018-12-12.json");
    public static final File slackDebugInputFileB
            = new File(DataInfo.slackInputFolder.getAbsolutePath()
            + File.separator + "foo.json");
}
