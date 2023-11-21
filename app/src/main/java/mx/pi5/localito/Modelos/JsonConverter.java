package mx.pi5.localito.Modelos;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonConverter {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String convertToJson(Object objeto) throws Exception {
        return objectMapper.writeValueAsString(objeto);
    }

    public static <T> T convertFromJson(String json, Class<T> tipoClase) throws Exception {
        return objectMapper.readValue(json, tipoClase);
    }
}
