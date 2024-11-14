import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class H6287 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[] A = new int[N];
        int[] AToB = new int[N - 1];
        int[] B = new int[N];
        int[] BToA = new int[N - 1];

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());

            A[i] = Integer.parseInt(st.nextToken());
            B[i] = Integer.parseInt(st.nextToken());
            AToB[i] = Integer.parseInt(st.nextToken());
            BToA[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        A[N-1] = Integer.parseInt(st.nextToken());
        B[N-1] = Integer.parseInt(st.nextToken());

        int[][] dp = new int[2][N];   //첫번째 행은 A, 두번째 행은 B
        dp[0][0] = A[0];
        dp[1][0] = B[0];

        for (int i = 1; i < N; i++) {
            dp[0][i] = A[i] + Math.min(dp[0][i-1], dp[1][i-1] + BToA[i-1]);
            dp[1][i] = B[i] + Math.min(dp[0][i - 1] + AToB[i - 1], dp[1][i - 1]);
        }

        StringBuilder sb = new StringBuilder();
        sb.append(Math.min(dp[0][N - 1], dp[1][N - 1]));
        System.out.println(sb);
    }
}
