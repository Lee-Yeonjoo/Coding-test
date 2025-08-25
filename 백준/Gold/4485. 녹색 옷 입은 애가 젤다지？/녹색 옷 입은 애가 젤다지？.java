import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//다익스트라
public class Main {
    static int[][] cave;
    static int N;
    static int testNum;
    static StringBuilder sb = new StringBuilder();
    static class Node {
        int x;
        int y;
        int cost;

        Node(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        while (true) {
            N = Integer.parseInt(br.readLine());
            if (N == 0) break;

            //동굴 도둑루피값 입력
            cave = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    cave[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            testNum++;
            dijkstra(0, 0);
        }
        System.out.println(sb);
    }

    //다익스트라
    static void dijkstra(int x, int y) {
        //방향 벡터 - 상하좌우
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        int[][] dist = new int[N][N];
        //최소 비용을 갱신해나갈 배열이므로 최댓값으로 초기화
        for (int i = 0; i < N; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost, o2.cost));

        //시작 노드 설정
        dist[0][0] = cave[0][0];
        pq.offer(new Node(0, 0, dist[0][0]));

        while (!pq.isEmpty()) {
            Node currNode = pq.poll();

            //꺼낸 노드가 도착지 노드라면 최솟값 갱신이 완료된 것이므로 종료
            if (currNode.x == N - 1 && currNode.y == N - 1) {
                sb.append("Problem ").append(testNum).append(": ").append(dist[currNode.x][currNode.y]).append("\n");
                return;
            }

            //중복 계산 방지
            if (dist[currNode.x][currNode.y] < currNode.cost) {
                continue;
            }

            //인접한 이웃에 대해 최단거리 갱신
            for (int i = 0; i < 4; i++) {
                int nx = currNode.x + dx[i];
                int ny = currNode.y + dy[i];

                //범위를 벗어나면 continue 
                if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
                    continue;
                }

                //cave에 입력한 값이 해당 이웃노드로 가는 비용
                //현재노드를 거쳐서 이웃으로 갈때의 최솟값 갱신 
                if (dist[nx][ny] > dist[currNode.x][currNode.y] + cave[nx][ny]) {
                    dist[nx][ny] = dist[currNode.x][currNode.y] + cave[nx][ny];
                    pq.offer(new Node(nx, ny, dist[nx][ny]));
                }
            }
        }
    }
}
