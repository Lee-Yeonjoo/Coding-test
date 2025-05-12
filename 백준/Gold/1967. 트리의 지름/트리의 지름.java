import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

//트리의 지름 구하기 - 두 번 탐색
public class Main {
    static class Node {
        int num;
        int cost;

        public Node(int num, int cost) {
            this.num = num;  //이웃 노드의 번호
            this.cost = cost;  //간선 비용
        }
    }

    static ArrayList<ArrayList<Node>> tree;
    static boolean[] visited;
    static int maxCost = 0;
    static int maxNodeNum = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st;
        tree = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            tree.add(new ArrayList<>());
        }
        for (int i = 0; i < n-1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            //무방향 그래프
            tree.get(u).add(new Node(v, cost));
            tree.get(v).add(new Node(u, cost));
        }

        visited = new boolean[n + 1];
        dfs(1, 0);

        visited = new boolean[n + 1];  //방문 초기화 꼭!
        dfs(maxNodeNum, 0);  //트리의 지름이 될 두 정점 중 하나를 시작점으로 다시 dfs -> 이때의 먼 거리가 트리의 지름
        System.out.println(maxCost);
    }

    static void dfs(int start, int sum) {
        visited[start] = true;

        for (int i = 0; i < tree.get(start).size(); i++) {
            Node nextNode = tree.get(start).get(i);

            if (!visited[nextNode.num]) {
                dfs(nextNode.num, sum + nextNode.cost);
                //더 먼 거리를 구했다면 갱신
                if (maxCost < sum + nextNode.cost) {
                    maxCost = sum + nextNode.cost;
                    maxNodeNum = nextNode.num;  //트리의 지름이 될 두 정점 중 하나를 구하기 위함
                }
            }
        }
    }
}
