package Sort;

import java.util.Arrays;

/**
 * 삽입 정렬
 *
 * 무언가 값을 고르고 적당한 곳에 넣고 싶다. 그럴 때에도 정렬이 완성된다.
 * 항목을 삽입할 공간을 만들기 위해 삽입할 항목보다 큰 항목들을 전부 오른쪽으로 밀어서 이동시킨다.
 * 이제 확보된 공간에 항목을 삽입한다. 이러한 방법을 구현한 것이 삽입 정렬이다.
 *
 * =============== API ===============
 * less, exch, isSorted
 * - 생략한다.
 *
 * insertion_sort ( Comparable[] a )
 * - 선택 정렬과는 조금 다르다. 선택 정렬은 맨 첫 원소를 제일 작게 만들고 그걸 반복하는 연산이라면
 *   삽입 정렬은 전 원소가 뒤 원소보다 크다면 자리를 비켜주는 연산이다.
 *   그래서 뒤에서 앞으로 비교한다. 선택 정렬과는 반대다.
 *   왜냐하면 큰 것은 작은 것을 위해 자리를 내줘야하기 때문에 큰 것을 먼저 알아야 하기 때문이다.
 *   그래서 앞에 것이 더 크다면 뒤에 것을 위해 자리를 넘겨줘야 하기 때문에 교환이 일어난다.
 *   자리를 계속 내줘야하기 때문에 내림차순으로 되어있는 배열을 정렬하기엔 상당한 시간이 걸린다.
 *
 */

public class Insertion_Sort {

    private static boolean less(Comparable v, Comparable w){
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j){
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static boolean isSorted(Comparable[] a){
        for (int i=1;i<a.length;i++){
            if (less(a[i], a[i-1])) return false;
        }
        return true;
    }

    public static void insertion_sort(Comparable[] a){
        int n = a.length;
        for (int i=1;i<n;i++){
            for (int j = i; j >0&&less(a[j], a[j-1]);j--){
                exch(a, j, j-1);
            }
        }
    }

    // 테스트
    public static void main(String[] args) {
        Comparable[] a = new Comparable[]{2,3,7,6,4,5};
        insertion_sort(a);
        System.out.println(Arrays.toString(a));
    }

}
