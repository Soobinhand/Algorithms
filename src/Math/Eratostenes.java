package Math;

import java.util.Arrays;

/**
 두 숫자 사이의 소수들과 그 갯수를 구하는 알고리즘
 에라토스테네스의 체 사용.
 */

public class Eratostenes {

    public static void eratostenes(int start, int end){
        boolean[] isPrime = new boolean[end+1];
        Arrays.fill(isPrime, true);
        int count = 0;

        isPrime[0] = isPrime[1] = false;
        for (int i = 2; i*i <= end; i++){
            if (isPrime[i]){
                for (int j = i*i; j <= end; j+=i){
                    isPrime[j] = false;
                }
            }
        }

        for (int i = start; i <= end; i++){
            if (isPrime[i]) {
                System.out.print(i+" ");
                count++;
            }
        }
        System.out.println();
        System.out.println(count + "개");
    }

    // 테스트 클라이언트
    public static void main(String[] args) {
        int start = 3;
        int end = 16;
        eratostenes(start, end);
    }
}

