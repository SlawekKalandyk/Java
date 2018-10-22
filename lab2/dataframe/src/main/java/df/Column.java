package df;

import java.util.*;

public class Column<T> {
    private String name;
    private int columnSize;
    private ArrayList<T> columnData = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getColumnSize() {
        return columnData.size();
    }

    public T get(int i) {
        return columnData.get(i);
    }

    public void add(T data) {
        columnData.add(data);
    }

}
