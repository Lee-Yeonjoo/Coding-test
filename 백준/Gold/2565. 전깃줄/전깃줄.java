import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//DP - LIS 풀이
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int[][] lines = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            lines[i][0] = Integer.parseInt(st.nextToken());
            lines[i][1] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(lines, (o1, o2) -> Integer.compare(o1[0], o2[0]));  //A값 기준으로 오름차순 정렬

        int[] dp = new int[N];
        Arrays.fill(dp, 1);  //자기자신은 무조건 포함되니까 1로 초기화
        int installCount = 0;
        //LIS
        for (int i = 1; i < N; i++) {
            for (int j = 0; j <= i - 1; j++) {
                if (lines[i][1] > lines[j][1]) {  //설치 가능
                    dp[i] = Math.max(dp[i], dp[j] + 1);  //앞 전깃줄 중에 설치 가능한 것들 중 가장 큰 값으로 갱신
                }
            }
            installCount = Math.max(installCount, dp[i]);
        }
        System.out.println(N - installCount);  //전체에서 설치 가능 개수 빼기 = 제거할 개수 
    }
}
