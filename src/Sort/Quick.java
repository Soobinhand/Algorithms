package Sort;

import java.util.Arrays;

/**
 * 퀵 정렬 알고리즘
 */

public class Quick {

    public static void sort(Comparable[] a){
        sort(a, 0, a.length-1);
    }

    public static void sort(Comparable[] a, int lo, int hi){
        if (hi <= lo){
            return;
        }
        int i = partition(a, lo, hi);
        sort(a, lo, i-1);
        sort(a, i+1, hi);
    }

    public static int partition(Comparable[] a, int lo, int hi){
        int i = lo;
        int j = hi+1;
        Comparable v = a[lo];
        while (true){
            while(less(a[++i], v)){
                if (i == hi){
                    break;
                }
            }
            while (less(v, a[--j])){
                if (j == lo){
                    break;
                }
            }
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
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
        Comparable[] a = new Comparable[]{9,8,7};
        sort(a);
        System.out.println(Arrays.toString(a));
    }
}
