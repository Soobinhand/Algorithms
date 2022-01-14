package Sort;

import java.util.Arrays;

/**
 * 병합 정렬 ( 상향식 )
 *
 * 상향식 병합 정렬은 하향식의 반대라고 생각하면 된다.
 * 하향식은 재귀적으로 계속 쪼개고 다시 오면서 병합한 반면,
 * 상향식은 쪼개는 행위를 size 를 증가시키면서 진행하는 것이다.
 * 작은 부분 배열들을 한 번의 순회로 병합할 수 있도록 병합 작업의 순서를 조정하는 것이다.
 * 이 방법은 재귀적인 방법에 비해 코드 양이 더 적기도 하다.
 * 먼저 1:1 병합 단계부터 시작한다. 그 다음은 2:2 ... 계속 해나간다.
 *
 * =============== API ===============
 * less 메서드와 merge 메서드는 생략한다.
 *
 * sort ( Comparable[] a )
 * - 우선 N은 정렬할 배열의 길이를 할당한다.
 *   aux 배열을 초기화 해준다.
 *   상향식 병합 정렬은 전체 배열에 대한 병합 단계의 나열로 이루어져 있다.
 *   매 단계마다 size : size 병합이 이루어진다.
 *   size 는 1에서 시작해서 매 단계마다 두 배씩 커진다.
 *   마지막 부분 배열의 크기는 전체 배열의 크기가 size 의 짝수 배열일 때만 size 가 된다.
 *   그 외의 경우에는 size 보다 작다.
 */

public class Merge_Sort_BU {

    private static Comparable[] aux; // 병합을 위한 임시 배열

    private static boolean less(Comparable a, Comparable b){
        return a.compareTo(b) < 0;
    }

    public static void merge(Comparable[] a, int lo, int mid, int hi){
        int i = lo;
        int j = mid + 1;
        for (int k = lo; k <= hi; k++){
            aux[k] = a[k];
        }
        for (int k = lo; k <= hi; k++){
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (less(a[j], a[mid])) a[k] = aux[j++];
            else a[k] = aux[i++];
        }
    }

    public static void sort(Comparable[] a){
        int N = a.length;
        aux = new Comparable[N];
        for (int size = 1; size < N; size = size + size){
            for (int lo = 0; lo < N-size; lo += size + size){
                merge(a, lo, lo + size - 1, Math.min(lo + size + size - 1, N - 1));
            }
        }
    }

    // 테스트
    public static void main(String[] args) {
        Comparable[] a = new Comparable[]{"B","A"};
        sort(a);
        System.out.println(Arrays.toString(a));
    }

}
