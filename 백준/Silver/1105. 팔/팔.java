import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String L = st.nextToken();
        String R = st.nextToken();

        int answer = 0;
        //둘의 자릿수가 같다면
        if (L.length() == R.length()) {
            //앞에서부터 L과 R 모두 8이라면 카운트
            for (int i = 0; i < L.length(); i++) {
                if (L.charAt(i) == R.charAt(i)) {
                    if (L.charAt(i) == '8' && R.charAt(i) == '8') {
                        answer++;
                    }
                }
                 else {
                    break;
                }
            }
        }

        System.out.println(answer);
    }
}
