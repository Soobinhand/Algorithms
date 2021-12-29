package Sort;

import java.util.Arrays;

/**
 * 쉘 정렬 알고리즘
 */

public class Shell {

    public static void sort(Comparable[] a){
        int N = a.length;
        int h = 1;
        while (h < N/3){
            h = 3 * h + 1;
        }
        while (h >= 1){
            for (int i = h; i<N;i++){
                for (int j = i; j >= h && less(a[j], a[j-h]); j -= h){
                    exch(a, j, j-h);
                }
            }
            h /= 3;
        }
    }

    public static boolean less(Comparable v, Comparable w){
        return v.compareTo(w) < 0;
    }

    public static void exch(Comparable[] a, int i, int j){
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    // 테스트 클라이언트
    public static void main(String[] args) {
        Comparable[] a = {1,6,5,4};
        sort(a);
        System.out.println(Arrays.toString(a));
    }
}