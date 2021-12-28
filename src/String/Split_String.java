package String;

/**
 * 문자열 공백 기준으로 나누는 알고리즘
 */

import java.util.Arrays;

public class Split_String {

    public static void main(String[] args) {
        String input = "i am son";
        String[] words = input.split(" ");
        System.out.println(Arrays.toString(words));
    }

}
