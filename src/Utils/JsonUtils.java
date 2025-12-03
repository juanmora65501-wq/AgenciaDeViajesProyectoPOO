package Utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Utilidad para manejo de JSON usando Gson.
 * Se usa en toda la capa DataAccess.
 */
public class JsonUtils {

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    /**
     * Guarda una lista de objetos en un archivo JSON.
     *
     * @param path ruta del archivo
     * @param data lista a guardar
     * @param <T> tipo de la lista
     * @throws IOException si el archivo no se puede escribir
     */
    public static <T> void saveList(String path, List<T> data) throws IOException {
        try (FileWriter writer = new FileWriter(path)) {
            gson.toJson(data, writer);
        }
    }

    /**
     * Carga una lista desde un archivo JSON.
     *
     * @param path ruta del archivo
     * @param clazz clase del tipo contenido en la lista
     * @param <T> tipo de objeto
     * @return lista cargada o null si hay error
     */
    public static <T> List<T> loadList(String path, Class<T> clazz) {
        try (FileReader reader = new FileReader(path)) {

            Type type = TypeToken.getParameterized(List.class, clazz).getType();
            return gson.fromJson(reader, type);

        } catch (IOException e) {
            return null; // si no existe el archivo, null
        }
    }

    /**
     * Convierte un objeto a JSON (String).
     */
    public static String toJson(Object obj) {
        return gson.toJson(obj);
    }

    /**
     * Convierte JSON (String) en un objeto del tipo dado.
     */
    public static <T> T fromJson(String json, Class<T> clazz) {
        return gson.fromJson(json, clazz);
    }
}
