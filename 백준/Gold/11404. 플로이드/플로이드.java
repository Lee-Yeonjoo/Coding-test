import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//플로이드 워셜 - 주의) 최댓값으로 초기화할 때 MAX_VALUE로 하면 안된다! 나올 수 있는 비용의 최대보다 적당히 큰 수로 초기화
public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        StringTokenizer st;
        int[][] busCost = new int[n][n];
        //자기 자신은 0으로, 나머지는 큰 수로 초기화
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    busCost[i][j] = 0;
                } else busCost[i][j] = 10000000;  //Integer.MAX_VALUE로 초기화하면 더했을 때 오버플로우되어 음수값이 나와서 min() 연산할 때 의도대로 안나온다.
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken());

            busCost[a][b] = Math.min(busCost[a][b], c);  //같은 노선의 버스가 있을 수 있으므로 더 적은 비용의 버스로 갱신
        }

        //플로이드 워셜
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    busCost[i][j] = Math.min(busCost[i][j], busCost[i][k] + busCost[k][j]);
                }
            }
        }

        //출력할 게 많으면 StringBuilder 사용
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                //갈 수 없는 곳은 0으로 출력!!
                if (busCost[i][j] == 10000000) {
                    sb.append("0").append(" ");
                    continue;
                }
                sb.append(busCost[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}
