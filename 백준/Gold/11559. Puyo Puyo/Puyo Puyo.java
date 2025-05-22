import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//DFS
public class Main {
    static char[][] field;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[][] visited;
    static int puyoCount;
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
                        dfs(i, j);
                        //뿌요가 4개 이상이면 터뜨리기
                        if (puyoCount >= 4) {
                            isChain = true;
                            puyoPop(i, j, field[i][j]);
                        }
                    }
                }
            }
           /* System.out.println("=====================================");
            System.out.println("이동 전 ");
            for (char[] chars : field) {
                for (char aChar : chars) {
                    System.out.print(aChar);
                }
                System.out.println();
            }*/
            //뿌요 이동
            move();
            /*System.out.println("이동 후");
            for (char[] chars : field) {
                for (char aChar : chars) {
                    System.out.print(aChar);
                }
                System.out.println();
            }*/

            //연쇄 반응이 더 이상 일어나지 않으므로 break
            if (!isChain) break;
            chainCount++;
        }
        System.out.println(chainCount);
    }

    //빈 자리 없애기 뿌요 이동
    static void move() {
        for (int j = 0; j < 6; j++) {
            for (int i = 11; i >= 0; i--) {
                int pt = i - 1;
                if (field[i][j] == '.') {
                    while (pt >= 0 && field[pt][j] == '.') {
                        pt--;
                    }
                    if (pt >= 0) {
                        field[i][j] = field[pt][j];
                        field[pt][j] = '.';
                    }
                }
            }
        }
    }

    //한번 더 dfs..
    static void puyoPop(int x, int y, char puyo) {
        visited[x][y] = 2;
        field[x][y] = '.';

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= 12 || ny < 0 || ny >= 6) {
                continue;
            }

            //같은 알파벳이 아니면 패스
            if (field[nx][ny] != puyo) {
                continue;
            }

            if (visited[nx][ny] == 1) {
                puyoPop(nx, ny, puyo);
            }
        }
    }

    static void dfs(int x, int y) {
        visited[x][y] = 1;

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
