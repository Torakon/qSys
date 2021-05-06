package quests.gen;

import java.util.ArrayList;

public class TypeDef<T> {
    private int size;
    private Object[] arr;

    public TypeDef(int size) {
        this.size = size;
        arr = new Object[size];
    }

    public void put(T key) {
        int emptyFound = findEmpty();
        if (emptyFound >= 0) {
            arr[emptyFound] = new Pair(key, false);
        }
    }

    public void toggle(T key) {
        for (int i = 0; i < size; i++) {
            Pair entry = (Pair) arr[i];
            if (entry.key == key) {
                entry.value = !entry.value;
            }
        }
    }

    private int findEmpty() {
        for (int i = 0; i < size; i++) {
            if (arr[i] == null) {
                return i;
            }
        }
        return -1;
    }

    public ArrayList<T> getAllTrue() {
        ArrayList<T> returnList = new ArrayList<T>();
        for (int i = 0; i < size; i++) {
            Pair entry = (Pair) arr[i];
            if (entry.value == true) {
                returnList.add(entry.key);
            }
        }
        return returnList;
    }

    private class Pair {
        private T key;
        private boolean value;

        public Pair(T key, boolean value) {
            this.key = key;
            this.value = value;
        }
    }
}
