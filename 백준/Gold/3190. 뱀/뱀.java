import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int x = 0, y = 0;
    
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());

        boolean[][] isApple = new boolean[N][N];
        StringTokenizer st;
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            isApple[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1] = true;
        }

        //자바의 Point타입을 이용
        Queue<Point> snake = new LinkedList<>();  //큐에 뱀이 차지한 좌표를 저장 
        snake.offer(new Point(0, 0));
        double direction = 0;  //뱀의 진행 방향 저장 - 각도로 저장
        int time = 0;  //시간 저장

        int nx, ny, dx = 0, dy = 0;
        int L = Integer.parseInt(br.readLine());
        //입력 방향에 대해 반복
        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken());
            String S = st.nextToken();

            //진행 좌표 구하기
            dx = -1 * (int) Math.sin(Math.toRadians(direction));
            dy = (int) Math.cos(Math.toRadians(direction));


            //시간이 X초가 될 때까지 반복
            while (time < X) {

                nx = x + dx;
                ny = y + dy;

                //큐에 추가하기 전에 몸에 닿는지 검사 먼저
                if (snake.contains(new Point(nx, ny))) {
                    System.out.println(time + 1);
                    return;
                }

                //만약 벽에 닿은 경우 종료
                if ((nx < 0 || nx >= N) || (ny < 0 || ny >= N)) {
                    System.out.println(time + 1);
                    return;
                }

                //큐에 새로 이동한 좌표를 추가
                snake.add(new Point(nx, ny));

                //이동한 곳에 사과가 없으면 꼬리 제거
                if (!isApple[nx][ny]) {
                    snake.poll();
                }
                else isApple[nx][ny] = false;  //사과가 있으면 치워야한다!

                time++;  //시간 증가
                //이전 좌표 기록
                x = nx;
                y = ny;
            }

            //방향 전환
            //오른쪽으로 회전
            if (S.equals("D")) {
                direction = (direction + 270) % 360;
            } else direction = (direction + 90) % 360;  //왼쪽으로 회전
        }

        //회전 없이
        dx = -1 * (int) Math.sin(Math.toRadians(direction));
        dy = (int) Math.cos(Math.toRadians(direction));
        while (true) {
            nx = x + dx;
            ny = y + dy;

            //큐에 추가하기 전에 몸에 닿는지 검사 먼저
            if (snake.contains(new Point(nx, ny))) {
                System.out.println(time + 1);
                return;
            }

            //만약 벽에 닿은 경우 종료
            if ((nx < 0 || nx >= N) || (ny < 0 || ny >= N)) {
                System.out.println(time + 1);
                return;
            }

            snake.add(new Point(nx, ny));

            //이동한 곳에 사과가 없으면 꼬리 제거
            if (!isApple[nx][ny]) {
                snake.poll();
            }

            time++;  //시간 증가
            //이전 좌표 기록
            x = nx;
            y = ny;
        }
    }
}
