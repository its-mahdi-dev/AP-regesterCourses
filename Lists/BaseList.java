package Lists;

import java.util.ArrayList;
import java.util.List;

import Models.Model;

public abstract class BaseList<T extends Model> {
    private List<T> items;

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
}
