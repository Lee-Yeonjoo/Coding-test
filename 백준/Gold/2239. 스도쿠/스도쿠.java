import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//백트래킹
public class Main {
    static int[][] sudoku;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sudoku = new int[9][9];
        for (int i = 0; i < 9; i++) {
            String input = br.readLine();
            for (int j = 0; j < 9; j++) {
                sudoku[i][j] = Integer.parseInt(String.valueOf(input.charAt(j)));
            }
        }
        dfs(0, 0);
    }

    static void dfs(int x, int y) {
        if (y == 9) {
            dfs(x + 1, 0);
            return;  //여기서 꼭 종료해줘야 인덱스 에러가 나지 않는다...ㅜ
        }

        if (x == 9) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    sb.append(sudoku[i][j]);
                }
                sb.append("\n");
            }
            System.out.println(sb);
            System.exit(0);
        }

        if (sudoku[x][y] == 0) {
            for (int i = 1; i <= 9; i++) {
                if (isValid(x, y, i)) {
                    sudoku[x][y] = i;
                    dfs(x, y + 1);
                }
            }
            sudoku[x][y] = 0;  //백트래킹. 넣을 수 있는 숫자가 없으므로 이전 칸의 값부터 잘못된거라 이전 칸으로 돌아가야 한다.
            return;  //여기서 꼭 함수를 끝내줘야 다음칸으로 안 넘어가고 이전 칸의 호출문으로 복귀함!
        }
        dfs(x, y + 1);  //이미 채워진 칸은 바로 다음 칸으로 패스
    }

    static boolean isValid(int x, int y, int num) {
        //가로줄
        for (int i = 0; i < 9; i++) {
            if (sudoku[x][i] == num) {
                return false;
            }
        }

        //세로줄
        for (int i = 0; i < 9; i++) {
            if (sudoku[i][y] == num) {
                return false;
            }
        }

        //3*3칸
        x = (x / 3) * 3;
        y = (y / 3) * 3;
        for (int i = x; i < x + 3; i++) {
            for (int j = y; j < y + 3; j++) {
                if (sudoku[i][j] == num) {
                    return false;
                }
            }
        }

        return true;
    }
}
