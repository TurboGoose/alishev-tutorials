package interfaces;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StreamsDemo {
    static final int CAP = 10;

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(CAP);
        int[] arr = new int[CAP];

        fillList(list);
        fillArr(arr);

//        list = list.stream().map(x -> x * 2).toList();
//        System.out.println(list);
//
//        arr = Arrays.stream(arr).filter(x -> x % 2 == 0).toArray();
//        System.out.println(Arrays.toString(arr));

        int sum = list.stream().reduce(Integer::sum).get();
        System.out.println(sum);
    }

    static void fillList(List<Integer> list) {
        for (int i = 0; i < CAP; i++) {
            list.add(i + 1);
        }
    }

    static void fillArr(int[] arr) {
        for (int i = 0; i < CAP; i++) {
            arr[i] = i + 1;
        }
    }
}
