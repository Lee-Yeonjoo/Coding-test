import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//브루트 포스
public class Main {
    static int N;
    static char[][] corridor;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        corridor = new char[N][N];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                corridor[i][j] = st.nextToken().charAt(0);
            }
        }
        permutation(0);
        System.out.println("NO");  //모든 장애물의 경우의 수를 해봐도 감시 못 피하는 경우
    }

    //최대 36칸 중 3칸 선택 -> 순열
    static boolean isStudent;  //학생을 발견한 경우 true
    static void permutation(int depth) {
        //순열 완성 -> 학생 탐색
        if (depth == 3) {
            isStudent = false;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    //선생님 칸에서 탐색
                    if (corridor[i][j] == 'T') {
                        searchStudent(i, j);
                    }
                }
            }
            //모든 T에 대한 탐색이 끝났을 때 S를 보지 못한 경우 YES 출력하고 프로그램 종료
            if (!isStudent) {
                System.out.println("YES");
                System.exit(0);
            }
            return;
        }

        //(0,0)부터 차례대로 장애물 설치할 칸 선택
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (corridor[i][j] == 'X') {  //T,S 아니고, 장애물이 없는 칸인 경우
                    corridor[i][j] = 'O';  //장애물 생성
                    permutation(depth + 1);
                    corridor[i][j] = 'X';  //백트래킹
                }
            }
        }
    }

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    //T에서 탐색하는 함수
    static void searchStudent(int x, int y) {
        //상하좌우 탐색
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            //범위 끝까지 탐색
            while (nx >= 0 && nx < N && ny >= 0 && ny < N) {
                //장애물을 만났으면 탐색 그만
                if (corridor[nx][ny] == 'O') break;

                //학생 만난 경우
                if (corridor[nx][ny] == 'S') isStudent = true;

                //같은 방향으로 한칸씩 이동
                nx += dx[i];
                ny += dy[i];
            }
        }
    }
}
