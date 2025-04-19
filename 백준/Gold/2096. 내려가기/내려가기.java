import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

//DP
public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        StringTokenizer st;
        int[][] scores = new int[N][3];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            scores[i][0] = Integer.parseInt(st.nextToken());
            scores[i][1] = Integer.parseInt(st.nextToken());
            scores[i][2] = Integer.parseInt(st.nextToken());
        }

        int[][] dpMax = new int[N][3];
        int[][] dpMin = new int[N][3];

        //dp 초기값 저장
        for (int i = 0; i < 3; i++) {
            dpMin[0][i] = scores[0][i];
            dpMax[0][i] = scores[0][i];
        }

        //바텀업 방식
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < 3; j++) {

                //이전 행의 j열 값은 무조건 존재하므로 저장
                dpMin[i][j] = dpMin[i - 1][j];
                dpMax[i][j] = dpMax[i - 1][j];

                //이전 행의 j-1열이 존재한다면 갱신
                if (j - 1 >= 0) {
                    dpMin[i][j] = Math.min(dpMin[i][j], dpMin[i - 1][j - 1]);
                    dpMax[i][j] = Math.max(dpMax[i][j], dpMax[i - 1][j - 1]);
                }

                //이전 행의 j+1열이 존재한다면 갱신
                if (j + 1 < 3) {
                    dpMin[i][j] = Math.min(dpMin[i][j], dpMin[i - 1][j + 1]);
                    dpMax[i][j] = Math.max(dpMax[i][j], dpMax[i - 1][j + 1]);
                }

                //자기 점수 더하기
                dpMin[i][j] += scores[i][j];
                dpMax[i][j] += scores[i][j];
            }
        }

        //마지막 행의 최대, 최소값 구하기
        int maxAnswer = Arrays.stream(dpMax[N - 1]).max().getAsInt();
        int minAnswer = Arrays.stream(dpMin[N - 1]).min().getAsInt();
        System.out.println(maxAnswer + " " + minAnswer);
    }
}
