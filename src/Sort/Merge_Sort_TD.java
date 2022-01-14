package Sort;

import java.util.Arrays;

/**
 * 병합 정렬 ( 하향식 )
 *
 * 병합은 두 개의 정렬된 배열을 하나의 큰 정렬된 배열로 합치는 작업이다.
 * 이 작업은 단순한 재귀적 방법으로 쉽게 구현된다.
 * 즉, 배열을 정렬할 때, 그것을 반으로 나누어 각각의 절반에 대해서 재귀적으로 정렬을 수행하고,
 * 다시 재귀적으로 정렬 결과를 병합한다.
 * 병합 정렬의 장점은 시간 복잡도를 전보다 줄이는 것이지만
 * 단점은 추가적인 메모리 공간을 필요로 한다는 점이다.
 * 거기에 하향식과 상향식이 있는데 여기선 하향식 병합 정렬을 알아보겠다.
 * 하향식 병합 정렬은 재귀적으로 구현되어 있다. 우선 배열을 반으로 계속 쪼갠다.
 * 마지막에 도달했을 때, 다시 올라가면서 병합을 시작한다.
 * 그렇게 병합을 하면서 해당 원소들을 정렬하게 된다.
 *
 * =============== API ===============
 * less 메서드는 생략한다.
 *
 * merge ( Comparable[] a, int lo, int mid, int hi )
 * - 해당 메서드는 병합을 하는 메서드이다.
 *   병합을 하는데 사실 이건 정렬이 되게끔 만들어주는 메서드다.
 *   정렬할 배열이 들어오면 배열을 두 부분을 쪼갠다.
 *   그리고 aux 배열에 a 배열을 복사한다. tmp 같은 느낌.
 *   그 다음 배열을 순회하면서 if 문을 순회한다.
 *   네 가지 조건이 있다.
 *   왼쪽 절반이 소진되었거나, 오른쪽 절반이 소진되었거나, 현재 키의 오른쪽이 왼쪽보다 작거나, 현재 키의 오른쪽이 왼쪽보다 크거나 이다.
 *   이렇게 되면 a[lo...hi] 구간을 병합하게 된다.
 *
 * sort ( Comparable[] a )
 * - 우선 복제 배열 aux 를 초기화한다.
 *   여기서 하는 이유는 한 번만 초기화하려고.
 *   merge 에서 하게되면 merge 를 호출할 때마다 초기화된다.
 *   그러면 이제 진짜 sort 함수로 들어가준다. ( 오버로딩 )
 *
 * sort ( Comparable[] a, int lo, int hi )
 * - a[lo...hi] 구간을 정렬한다.
 *   만약에 hi가 lo 보다 작거나 같으면 멈춘다.
 *   그게 아니면 mid 를 구해준다. mid 는 lo와 hi의 중간 값이다.
 *   그리고 sort 재귀를 진행한다. 왼쪽 배열부터 정렬하고 오른쪽 배열을 정렬한다.
 *   그걸 lo~mid, mid+1~hi 로 표현되는 것이다.
 *   그러면 왼쪽 절반 정렬, 오른쪽 절반 정렬이된다.
 *   그리고 그것들을 merge 해준다. 결과를 병합하는 것이다.
 *   서로서로 다 정렬하고 마지막에 병합하면 완전한 정렬된 배열이 되어있는 것이다.
 *
 */

public class Merge_Sort_TD {

    private static Comparable[] aux; // 임시 배열. a 복제 배열.

    public static boolean less(Comparable a, Comparable b){
        return a.compareTo(b) < 0;
    }

    public static void merge(Comparable[] a, int lo, int mid, int hi){
        int i = lo;
        int j = mid + 1;
        for (int k = lo; k <= hi; k++){
            aux[k] = a[k];
        }
        for (int k = lo; k<= hi; k++){
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (less(aux[j], aux[mid])) a[k] = aux[j++];
            else a[k] = aux[i++];
        }
    }

    public static void sort(Comparable[] a){
        aux = new Comparable[a.length];
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi){
        if (hi <= lo) return;
        int mid = lo + (hi - lo)/2;
        sort(a, lo, mid);
        sort(a, mid+1, hi);
        merge(a, lo, mid, hi);
    }

    // 테스트
    public static void main(String[] args) {
        Comparable[] a = new Comparable[]{5,4,3,2,1};
        sort(a);
        System.out.println(Arrays.toString(a));
    }

}
