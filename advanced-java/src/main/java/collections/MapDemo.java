package collections;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class MapDemo {
    public static void main(String[] args) {
        Map<Integer, String> hashMap = new HashMap<>();
        Map<Integer, String> linkedHashMap = new LinkedHashMap<>();
        Map<Integer, String> treeMap = new TreeMap<>();

        testMap(hashMap);
        testMap(linkedHashMap);
        testMap(treeMap);
    }

    public static void testMap(Map<Integer, String> map) {
        map.put(1, "a");
        map.put(10, "aa");
        map.put(5, "b");
        map.put(100, "aaa");
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
        System.out.println();
    }
}
