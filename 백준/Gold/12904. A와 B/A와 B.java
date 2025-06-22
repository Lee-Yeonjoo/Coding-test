import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//구현, 왜 그리디
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //StringBuilder를 사용하면 쉽게 문자를 제거하고, 뒤집을 수 있다.
        StringBuilder S = new StringBuilder(br.readLine());
        StringBuilder T = new StringBuilder(br.readLine());

        //T의 길이가 클 때까지만 반복 -> T의 길이는 줄어든다.
        while (T.length() > S.length()) {
            char last = T.charAt(T.length() - 1);  //마지막 문자 추출
            T.deleteCharAt(T.length() - 1);  //마지막 문자 제거

            //마지막 문자가 B인 경우 뒤집기
            if (last == 'B') {
                T.reverse();
            }
        }

        //S와 T가 동일하다면 1 출력, 아니면 0 출력
        System.out.println(T.compareTo(S) == 0? 1 : 0);
    }
}
