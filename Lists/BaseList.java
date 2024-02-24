package Lists;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseList<T> {
    private List<T> items;

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
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
}
