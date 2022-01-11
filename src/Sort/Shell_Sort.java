package Sort;

import java.util.Arrays;

/**
 * 쉘 정렬
 *
 * 우선 삽입 정렬은 크기가 큰 무작위 배열을 처리하는데 느리다. 왜냐하면 인접한 항목과의 교환만 일어나기 때문에
 * 항목들이 한 번에 한 위치로만 이동될 수 있다.
 * 쉘 정렬은 삽입 정렬의 확장 버전으로 서로 멀리 떨어진 항목 간에도 교환이 일어날 수 있게 함으로써 삽입 정렬이 빠르게
 * 처리될 수 있는 부분적으로 정렬된 배열을 만든다.
 * 기본적인 아이디어는 매 h 번째 항목들 간에 순서를 따질 때, 정렬된 상태가 되도록 배열을 재정리하는 것이다.
 * 이렇게 부분적으로 정렬된 배열을 h-정렬되었다고 한다.
 * 큰 h값에 대해 h-정렬을 함으로써 서로 멀리 떨어진 배열 항목들을 이동시킬 수 있고 이에 따라 작은 h값에 대한
 * h-정렬들을 쉬워지게 만들 수 있다. h가 1이 될 때까지 반복하는 어떤 절차를 만든다면 배열 전체가 정렬된다.
 * 이것이 바로 쉘 정렬이다.
 * 결국 중요한 것은 인접한 항목과의 교환만 일어나서 느리던 삽입 정렬을 먼 항목과의 교환도 가능하게 해서 조금의 성능을 높인 게 쉘정렬임.
 *
 * =============== API ===============
 * less, exch() 는 생략한다.
 *
 * shell_sort ( Comparable[] a )
 * - 우선 N을 배열 a의 길이로 할당한다.
 *   그리고 h를 정해준다. 근데 이걸 정할 때 아무거나 해도 상관없다.
 *   3억을 넣어도 성립한다. 물론 시간이 더 걸릴 것이다. 배열 길이보다 작으면 적당하다.
 *   그 후에, 이걸 얼만큼 내보낼 것인지가 주요 관심사가 된다.
 *   배열의 길이가 5고 h를 1로 설정했다고 가정하자.
 *   그럼 우선 1을 길이를 3으로 나눠준 값보다 작으면 3배로 늘려준다. 그리고 1을 더해준다. 이는 이후에 나올 나누기 연산때문이다.
 *   또한 후에 나올 1을 위해 1을 더해주는 것이다.
 *   이 연산을 하는 이유는 절대 배열의 길이는 넘지 않으면서 뒤의 항목이랑 비교하기 위함이다.
 *   그래서 맨 뒤의 항목들과 맨 앞의 항목들을 비교한다. 비교가 끝나면 당연히 교체가 일어난다.
 *   그게 끝나면 h를 다시 3으로 나눠준다. 그러면 아까 숫자보다 작은 값이된다.
 *   그걸 다시 연산에 넣어준다. 그걸 계속 반복해주면 마치 톱니바퀴처럼 정렬이 되는 과정을 볼 수 있다.
 *
 */

public class Shell_Sort {

    public static boolean less(Comparable v, Comparable w){
        return v.compareTo(w) < 0;
    }

    public static void exch (Comparable[] a, int i, int j){
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void shell_sort(Comparable[] a){
        int N = a.length;
        int h = 1;
        while (h < N/3) h = h*3+1;
        while (h >= 1){
            for (int i=h;i<N;i++){
                for (int j=i;j>=h && less(a[j], a[j-h]); j -= h){
                    exch(a, j, j-h);
                }
            }
            h /= 3;
        }
    }

    // 테스트
    public static void main(String[] args) {
        Comparable[] a = new Comparable[]{200,5,4,3,2,3,2,1};
        shell_sort(a);
        System.out.println(Arrays.toString(a));
    }
}
