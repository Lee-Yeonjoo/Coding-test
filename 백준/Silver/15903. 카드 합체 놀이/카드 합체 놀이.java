import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//그리디 
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        long[] cards = new long[n];  //int범위를 넘음
        for (int i = 0; i < n; i++) {
            cards[i] = Long.parseLong(st.nextToken());
        }

        //카드 합치기
        for (int i = 0; i < m; i++) {
            //오름차순 정렬
            Arrays.sort(cards);

            long sum = cards[0] + cards[1];
            cards[0] = sum;
            cards[1] = sum;
        }

        //최종 점수
        long answer = 0;
        for (int i = 0; i < n; i++) {
            answer += cards[i];
        }
        System.out.println(answer);
    }
}
