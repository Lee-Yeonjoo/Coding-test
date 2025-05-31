import java.io.*;
import java.util.*;

//구현, BFS - 더 간단한 방법. S를 거리 세듯이 노드에 저장
public class Main {
    static int N, K, S, X, Y;
    static int[][] examiner;
    static ArrayList<VirusNode> virus;

    static class VirusNode {
        int num;
        int time;  //S초를 세기 위함
        int x;
        int y;

        public VirusNode(int num, int time, int x, int y) {
            this.num = num;
            this.time = time;
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        examiner = new int[N][N];
        virus = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                examiner[i][j] = Integer.parseInt(st.nextToken());
                //처음 바이러스의 좌표 저장
                if (examiner[i][j] != 0) {
                    virus.add(new VirusNode(examiner[i][j], 0, i, j));  //리스트에 바이러스 저장
                }
            }
        }
        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());

        //바이러스를 번호 기준으로 오름차순 정렬
        virus.sort((o1, o2) -> Integer.compare(o1.num, o2.num));
        bfs();
        System.out.println(examiner[X - 1][Y - 1]);  //출력을 bfs의 while문 내에서 하면 바이러스가 없는 경우에 출력이 안된다. 주의
    }

    static void bfs() {
        Queue<VirusNode> q = new LinkedList<>(virus);  //정렬한 바이러스 리스트를 큐에 삽입
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        //오름차순으로 전염
        while (!q.isEmpty()) {
            VirusNode currVirus = q.poll();

            if (currVirus.time == S) {
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nx = currVirus.x + dx[i];
                int ny = currVirus.y + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
                    continue;
                }

                if (examiner[nx][ny] == 0) {
                    q.add(new VirusNode(currVirus.num, currVirus.time + 1, nx, ny));  //시간을 증가시켜서 다시 큐에 삽입
                    examiner[nx][ny] = currVirus.num;  //방문처리 역할도 한다.
                }
            }
        }
    }
}
