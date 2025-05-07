import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static class Dust {  //먼지의 좌표와 양
        int x;
        int y;
        int amount;

        public Dust(int x, int y, int amount) {
            this.x = x;
            this.y = y;
            this.amount = amount;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        int dust = 0;  //먼지의 총량
        int[][] room = new int[R][C];
        int x1 = 0, y1 = 0, x2 = 0, y2 = 0;
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                room[i][j] = Integer.parseInt(st.nextToken());
                if (room[i][j] == -1) {  //공기청정기의 좌표2
                    x2 = i;
                    y2 = j;
                } else dust += room[i][j];
            }
        }
        //공기청정기의 좌표1
        x1 = x2 - 1;
        y1 = y2;

        //먼지 확산
        for (int t = 0; t < T; t++) {
            Queue<Dust> dustList = new LinkedList<>();

            //먼지가 있는 칸을 큐에 저장 -> 원본 먼지량을 저장하기 위해
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (room[i][j] > 4) {
                        dustList.add(new Dust(i, j, room[i][j]));
                    }
                }
            }

            //큐가 빌 때까지 먼지 확산
            while (!dustList.isEmpty()) {
                Dust dust1 = dustList.poll();

                int d = dust1.amount / 5;

                //원본 먼지의 상하좌우로 먼지를 확산시킨다.
                for (int k = 0; k < 4; k++) {
                    int nx = dust1.x + dx[k];
                    int ny = dust1.y + dy[k];

                    if (nx < 0 || nx >= R || ny < 0 || ny >= C) {
                        continue;
                    }

                    //공기청정기 위치면 무시
                    if (room[nx][ny] == -1) {
                        continue;
                    }

                    room[nx][ny] += d;
                    room[dust1.x][dust1.y] -= d;
                }
            }

            //공기 청정기 코드
            dust -= room[x1 - 1][y1];  //먼지 총량에서 공기청정기로 없어진 먼지 빼기
            //위쪽 공기청정기에 의한 이동
            for (int i = x1 - 2; i >= 0; i--) {
                room[i+1][0] = room[i][0];
            }
            for (int j = 1; j < C; j++) {
                room[0][j - 1] = room[0][j];
            }
            for (int i = 1; i <= x1; i++) {
                room[i - 1][C - 1] = room[i][C - 1];
            }
            for (int j = C - 2; j > y1; j--) {
                room[x1][j + 1] = room[x1][j];
            }
            room[x1][y1 + 1] = 0;

            dust -= room[x2 + 1][y2];
            //아래쪽 공기청정기에 의한 이동
            for (int i = x2 + 2; i < R; i++) {
                room[i - 1][0] = room[i][0];
            }
            for (int j = 1; j < C; j++) {
                room[R - 1][j - 1] = room[R - 1][j];
            }
            for (int i = R - 2; i >= x2; i--) {
                room[i + 1][C - 1] = room[i][C - 1];
            }
            for (int j = C - 2; j > y2; j--) {
                room[x2][j + 1] = room[x2][j];
            }
            room[x2][y2 + 1] = 0;
        }

        System.out.println(dust);
    }
}
