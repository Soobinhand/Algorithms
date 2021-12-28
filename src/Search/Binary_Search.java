package Search;

import java.util.Arrays;

/**
 이분 탐색 솔루션.
 찾을 key 와 탐색할 배열을 넣으면 된다.
 반환은 해당 수의 인덱스.
 */

public class Binary_Search {

    public static int binary_search(int key, int[] a){
        int lo = 0;
        int hi = a.length - 1;
        while(lo <= hi){
            int mid = (hi + lo) / 2;
            if (key < a[mid]) hi = mid - 1;
            else if (key > a[mid]) lo = mid + 1;
            else return mid;
        }
        return -1;
    }

    // 테스트 클라이언트
    public static void main(String[] args) {
        int[] a = new int[]{1,6,5,4,3,87};
        Arrays.sort(a);
        System.out.println(binary_search(5, a));
    }
}
