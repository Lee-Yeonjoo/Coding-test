import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//브루트포스
public class Main {

    static int max_candy = 0;
    static char[][] board;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        board = new char[N][N];

        for (int i = 0; i < N; i++) {
            String candy = br.readLine();
            for (int j = 0; j < N; j++) {
                board[i][j] = candy.charAt(j);
            }
        }

        //각 행 안에서 사탕 바꾸는 완전 탐색
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N - 1; j++) {

                swapRow(i, j);  //문자 바꾸기
                countCandy();  //연속된 사탕의 최대 개수 세기
                swapRow(i, j);  //deep copy 하지 말고 결과 구한 후 복구(더 효율적)
            }
        }

        //열에 대해 사탕 바꾸는 완전 탐색
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N - 1; j++) {

                swapCol(i, j);  //문자 바꾸기
                countCandy();
                swapCol(i, j);
            }
        }

        System.out.println(max_candy);
        br.close();
    }

    //연속된 사탕의 최대 개수 세기
    static void countCandy() {

        for (int i = 0; i < N; i++) {
            //다음 줄로 넘어갈 때마다 temp값 초기화 
            int tempRow = 1;
            int tempCol = 1;
            for (int j = 1; j < N; j++) {
                //가로줄에서 카운트
                //만약 이전 사탕과 같다면 개수 세기
                if (board[i][j - 1] == board[i][j]) {
                    tempRow++;
                } else { //이전 사탕과 달라질 경우 세놓았던 개수에 대해 max값인지 검사 후, tempRow 초기화
                    max_candy = Math.max(max_candy, tempRow);
                    tempRow = 1;
                }

                //세로줄에서 카운트
                if (board[j - 1][i] == board[j][i]) {
                    tempCol++;
                } else {
                    max_candy = Math.max(max_candy, tempCol);
                    tempCol = 1;
                }
            }

            //한 줄이 다 같을 경우 max검사를 못하므로 다음 코드 추가
            max_candy = Math.max(max_candy, tempRow);
            max_candy = Math.max(max_candy, tempCol);
        }
    }

    static void swapRow(int i, int j) {
        char temp = board[i][j];
        board[i][j] = board[i][j + 1];
        board[i][j + 1] = temp;
    }

    static void swapCol(int i, int j) {
        char temp = board[j][i];
        board[j][i] = board[j+1][i];
        board[j+1][i] = temp;
    }
}
