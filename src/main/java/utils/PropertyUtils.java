package utils;


import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.Properties;

/**
 * properties文件操作类
 * 包括properties文件的读写操作
 * Created by xualvin on 7/7/18.
 */
public class PropertyUtils {
    private final static String PROPERTY_FILE_LOCATION = "src/main/resources/";

    /**
     * 通过properties文件和键值获取value
     *
     * @param fileName properties file name
     * @param key
     * @return
     */
    public static String getValueByKey(String fileName, String key) {
        if (StringUtils.isEmpty(fileName) || StringUtils.isEmpty(key)) {
            return null;
        }
        Properties properties = new Properties();
        InputStream inputStream;
        try {
            inputStream = new BufferedInputStream(new FileInputStream(PROPERTY_FILE_LOCATION + fileName));
            properties.load(inputStream);
            return properties.getProperty(key);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Get all property configurations
     *
     * @param fileName
     * @return
     */
    public static Properties getAll(String fileName) {
        if (StringUtils.isEmpty(fileName)) {
            return null;
        }
        Properties properties = new Properties();
        InputStream inputStream;
        try {
            inputStream = new BufferedInputStream(new FileInputStream(PROPERTY_FILE_LOCATION + fileName));
            properties.load(inputStream);
            return properties;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Add property key/value to properties file
     * @param fileName
     * @param key
     * @param value
     * @param comments
     * @return -1 add failed, 1-success
     */
    public static int addProperty(String fileName, String key, String value, String comments) {
        if (StringUtils.isEmpty(fileName) || StringUtils.isEmpty(key)) {
            return -1;
        }

        Properties properties = new Properties();
        FileOutputStream outputStream;
        try {
            outputStream = new FileOutputStream(PROPERTY_FILE_LOCATION + fileName, true);
            properties.setProperty(key, value);
            properties.store(outputStream, comments);
            return 1;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
