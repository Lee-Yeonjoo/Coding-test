import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//브루트포스(6번 다 해보는 것) + 그리디(각 각에 대해)
public class Main {
    static int sum = 0;
    static int N;
    static int[][] dice;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        dice = new int[N][6];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 6; j++) {
                dice[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //처음 1번 주사위의 아랫면 = i
        for (int i = 0; i < 6; i++) {
            findMaxSum(i);
        }
        System.out.println(sum);
    }

    //옆면의 최댓값의 합 구하기
    static void findMaxSum(int start) {
        int bottom = start;
        int tempSum = 0;
        for (int i = 0; i < N; i++) {
            int max = 0;
            int top = changeNum(bottom);

            //옆면 중 가장 큰 주사위값 구하기
            for (int j = 0; j < 6; j++) {
                 if (j != top && j != bottom) {
                    max = Math.max(max, dice[i][j]);
                }
            }

            //다음 아랫면 번호 구하기
            for (int j = 0; j < 6; j++) {
                if (i == N - 1) break;  //맨 마지막 주사위는 안구함
                if (dice[i][top] == dice[i + 1][j]) {
                    bottom = j;
                }
            }
            tempSum += max;  //i주사위의 옆면 중 가장 큰 값 더하기
        }
        sum = Math.max(sum, tempSum);  //합 중에 최댓값 구하기
    }

    //마주보는 면으로 변환하는 함수
    static int changeNum(int num) {
        if (num == 0) {
            return 5;
        } else if (num == 1) {
            return 3;
        } else if (num == 2) {
            return 4;
        } else if (num == 3) {
            return 1;
        } else if (num == 4) {
            return 2;
        } else {
            return 0;
        }
    }
}