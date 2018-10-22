package df;

import java.util.*;
import java.lang.*;

public class DataFrame {
    private ArrayList<Column> columns = new ArrayList<>();

    public DataFrame(String[] colNames, String[] dataTypes) {
        if (colNames.length == dataTypes.length) {
            for (int i = 0; i < colNames.length; ++i) {
                Column<Integer> col = new Column<>();// to change
                col.setName(colNames[i]);
                columns.add(col);
            }
        }
    }

    public int size() {
        return columns.get(0).getColumnSize();
    }

    public Column get(String colName) {
        for (Column col : columns) {
            if (col.getName().equals(colName)) {
                return col;
            }
        }
        return null;
    }

    public DataFrame get(String[] cols, boolean copy) {
        return null;
    }

    public DataFrame iloc(int i) {
        String[] names = new String[columns.size()];
        String[] dataTypes = new String[columns.size()]; //to change

        for (int j = 0; j < columns.size(); ++j) {
            names[j] = columns.get(j).getName();
            dataTypes[j] = columns.get(j).getName(); //to change
        }

        DataFrame tempDF = new DataFrame(names, dataTypes);

        for (int j = 0; j < columns.size(); ++j) {
            tempDF.columns.get(j).add(columns.get(j).get(i));
        }

        return tempDF;
    }

    public DataFrame iloc(int from, int to) {
        String[] names = new String[columns.size()];
        String[] dataTypes = new String[columns.size()]; //to change

        for (int j = 0; j < columns.size(); ++j) {
            names[j] = columns.get(j).getName();
            dataTypes[j] = columns.get(j).getName(); //to change
        }

        DataFrame tempDF = new DataFrame(names, dataTypes);

        for (int i = from; i < to; ++i) {
            for (int j = 0; j < columns.size(); ++j) {
                tempDF.columns.get(j).add(columns.get(j).get(i));
            }
        }

        return tempDF;
    }
}
