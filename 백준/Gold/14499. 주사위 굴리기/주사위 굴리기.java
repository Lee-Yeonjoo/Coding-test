import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M, x, y, K;
    static int[] dice;

    //동서북남
    static int[] dx = {0, 0, 0, -1, 1};
    static int[] dy = {0, 1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //주사위 배열 초기화
        dice = new int[7];  //1 = 윗면, 6 = 바닥면
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            int direction = Integer.parseInt(st.nextToken());

            //이동 좌표 구하기
            int nx = x + dx[direction];
            int ny = y + dy[direction];
            //이동할 위치의 범위 체크
            if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
                continue;
            }
            rollDice(direction);  //주사위 굴리기 
            
            //이동한 칸의 수가 0인 경우
            if (map[nx][ny] == 0) {
                map[nx][ny] = dice[6];  //바닥면의 수를 칸에 복사
            } else {  //0이 아닌 경우
                dice[6] = map[nx][ny];  //칸의 수를 바닥면에 복사
                map[nx][ny] = 0;
            }

            System.out.println(dice[1]);  //주사위 윗면 출력
            x = nx;
            y = ny;
        }

    }

    //주사위 굴리기
    static void rollDice(int direction) {
        int temp = dice[1];
        //남쪽
        if (direction == 4) {
            dice[1] = dice[2];
            dice[2] = dice[6];
            dice[6] = dice[5];
            dice[5] = temp;
        } else if (direction == 3) {  //북쪽
            dice[1] = dice[5];
            dice[5] = dice[6];
            dice[6] = dice[2];
            dice[2] = temp;
        } else if (direction == 2) {  //서쪽
            dice[1] = dice[3];
            dice[3] = dice[6];
            dice[6] = dice[4];
            dice[4] = temp;
        } else {  //동쪽
            dice[1] = dice[4];
            dice[4] = dice[6];
            dice[6] = dice[3];
            dice[3] = temp;
        }
    }
}
