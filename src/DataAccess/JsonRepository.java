package DataAccess;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JsonRepository<T> implements IDataAccess<T> {

    private String filename;
    private Gson gson;
    private Type listType;
    private Class<T> clazz;

    public JsonRepository(String filename, Class<T> clazz) {
        this.filename = filename;
        this.clazz = clazz;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        this.listType = TypeToken.getParameterized(ArrayList.class, clazz).getType();
    }

    private List<T> readFromFile() {
        try (Reader reader = new FileReader(filename)) {
            List<T> items = gson.fromJson(reader, listType);
            return (items != null) ? items : new ArrayList<>();
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    private void writeToFile(List<T> items) {
        try (Writer writer = new FileWriter(filename)) {
            gson.toJson(items, writer);
        } catch (IOException e) {
            System.err.println("Error writing file: " + e.getMessage());
        }
    }

    @Override
    public T findById(String id) {
        List<T> items = readFromFile();

        for (T item : items) {
            try {
                Method getIdMethod = item.getClass().getMethod("getId");
                String currentId = (String) getIdMethod.invoke(item);

                if (currentId != null && currentId.equals(id)) {
                    return item;
                }
            } catch (Exception e) {
                System.err.println("Error finding item: " + e.getMessage());
            }
        }
        return null;
    }

    @Override
    public List<T> findAll() {
        return readFromFile();
    }

    @Override
    public void save(T item) {
        List<T> items = readFromFile();

        try {
            Method getIdMethod = item.getClass().getMethod("getId");
            String id = (String) getIdMethod.invoke(item);

            if (id == null || id.isEmpty()) {
                // Create new ID
                id = generateStringId();

                Method setIdMethod = item.getClass().getMethod("setId", String.class);
                setIdMethod.invoke(item, id);

                items.add(item);

            } else {
                // Update or insert
                boolean found = false;

                for (int i = 0; i < items.size(); i++) {
                    T existing = items.get(i);
                    String existingId = (String) getIdMethod.invoke(existing);

                    if (id.equals(existingId)) {
                        items.set(i, item);
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    items.add(item);
                }
            }

            writeToFile(items);

        } catch (Exception e) {
            System.err.println("Error saving item: " + e.getMessage());
        }
    }

    private String generateStringId() {
        return "ID-" + System.currentTimeMillis();
    }

    @Override
    public void delete(String id) {
        List<T> items = readFromFile();

        try {
            Method getIdMethod = clazz.getMethod("getId");

            items.removeIf(item -> {
                try {
                    String currentId = (String) getIdMethod.invoke(item);
                    return id.equals(currentId);
                } catch (Exception e) {
                    System.err.println("Error deleting item: " + e.getMessage());
                    return false;
                }
            });

            writeToFile(items);

        } catch (Exception e) {
            System.err.println("Error deleting item: " + e.getMessage());
        }
    }
}
