// src/test/java/starter/purchase/utils/PurchaseDataReader.java
package starter.purchase.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class PurchaseDataReader {

    private static final String FILE_PATH = "src/test/resources/test-data/purchase-data.json";

    public static JsonNode getUserData(String userKey) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        InputStream inputStream = PurchaseDataReader.class.getClassLoader()
                .getResourceAsStream("test-data/purchase-data.json");
        return mapper.readTree(inputStream).get(userKey);
    }

    public static List<Map<String, String>> getCheckoutList(String userKey) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        InputStream inputStream = PurchaseDataReader.class.getClassLoader()
                .getResourceAsStream("test-data/purchase-data.json");
        JsonNode node = mapper.readTree(inputStream).get(userKey).get("checkoutData");
        return mapper.convertValue(node, List.class);
    }
}
