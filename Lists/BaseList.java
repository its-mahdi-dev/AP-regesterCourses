package Lists;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Models.Model;
import Models.Student;

public abstract class BaseList<T extends Model> {
    private List<T> items;
    private String path;

    public BaseList(String path) {
        this.path = path;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public T findOne(int id) {
        for (T item : items) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    public List<T> findById(int id) {
        List<T> newItems = new ArrayList<>();
        for (T item : items) {
            if (item.getId() == id) {
                newItems.add(item);
            }
        }
        return newItems;
    }

    public List<T> findByName(String name) {
        List<T> newItems = new ArrayList<>();
        for (T item : items) {
            if (item.getName().contains(name)) {
                newItems.add(item);
            }
        }
        return newItems;
    }

    public List<T> getAll() {
        return items;
    }

    protected static Map<String, String> convertToStringMap(String str) {
        Map<String, String> map = new HashMap<>();
        str = str.substring(1, str.length() - 1);
        if (!str.isEmpty()) {
            String[] pairs = str.split(",(?=\\s[a-zA-Z])");
            for (String pair : pairs) {
                int index = pair.indexOf('=');
                if (index != -1) {
                    String key = pair.substring(0, index).trim();
                    String value = pair.substring(index + 1).trim();
                    map.put(key, value);
                }
            }
        }
        return map;
    }

    protected List<Integer> getIntegers(String line) {
        List<Integer> list = new ArrayList<>();
        String[] parts = line.split("-");
        for (int i = 0; i < parts.length; i++) {
            list.add(Integer.parseInt(parts[i]));
        }
        return list;
    }

    public Map<String, Integer[]> getTime(String line) {
        String[] times = line.split("-");
        Map<String, Integer[]> time = new HashMap<>();
        for (String t : times) {
            String[] newTime = t.split(":");
            String day = newTime[0];
            String[] hours = newTime[1].split("~");
            time.put(
                    day, new Integer[] { Integer.parseInt(hours[0]), Integer.parseInt(hours[1]) });
        }
        return time;

    }

    public void updateList() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, false))) {
            for (T s : items) {
                writer.write(s.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
