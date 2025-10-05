import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//DP - 답 봄
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        int[] count = new int[N + 1];
        int[] dp = new int[N + 1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            count[b]++;  //각 과목의 선수과목이 몇 개인지 셈
        }

        Queue<Integer> q = new ArrayDeque<>();

        //선수과목 없는 과목 -> 1학기에 바로 듣기
        for (int i = 1; i <= N; i++) {
            if (count[i] == 0) {  //선수과목이 0개이므로 큐에 넣기
                q.add(i);  //큐에 넣는 것은 이 과목의 다음 과목의 학기 수를 구할 수 있기 때문 -> 현재 과목의 선수과목이 없으니까!
                dp[i] = 1;
            }
        }

        while (!q.isEmpty()) {
            int curr = q.poll();  //선수과목이 없는 과목 꺼내기

            for (int nextSubject : graph.get(curr)) {  //curr과목의 다음 과목들의 학기 수를 구할 수 있게 됨
                count[nextSubject]--;  //선수과목에 대해 처리했으므로 하나 줄어듦
                dp[nextSubject] = Math.max(dp[nextSubject], dp[curr] + 1);  //선수과목의 학기 수에 1을 더하면 됨
                if (count[nextSubject] == 0) {
                    q.add(nextSubject);  //선수과목에 대해 다 처리해서, 없게 되면 큐에 삽입
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < N + 1; i++) {
            sb.append(dp[i]).append(" ");
        }
        System.out.println(sb);
    }
}
