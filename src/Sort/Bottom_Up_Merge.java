package Sort;

import java.util.Arrays;

/**
 * 상향식 병합 정렬 알고리즘
 */

public class Bottom_Up_Merge {

    private static Comparable[] aux; // 임시 배열

    public static void sort(Comparable[] a){
        int N = a.length;
        aux = new Comparable[N];
        for (int size = 1; size < N; size = size+size){
            for (int lo = 0; lo < N - size; lo += size+size){
                merge(a, lo, lo + size-1, Math.min(lo+size+size-1, N-1));
            }
        }
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