package Sort;

import java.util.Arrays;

/**
 * 이진 삽입 정렬
 *
 * 삽입 정렬이 상당히 큰 배열에선 상당히 느리다는 것은 이미 알고 있다.
 * 이진 삽입 정렬은 조금은 더 빠르게 해주는 정렬기법이다.
 * 삽입 정렬과 메커니즘은 동일하지만 원소가 들어갈 위치를 선형 탐색이 아니라 이분 탐색을 한다는 점이 다르다.
 * 하지만 무조건 더 빠르다는 것은 아니다.
 *
 * =============== API ===============
 * less, exch() 는 생략한다.
 *
 * binaryInsertion_sort ( Comparable[] a )
 * - 우선 똑같이 N은 a 배열의 길이로 할당한다.
 *   그 후에 for 문도 사실 같다. 이것은 바로 1부터 N까지 간다는 것이다.
 *   근데 그 안이 다르다. 바로 이분탐색을 한다는 것이다.
 *   원래는 for 문을 다시 돌려서 이중 for 문을 돌려줬다. 그것도 i부터 0까지 계속해서 돌려준다.
 *   하지만 이분 탐색은 그렇지 않다.
 *   우선 v를 정해준다. v는 현재 target 이 되는 값이다. v를 어디다가 넣어야 하지? 가 된다.
 *   그 후, lo는 0 hi는 i로 정해준다. 이는 탐색 범위가 i만큼 이라는 뜻이다.
 *   그 후 mid 를 구해주는데 이는 lo + hi 를 2로 나눠준 값을 할당한다.
 *   여기서 주의할 점은 (lo + hi) / 2 도 가능한 식이지만 굳이 더 따지고 들어간다면 이건 나중에 오류가 날 가능성이 있다.
 *   왜냐하면 hi가 integer 의 끝값이고 lo가 1이라고 해보자. 둘 더하면 overflow 가 발생한다.
 *   그래서 이걸 lo + ( hi - lo ) / 2로 해준다. 이건 거리를 구하는 공식이다. 실제에선 똑같은 결과를 반환하지만 컴퓨터에선 그렇지 않다.
 *   다시 이진 탐색으로 돌아오자.
 *   mid 를 구했다. 그러면 0과 i 사이의 중간 위치에 있는 값이다. 그건.
 *   그거랑 i랑 비교해서 만약에 mid 에 있는 항목이 더 크다면 hi를 mid 로 바꿔준다.
 *   그게 아니라면 lo를 mid + 1로 바꿔준다. 그리고 그게 반복되고 그렇지 않을 때 그만둔다.
 *   그러면 지금 가리키고 있는 곳은 결국 i보다 크다는 것이다. 그러면 바꿔야지.
 *   그렇게 바꿔준다.
 */

public class BinaryInsertion_Sort {

    public static boolean less(Comparable v, Comparable w){
        return v.compareTo(w) < 0;
    }

    public static void exch(Comparable[] a, int i, int j){
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void binaryInsertion_sort(Comparable[] a){
        int N = a.length;
        for (int i=1;i<N;i++){
            Comparable v = a[i];
            int lo = 0;
            int hi = i;
            while (lo < hi){
                int mid = (lo + hi) / 2;
                if (less(v, a[mid])) hi = mid;
                else lo = mid + 1;
            }
            for (int j = i; j > lo; --j){
                a[j] = a[j-1];
            }
            a[lo] = v;
        }
    }

    // 테스트
    public static void main(String[] args) {
        Comparable[] a = new Comparable[]{5,4,3,3,7,43,76,32,2,4,21};
        binaryInsertion_sort(a);
        System.out.println(Arrays.toString(a));
    }
}
