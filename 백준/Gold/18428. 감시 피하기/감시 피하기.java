import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

//브루트 포스 - 매번 T와 X의 위치를 찾는게 아니라 처음에 따로 빼두어서 시간 단축
public class Main {
    static int N;
    static char[][] corridor;
    //시간 단축을 위해 T랑 X 위치를 따로 기억해두기
    static List<Point> teacher = new ArrayList<>();
    static List<Point> empties = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        corridor = new char[N][N];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                corridor[i][j] = st.nextToken().charAt(0);
                //시간 단축을 위해 T, X 자리 기억
                if (corridor[i][j] == 'T') {
                    teacher.add(new Point(i, j));
                } else if (corridor[i][j] == 'X') {
                    empties.add(new Point(i, j));
                }
            }
        }
        permutation(0, 0);
        System.out.println("NO");  //모든 장애물의 경우의 수를 해봐도 감시 못 피하는 경우
    }

    //최대 36칸 중 3칸 선택 -> 순열
    static boolean isStudent;  //학생을 발견한 경우 true
    static void permutation(int depth, int start) {
        //순열 완성 -> 학생 탐색
        if (depth == 3) {
            isStudent = false;

            //teacher 위치들에 대해 탐색
            for (Point point : teacher) {
                searchStudent(point.x, point.y);
            }

            //모든 T에 대한 탐색이 끝났을 때 S를 보지 못한 경우 YES 출력하고 프로그램 종료
            if (!isStudent) {
                System.out.println("YES");
                System.exit(0);
            }
            return;
        }

        //start번째 빈칸부터 차례대로 장애물 설치할 칸 선택
        for (int i = start; i < empties.size(); i++) {
            Point empty = empties.get(i);
            corridor[empty.x][empty.y] = 'O';  //장애물 생성
            permutation(depth + 1, i + 1);
            corridor[empty.x][empty.y] = 'X';  //백트래킹
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
