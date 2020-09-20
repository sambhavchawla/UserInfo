package Property;

import java.io.IOException;
import java.util.Properties;

/**\
 * Class to read properties listed in properties file
 */
public class PropertyReader {
    public static String getProperty(String key) {
        Properties properties = new Properties();
        try {
            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties"));

        } catch (IOException e) {
        }
        return properties.getProperty(key);
    }
}
