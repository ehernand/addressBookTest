package util;

import org.apache.commons.io.FilenameUtils;

public class LocalFileUtil {

    public static String getFileExtension(String filePath) {
        return FilenameUtils.getExtension(filePath);
    }
}
