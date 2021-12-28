package Sort;

import java.util.Arrays;

/**
 * 삽입 정렬 알고리즘
 */

public class Insertion {
    public static void sort(Comparable[] a){
        int N = a.length;
        for (int i = 1;i<N;i++){
            for (int j = i;j > 0&&less(a[j], a[j-1]); j--){
                exch(a, j, j-1);
            }
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
        Comparable[] a = {0,9,7,8};
        sort(a);
        System.out.println(Arrays.toString(a));
    }
}