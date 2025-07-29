import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//브루트 포스 - 조합
public class Main {
    static int N;
    static int minValue = Integer.MAX_VALUE;
    static int sum = 0;
    static int[][] abilitys;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        abilitys = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                abilitys[i][j] = Integer.parseInt(st.nextToken());
                sum += abilitys[i][j];
            }
        }

        for (int i = 2; i <= N / 2; i++) {  //nC1 ~ nC(n/2)까지
            visited = new boolean[N];
            //조합
            combination(0, 0, i, 0);
        }
        System.out.println(minValue);
    }

    //조합
    static boolean[] visited;
    static void combination(int start, int depth, int r, int ability /*현재까지의 능력치*/) {
        //조합 완성
        if (depth == r) {
            //상대 팀 능력치 구하기
            int otherTeam = sum;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (visited[i] || visited[j]) {
                        otherTeam -= abilitys[i][j];
                    }
                }
            }
            //능력치 비교
            minValue = Math.min(minValue, Math.abs(ability - otherTeam));
            //System.out.println(ability + " : " + otherTeam);
            return;
        }

        //다음 조합
        for (int i = start; i < N; i++) {
            visited[i] = true;  //방문 표시

            //새 요소 추가 시 추가될 능력치 계산
            int newAbility = 0;
            for (int j = 0; j < N; j++) {
                if (visited[j]) {
                    newAbility += abilitys[i][j];
                    newAbility += abilitys[j][i];
                }
            }
            combination(i + 1, depth + 1, r, ability + newAbility);
            visited[i] = false;  //백트래킹
        }
    }
}
