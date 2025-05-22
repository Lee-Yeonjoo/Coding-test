import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

//DFS
public class Main {
    static char[][] field;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[][] visited;
    static int puyoCount;
    static ArrayList<Point> popPoint;  //뿌요가 4개 이상일 때 터지는 위치를 기록
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        field = new char[12][6];
        for (int i = 0; i < 12; i++) {
            String input = br.readLine();
            for (int j = 0; j < 6; j++) {
                field[i][j] = input.charAt(j);
            }
        }

        boolean isChain;
        int chainCount = 0;
        while (true) {
            isChain = false;
            visited = new int[12][6];
            for (int i = 0; i < 12; i++) {
                for (int j = 0; j < 6; j++) {
                    if (field[i][j] != '.') {
                        puyoCount = 1;
                        popPoint = new ArrayList<>();
                        dfs(i, j);
                        //뿌요가 4개 이상이면 터뜨리기
                        if (puyoCount >= 4) {
                            isChain = true;
                            puyoPop();
                        }
                    }
                }
            }
          
            //뿌요 이동
            move();
            
            //연쇄 반응이 더 이상 일어나지 않으므로 break
            if (!isChain) break;
            chainCount++;
        }
        System.out.println(chainCount);
    }

    //빈 자리 없애기 뿌요 이동
    static void move() {
        //각 열마다 행을 이동하면서 뿌요를 가리킨다.
        for (int j = 0; j < 6; j++) {
            for (int i = 11; i >= 0; i--) {
                int pt = i - 1;  //i보다 하나 위의 행부터 시작
                if (field[i][j] == '.') {  //만약 i위치의 자리가 '.'라면
                    //'.'이 아닐 때까지 행을 하나 위에꺼를 가리키도록 해서 뿌요가 위치한 행을 찾는다.
                    while (pt >= 0 && field[pt][j] == '.') {
                        pt--;
                    }
                    //뿌요를 찾은 경우, 뿌요의 값을 옮긴다.
                    if (pt >= 0) {  //이거 등호 안붙여서 틀림...ㅜㅜ
                        field[i][j] = field[pt][j];
                        field[pt][j] = '.';
                    }
                }
            }
        }
    }

    //popPoint에 저장한 위치의 뿌요를 삭제하는 함수
    static void puyoPop() {
        //리스트에 저장한 좌표를 모두 '.'로 변환
        for (Point point : popPoint) {
            field[point.x][point.y] = '.';
        }
    }

    static void dfs(int x, int y) {
        visited[x][y] = 1;
        popPoint.add(new Point(x, y));  //dfs로 방문하는 좌표를 모두 리스트에 추가

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= 12 || ny < 0 || ny >= 6) {
                continue;
            }

            //같은 알파벳이 아니면 패스
            if (field[nx][ny] != field[x][y]) {
                continue;
            }

            if (visited[nx][ny] == 0) {
                puyoCount++;
                dfs(nx, ny);
            }
        }
    }
}
