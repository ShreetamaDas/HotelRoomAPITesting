
package utils;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PayloadBuilder {
    public static Map<String, Object> loadPropertiesAsMap(String resourcePath) {
        try (InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(resourcePath)) {
            Properties p = new Properties();
            p.load(is);
            Map<String, Object> map = new HashMap<>();
            for (String name : p.stringPropertyNames()) {
                map.put(name, p.getProperty(name));
            }
            return map;
        } catch (Exception e) {
            throw new RuntimeException("Failed to read " + resourcePath, e);
        }
    }
}
