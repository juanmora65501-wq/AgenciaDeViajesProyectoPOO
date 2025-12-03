package DataAccess;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JsonRepository<T> implements IDataAccess<T> {

    private final String filePath;
    private final Type listType;
    private final Gson gson;

    public JsonRepository(String filePath, Type listType) {
        this.filePath = filePath;
        this.listType = listType;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    @Override
    public List<T> getAll() {
        try (FileReader reader = new FileReader(filePath)) {
            List<T> data = gson.fromJson(reader, listType);
            return data != null ? data : new ArrayList<>();
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public T getById(String id) {
        return getAll().stream()
                .filter(item -> {
                    try {
                        return item.getClass().getMethod("getId").invoke(item).equals(id);
                    } catch (Exception e) {
                        return false;
                    }
                })
                .findFirst().orElse(null);
    }

    @Override
    public void saveAll(List<T> data) {
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(data, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void add(T entity) {
        List<T> data = getAll();
        data.add(entity);
        saveAll(data);
    }

    @Override
    public void update(T entity) {
        List<T> data = getAll();
        try {
            String id = (String) entity.getClass().getMethod("getId").invoke(entity);

            for (int i = 0; i < data.size(); i++) {
                T current = data.get(i);

                String currentId = (String) current.getClass().getMethod("getId").invoke(current);

                if (currentId.equals(id)) {
                    data.set(i, entity);
                    break;
                }
            }
            saveAll(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String id) {
        List<T> data = getAll();
        data.removeIf(item -> {
            try {
                return item.getClass().getMethod("getId").invoke(item).equals(id);
            } catch (Exception e) {
                return false;
            }
        });
        saveAll(data);
    }
}
