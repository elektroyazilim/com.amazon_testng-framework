package amazon.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {



    // fileName can be like that "config.properties"
    public static String getProperty(String fileName, String key) throws IOException {
        String path = "src/test/resources/config/" + fileName;
        Properties prop = new Properties();
        String value = null;
        try {
            FileInputStream fis = new FileInputStream(path);
            prop.load(fis);
            value = prop.getProperty(key);
        } catch (IOException exception) {
            System.out.println("Hata olustu : " + exception);
        }

        return value;

    }


    public static String getProperty(String key) throws IOException{
        Properties prop = new Properties();
        try {
            // src/test/resources/config/config.properties
            FileInputStream fis = new FileInputStream("src/test/resources/config/config.properties");
            prop.load(fis);
        } catch (Exception ex) {
            System.out.println("Error : " + ex);
        }

        String value = prop.getProperty(key);

        return value;

    }

    public static void setProperty(String fileName, String key, String value) throws IOException {

        String path = "src/test/resources/config/" + fileName;
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(path);
        prop.load(fis);

        prop.setProperty(key, value);

        FileOutputStream fos = new FileOutputStream(path);
        prop.store(fos, null);
    }

    public static void setProperty(String key, String value) throws IOException {

        Properties prop = new Properties();
        try {
            FileInputStream fis = new FileInputStream("src/test/resources/config/config.properties"); // src/test/resources/config/config.properties
            prop.load(fis);
            prop.setProperty(key, value);
            FileOutputStream fos = new FileOutputStream("src/test/resources/config/config.properties");
            prop.store(fos, null);
        } catch (Exception ex) {
            System.out.println("Dosya yazma hatasi : " + ex);

        }
    }
}
