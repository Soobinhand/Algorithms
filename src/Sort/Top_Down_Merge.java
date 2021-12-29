package Sort;

import java.util.Arrays;

/**
 * 하향식 병합 정렬 알고리즘
 */

public class Top_Down_Merge {

    private static Comparable[] aux; // 임시 배열

    public static void sort(Comparable[] a){
        aux = new Comparable[a.length];
        sort(a, 0, a.length - 1);
    }

    public static void sort(Comparable[]a, int lo, int hi){
        if (hi <= lo) return;
        int mid = (hi+lo)/2;
        sort(a, lo, mid);
        sort(a, mid+1,hi);
        merge(a, lo, mid, hi);
    }

    public static boolean less(Comparable v, Comparable w){
        return v.compareTo(w) < 0;
    }

    public static void merge(Comparable[] a, int lo, int mid, int hi){
        int i = lo;
        int j = mid + 1;
        for (int k = lo; k <= hi; k++){
            aux[k] = a[k];
        }

        for (int k = lo;k<=hi;k++){
            if (i > mid){
                a[k] = aux[j++];
            }
            else if(j > hi){
                a[k] = aux[i++];
            }
            else if(less(aux[j], aux[i])){
                a[k] = aux[j++];
            }
            else{
                a[k] = aux[i++];
            }
        }
    }

    // 테스트 클라이언트
    public static void main(String[] args) {
        Comparable[] a = {9,8,0,6,8};
        sort(a);
        System.out.println(Arrays.toString(a));
    }
}