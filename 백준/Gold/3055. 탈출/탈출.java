import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.List;

//bfs, 홍수가 여러 개일 수 있다 주의!
public class Main {
    static int R, C;
    static char[][] map;
    static class Node {
        int x;
        int y;
        int time;  //걸리는 시간
        boolean isFlood;  //홍수 여부

        public Node(int x, int y, int time, boolean isFlood) {
            this.x = x;
            this.y = y;
            this.time = time;
            this.isFlood = isFlood;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        int x = 0, y = 0;
        //홍수는 여러 개일 수 있다..
        List<Point> flood = new ArrayList<>();

        for (int i = 0; i < R; i++) {
            String input = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = input.charAt(j);
                if (map[i][j] == 'S') {  //S와 *의 처음 좌표 찾기
                    x = i;
                    y = j;
                } else if (map[i][j] == '*') {
                    flood.add(new Point(i, j));
                }
            }
        }

        bfs(x, y, flood);  //bfs 탐색
    }

    static void bfs(int x, int y, List<Point> flood) {
        Queue<Node> q = new LinkedList<>();
        boolean[][] visited = new boolean[R][C];  //고슴도치의 방문 여부 표시
        //상하좌우 방향 벡터
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        //System.out.println(x + " " + y + " " + floodX + " " + floodY);

        //홍수들을 먼저 큐에 넣는다.
        for (Point point : flood) {
            q.add(new Node(point.x, point.y, 0, true));
        }
        q.add(new Node(x, y, 0, false));  //고슴도치 시작위치도 큐에 삽입
        visited[x][y] = true;

        while (!q.isEmpty()) {
            Node currNode = q.poll();

            //고슴도치가 비버집에 도착한 경우
            if (map[currNode.x][currNode.y] == 'D' && !currNode.isFlood) {
                System.out.println(currNode.time);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nx = currNode.x + dx[i];
                int ny = currNode.y + dy[i];

                if (nx < 0 || nx >= R || ny < 0 || ny >= C) {
                    continue;
                }

                //바위면 이동 불가
                if (map[nx][ny] == 'X') {
                    continue;
                }

                //고슴도치보다 홍수를 먼저 처리한다. 큐에 먼저 삽입(문제에서 물이 찰 예정인 위치는 고슴도치가 갈 수 없다고 했기 때문)
                //홍수인 경우 이미 방문한 * 자리가 아니고, 비버집이 아니라면 이동 가능
                if (currNode.isFlood && map[nx][ny] != '*' && map[nx][ny] != 'D') {
                    q.add(new Node(nx, ny, 0, true));
                    map[nx][ny] = '*';  //지도에 *로 바꿈으로써 방문 표시
                }

                //고슴도치인 경우, 아직 방문하지 않았고, 홍수가 없는 곳이면 이동 가능
                if (!currNode.isFlood && !visited[nx][ny] && map[nx][ny] != '*') {
                    q.add(new Node(nx, ny, currNode.time + 1, false));  //time을 1 증가시킴
                    visited[nx][ny] = true;
                }
            }
        }
        System.out.println("KAKTUS");
    }
}
