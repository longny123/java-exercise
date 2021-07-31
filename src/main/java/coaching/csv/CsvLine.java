package coaching.csv;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Supplies information about CSV line
 */
public class CsvLine {

    Map<Integer, String> dataList;

    public CsvLine(){
        dataList = new HashMap<>();
    }

    /**
     * Get data at specific position in a row
     *
     * @param index Position of segment
     * @return Data
     */
    public String get(int index) {
       return dataList.get(index);
    }

    /**
     * Set data in a specific position
     *
     * @param position Position
     * @param data     Data
     */
    public void set(int position, String data) {
        dataList.put(position, data);
    }

    /**
     * Get data
     *
     * @return
     */
    public Collection<String> getData() {
        return dataList.values();
    }
}
