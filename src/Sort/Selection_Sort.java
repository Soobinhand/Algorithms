package Sort;

import java.util.Arrays;

/**
 * 선택 정렬
 *
 * 가장 단순한 정렬 알고리즘 중 하나는 선택 정렬이다.
 * 선택 정렬의 동작은 아래와 같다.
 * 먼저 배열 안에서 가장 작은 항목을 찾는다. 그리고 그 항목을 배열의 가장 첫 항목과 자리바꿈한다.
 * 만약 맨 앞이 젤 작으면 그대로 있는다. 그리고 나서 다음 번으로 작은 항목을 찾아서 두 번째 항목과 자리바꿈한다.
 * 이러한 작업을 모든 배열이 정렬될 때까지 반복한다.
 * 반복적으로 남은 배열 안에서 가장 작은 항목을 선택하기 때문에 선택 정렬이라고 부른다.
 * 직접 해보면 제일 작은걸 맨 앞으로 보내는 행위를 계속한다. 그냥 배열 순회 내내 한다.
 * 그리고 그게 끝나면 당연히 맨 앞에 있는게 젤 작은 것이다.
 * 그걸 반복하면 정렬이 될 수 밖에 없다.
 *
 * =============== API ===============
 * less ( Comparable v, Comparable w )
 * - 수를 Comparable 로 받아왔기 때문에 compareTo 메소드 사용이 가능하다.
 *   수도 가능하고 물론 문자열 및 문자도 가능하다.
 *   v가 w보다 작다면 true 를 반환한다. 이것은 0보다 작다는 것으로 구현되었다.
 *   compareTo 와 관련된 메소드들은 다음에 더 자세히 공부한다.
 *   암튼 왼쪽 것이 오른쪽 것보다 작으면 true, 크거나 같으면 false 이다.
 *
 * exch ( Comparable[] a, int i, int j )
 * - 둘의 자리를 바꿔주는 메소드이다.
 *   만약 i가 3이고, j가 4라고 가정하자. t라는 임시 구역을 두고 a[i]를 넣는다.
 *   그럼 t = 3이된다. 후에 i에 j를 넣는다. 그러면 a[i]는 4가된다.
 *   마지막으로 a[j]에 아까 t를 넣으면 a[j]는 3이된다.
 *   둘의 자리가 바뀌었다. 정렬 메소드이다.
 *
 * isSorted ( Comparable[] a )
 * - 정렬되었다는 사실을 확인해주는 메소드이다. 디버깅에 좋은 테스트코드이다.
 *   a 배열이 매개변수로 들어가서 1부터 끝까지 순회하는데, 그 전의 인덱스와 계속 비교한다.
 *   그래서 하나라도 true 가 나오면 그것은 정렬이 되어있지 않은 상태라는 것이다.
 *   왜냐하면 더 작아? 라고 물어봤는데 응 작아! 라고 답이 오면 안되는 것이다.
 *   a[i]와 a[i-1]의 비교이기 때문이다.
 *
 * selection_sort ( Comparable[] a )
 * - 배열 크기만큼 순회한다. 그 중에 다음 것을 가리키는 j도 돌린다.
 *   즉, 이중 for 문을 돌린다. 그렇게 되면 계속 다음 것 하고 비교할 수 있는 상태가 된다.
 *   정렬이 되려면 a[i]가 무조건 a[j]보다 작아야한다. 근데 그게 아니라면?
 *   그러면 exch() 를 해줘야한다. 그 판단을 less 함수가 해주는 것이다.
 *   만약 그게 아니라면 아무것도 안한다.
 *   a[j]가 a[i]보다 작아? 응! 그러면 둘이 자리바꿔!!
 *   무조건 다음에 나오는 숫자는 커야한다는 데에서 기인한다.
 *
 */

public class Selection_Sort {

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

    public static void selection_sort(Comparable[] a){
        int n = a.length;
        for (int i=0;i<n;i++){
            for (int j=i+1;j<n;j++){
                if (less(a[j], a[i])) exch(a, i, j);;
            }

        }
        assert isSorted(a);
    }

    // 테스트
    public static void main(String[] args) {
        Comparable[] a = new Comparable[]{5,4,3,2,1};
        selection_sort(a);
        System.out.println(Arrays.toString(a));
    }
}
