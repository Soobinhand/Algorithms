package Sort;

import java.util.Arrays;

/**
 * 선택 정렬 알고리즘
 */

public class Selection {
    public static void sort(Comparable[] a){
        int N = a.length; // 배열의 크기
        for (int i=0;i<N;i++){
            int min = i;
            for (int j=i+1;j<N;j++){
                if (less(a[j], a[min])){
                    min = j;
                }
            }
            exch(a, i,min);
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
        Comparable[] a = {4,3,5,1};
        sort(a);
        System.out.println(Arrays.toString(a));
    }
}
