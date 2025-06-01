import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//DP 근데 BFS도 visited를 2차원으로 설정하면 가능한 듯? 볼륨차이값과 가능 볼륨에 대한 visited
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] volumes = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            volumes[i] = Integer.parseInt(st.nextToken());
        }
        boolean[][] dp = new boolean[N][M + 1];  //행은 각 노래의 볼륨 차이, 열은 그 결과 가능한 볼륨
        if (S + volumes[0] <= M) {
            dp[0][S + volumes[0]] = true;
        }
        if (S - volumes[0] >= 0) {
            dp[0][S - volumes[0]] = true;
        }

        boolean isPossible;
        for (int i = 1; i < N; i++) {
            isPossible = false;
            for (int j = 0; j <= M; j++) {
                if (dp[i - 1][j]) {  //이전 행에서의 가능한 볼륨이 있다면
                    isPossible = true;
                    //i번째 노래의 볼륨을 더해서 가능한 경우
                    if (j + volumes[i] <= M) {
                        dp[i][j + volumes[i]] = true;
                    }
                    //빼서 가능한 경우
                    if (j - volumes[i] >= 0) {
                        dp[i][j - volumes[i]] = true;
                    }
                }
            }
            //가능한 볼륨이 아예 없는 경우 -1 출력 후 종료
            if (!isPossible) {
                System.out.println(-1);
                System.exit(0);
            }
        }

        //뒤에서부터 가능 볼륨을 찾아서 바로 출력 후 종료
        for (int j = M; j >= 0; j--) {
            if (dp[N - 1][j]) {
                System.out.println(j);
                System.exit(0);
            }
        }
        System.out.println(-1);  //이걸 꼭 넣어야 함!! 반례) 1 3 5  (다음줄) 4
    }
}
