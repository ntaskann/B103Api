package util;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

public class ObjectMapperUtils {

    /*
        <T> T --> herhangi bir data tipi
         readValue(json, cls); methodu 1.parametredeki String formatindaki json datayi
         2.parametredeki java objesine cevirir
     */
    public static <T> T convertJsontoJava(String json, Class<T> cls) { //Generic method


        try {
            return new ObjectMapper().readValue(json, cls);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
