import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

//dfs
public class Main {
    static boolean[] visited;
    static ArrayList<Integer>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());
        int[] truthPeople = new int[K];
        //진실을 아는 사람
        for (int i = 0; i < K; i++) {
            truthPeople[i] = Integer.parseInt(st.nextToken());
        }

        //같은 파티를 간 적이 있으면 이웃으로 양방향 연결 
        graph = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        //파티에 온 사람들 정보 저장 
        ArrayList<Integer>[] partyInfo = new ArrayList[M];
        for (int i = 0; i < M; i++) {
            partyInfo[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());  //사람 수
            for (int j = 0; j < x; j++) {
                partyInfo[i].add(Integer.parseInt(st.nextToken()));
            }

            //파티에서 만난 사람들은 일직선으로 양방향 연결
            for (int j = 0; j < x - 1; j++) {
                graph[partyInfo[i].get(j)].add(partyInfo[i].get(j + 1));  //i는 현재 파티의 번호
                graph[partyInfo[i].get(j + 1)].add(partyInfo[i].get(j));
            }
        }

        visited = new boolean[N + 1];
        //진실을 아는 사람을 시작점으로 dfs 반복
        for (int i = 0; i < K; i++) {
            if (!visited[truthPeople[i]]) {
                dfs(truthPeople[i]);
            }
        }

        //방문 안한 노드들이 간 파티의 수 세기 -> 방문 안한 노드들 = 진실을 끝까지 모름 
        boolean[] partyCount = new boolean[M];
        for (int i = 1; i < N + 1; i++) {
            if (visited[i]) continue;

            for (int j = 0; j < M; j++) {
                //if (partyCount[j]) continue;  //이미 표시한 파티 건너뛰기 -> 시간단축 아마도
                for (Integer personNum : partyInfo[j]) {
                    if (personNum == i) {
                        partyCount[j] = true;
                    }
                }
            }
        }

        int answer = 0;
        for (boolean b : partyCount) {
            if (b) answer++;
        }
        System.out.println(answer);
    }

    static void dfs(int x) {
        visited[x] = true;

        //같이 파티 간 사람 탐색
        for (Integer adj : graph[x]) {
            if (!visited[adj]) {
                dfs(adj);
            }
        }
    }
}
