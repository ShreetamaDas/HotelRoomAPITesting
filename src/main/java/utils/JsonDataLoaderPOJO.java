package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.nio.file.Paths;
import java.util.List;

public class JsonDataLoaderPOJO {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static <T> List<T> loadJsonArrayAsList(String filePath, TypeReference<List<T>> typeRef) {
        try {
            return mapper.readValue(Paths.get(filePath).toFile(), typeRef);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load JSON array from " + filePath, e);
        }
    }

    // Convenience method for Rooms
    public static List<POJO> loadRooms(String filePath) {
        return loadJsonArrayAsList(filePath, new TypeReference<List<POJO>>() {});
    }
}