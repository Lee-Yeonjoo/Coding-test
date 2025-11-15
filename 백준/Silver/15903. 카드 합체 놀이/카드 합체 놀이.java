import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//그리디
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        PriorityQueue<Long> pq = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            pq.add(Long.parseLong(st.nextToken()));
        }

        //카드 합치기
        for (int i = 0; i < m; i++) {
            long sum = pq.poll() + pq.poll();
            pq.add(sum);
            pq.add(sum);
        }

        //최종 점수
        long answer = 0;
        for (int i = 0; i < n; i++) {
            answer += pq.poll();
        }
        System.out.println(answer);
    }
}
