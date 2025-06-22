import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//구현, 왜 그리디?
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();
        String T = br.readLine();

        while (!T.isEmpty()) {
            char last = T.charAt(T.length() - 1);  //마지막 문자 추출
            T = T.substring(0, T.length() - 1);  //마지막 문자 제거

            //마지막 문자가 B인 경우 뒤집기
            String temp = "";
            if (last == 'B') {
                for (int i = T.length() - 1; i >= 0; i--) {
                    temp += T.charAt(i);
                }
            } else temp = T;

            //만약 연산 결과가 S와 동일하다면 1 출력 후 종료
            if (temp.equals(S)) {
                System.out.println(1);
                System.exit(0);
            }
            T = temp;  //다시 T에 저장
        }
        System.out.println(0);  //불가능한 경우 0 출력
    }
}
