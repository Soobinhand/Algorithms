package Sort;

import java.util.Arrays;

/**
 * 정렬 클래스를 위한 템플릿
 */

public class Template_For_Sort {
    public static void sort(Comparable[] a){
        Selection.sort(a);
        Insertion.sort(a);
    }

    public static boolean less(Comparable v, Comparable w){
        return v.compareTo(w) < 0;
    }

    public static void exch(Comparable[] a, int i, int j){
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void show(Comparable[] a){
        System.out.println(Arrays.toString(a));
    }

    public static boolean isSorted(Comparable[] a){
        for (int i=1;i<a.length;i++){
            if (less(a[i], a[i-1])) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String[] a = {"a","c","b"};
        sort(a);
        assert isSorted(a);
        show(a);
    }
}
