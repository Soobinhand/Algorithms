package String;

/**
 문자열이 회문인지 확인하는 알고리즘.
 팰린드롬 알고리즘.
 팰린드롬이라면 true, 아니라면 false
 */

public class Palindrome {
    public static boolean isPalindrome(String s){
        int N = s.length();
        for(int i=0; i<N/2;i++){
            if (s.charAt(i) != s.charAt(N-i-1)){
                return false;
            }
        }
        return true;
    }

    // 테스트 클라이언트
    public static void main(String[] args) {
        System.out.println(isPalindrome("abba"));
        System.out.println(isPalindrome("stay"));
    }
}
