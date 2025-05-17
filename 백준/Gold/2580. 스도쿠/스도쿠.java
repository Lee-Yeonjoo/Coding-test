import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//완전탐색, 백트래킹
public class Main {
    static int[][] sudoku;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        sudoku = new int[9][9];

        for (int i = 0; i < 9; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                sudoku[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        search(0, 0);
    }

    static void search(int r, int c) {

        //열이 끝났으므로 다음 행으로 진행
        if (c == 9) {
            search(r + 1, 0);
            return;
        }

        //행이 끝났으므로 최종 출력
        if (r == 9) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    sb.append(sudoku[i][j]).append(" ");
                }
                sb.append("\n");
            }
            System.out.println(sb);
            System.exit(0);  //다른 케이스 출력 안되도록 프로그램 종료
        }

        //탐색한 위치가 0인 경우 - 스도쿠 조건에 맞는 수를 찾고 탐색 진행
        if (sudoku[r][c] == 0) {
            for (int i = 1; i <= 9; i++) {
                if (check(r, c, i)) {  //(r,c) 위치에 i가 스도쿠 조건에 만족하는지를 체크
                    sudoku[r][c] = i;
                    search(r, c + 1);
                }
            }
            sudoku[r][c] = 0;  //만족하는 값이 없을 경우 백트래킹!!
            return;
        }

        search(r, c + 1);  //위의 것들 다 해당 안되는 평범한 경우 - 그냥 다음 열로 탐색 진행
    }

    static boolean check(int r, int c, int k) {
        //가로줄에 대해 검사
        for (int i = 0; i < 9; i++) {
            if (sudoku[r][i] == k) {
                return false;
            }
        }

        //세로줄에 대해 검사
        for (int i = 0; i < 9; i++) {
            if (sudoku[i][c] == k) {
                return false;
            }
        }

        //3x3칸에 대해 검사
        //3x3의 맨 왼쪽 윗칸 좌표 구하기
        int x = (r / 3) * 3;
        int y = (c / 3) * 3;
        for (int i = x; i < x + 3; i++) {
            for (int j = y; j < y + 3; j++) {
                if (sudoku[i][j] == k) {
                    return false;
                }
            }
        }

        return true;
    }
}
