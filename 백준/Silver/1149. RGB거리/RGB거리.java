import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//DP
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int[][] cost = new int[N][3];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i < N; i++) {
            for (int j = 0; j < 3; j++) {
                int minCost = Integer.MAX_VALUE;
                for (int k = 0; k < 3; k++) {
                    if (j != k) {
                        minCost = Math.min(minCost, cost[i - 1][k]);
                    }
                }
                cost[i][j] += minCost;
            }
        }
        System.out.println(Math.min(cost[N - 1][0], Math.min(cost[N - 1][1], cost[N - 1][2])));
    }
}
