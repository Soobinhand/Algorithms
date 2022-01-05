package Search;

/**
 * 이진 탐색 ( 기본 )
 *
 * 기본적인 이진 탐색은 키의 최소/최대 값을 관리하는 변수 lo와 hi를 두고서 키가 a[lo...hi] 배열 안에 있는지
 * 중간 영역 ( mid ) 부터 범위를 줄여 가면서 찾아가는 방식으로 수행된다.
 * 만약 키가 a[mid]와 같다면 리턴 값은 mid 가 되고 아니라면 키 값이 a[mid]보다 크냐 작으냐에 따라
 * 배열의 우측 또는 좌측 범위를 찾아 나간다.
 * 이 과정은 탐색 범위 안에서 키가 발견되거나, 탐색 범위 자체가 공백이면 종료된다.
 * 이진 탐색은 전체 배열의 일부분만 검사해서 키를 찾기 때문에 매우 효과적이다.
 *
 * =============== API ===============
 * rank( int key, int[] arr )
 * - 이 메서드는 int 타입 키와 함께 정렬된 상태의 int 타입 배열을 인수로 받아
 *   그 배열 안에 키가 존재하면 그 키 값에 해당하는 배열의 index 를 리턴하고
 *   없으면 -1을 리턴한다.
 */

public class BinarySearch {

    public static int rank(int key, int[] arr){
        int lo = 0;
        int hi = arr.length - 1;
        while (lo <= hi){
            int mid = (lo + hi) / 2;
            if (key < arr[mid]) hi = mid - 1;
            else if (key > arr[mid]) lo = mid + 1;
            else return mid;
        }
        return -1;
    }

    // 테스트
    public static void main(String[] args) {
        int key = 5;
        int[] arr = {0,1,2,5,7,8,9,10};
        System.out.println(rank(key, arr)); // 3
    }
}
