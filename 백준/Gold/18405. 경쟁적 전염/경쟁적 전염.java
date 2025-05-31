import java.io.*;
import java.util.*;

public class Main {
    static int N, K;
    static PriorityQueue<VirusNode> pq;
    static PriorityQueue<VirusNode> pqNext;
    static int[][] examiner;

    static class VirusNode {
        int num;
        int x;
        int y;

        public VirusNode(int num, int x, int y) {
            this.num = num;
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
        pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.num, o2.num));  //바이러스의 번호가 낮은 것부터 우선순위
        pqNext =  new PriorityQueue<>((o1, o2) -> Integer.compare(o1.num, o2.num));  //다음 초의 bfs에서 쓸 우선순위 큐
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                examiner[i][j] = Integer.parseInt(st.nextToken());
                //처음 바이러스의 좌표 저장
                if (examiner[i][j] != 0) {
                    pqNext.add(new VirusNode(examiner[i][j], i, j));  //우선순위 큐에 바이러스 번호와 좌표 저장
                }
            }
        }
        st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());
        int Y = Integer.parseInt(st.nextToken());

        //bfs를 S초 반복
        for (int i = 0; i < S; i++) {
            bfs();
        }

        System.out.println(examiner[X - 1][Y - 1]);
        br.close();
    }

    static void bfs() {
        pq.addAll(pqNext);  //이전 턴에서 저장해둔 pqNext를 현재 bfs에서 사용
        pqNext =  new PriorityQueue<>((o1, o2) -> Integer.compare(o1.num, o2.num));  //초기화
        Queue<VirusNode> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        //바이러스 번호가 낮은거부터 전염
        while (!pq.isEmpty()) {
            VirusNode first = pq.poll();  //가장 낮은 바이러스 꺼내기
            q.add(first);

            //first와 같은 번호인 바이러스만 큐에 삽입
            while (!pq.isEmpty() && pq.peek().num == first.num) {
                VirusNode v = pq.poll();
                q.add(v);
                visited[v.x][v.y] = true;  //방문 처리
            }

            //같은 번호인 바이러스만 전염
            while (!q.isEmpty()) {
                VirusNode currVirus = q.poll();

                for (int i = 0; i < 4; i++) {
                    int nx = currVirus.x + dx[i];
                    int ny = currVirus.y + dy[i];

                    if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
                        continue;
                    }

                    if (!visited[nx][ny] && examiner[nx][ny] == 0) {
                        pqNext.add(new VirusNode(currVirus.num, nx, ny));
                        visited[nx][ny] = true;  //매 초마다 bfs를 하기 때문에 방문처리를 examiner로 하면 안된다. examiner는 이미 1로 표시되어있으니까
                        examiner[nx][ny] = currVirus.num;
                    }
                }
            }
        }
    }
}
