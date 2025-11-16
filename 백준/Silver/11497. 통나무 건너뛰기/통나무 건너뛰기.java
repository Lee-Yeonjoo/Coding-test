import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int N;
        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            PriorityQueue<Integer> pq = new PriorityQueue<>();
            for (int i = 0; i < N; i++) {
                pq.add(Integer.parseInt(st.nextToken()));
            }

            //우선순위큐의 작은 수부터 양 끝단을 채우기
            int[] logs = new int[N];
            for (int i = 0; i < N / 2; i++) {
                logs[i] = pq.poll();
                logs[N - 1 - i] = pq.poll();
            }

            //요소가 홀수 개일 때, 남은 수 넣기
            if (!pq.isEmpty()) {
                logs[N / 2] = pq.poll();
            }
            
            //인접한 통나무 간 높이의 최대 차이 구하기
            int maxDiff = Math.abs(logs[N - 1] - logs[0]);  //차이니까 절댓값 빼먹으면 안됨 ㅜㅜ
            for (int i = 0; i < N - 1; i++) {
                maxDiff = Math.max(maxDiff, Math.abs(logs[i + 1] - logs[i]));
            }
            sb.append(maxDiff).append("\n");
        }
        System.out.println(sb);
    }
}
