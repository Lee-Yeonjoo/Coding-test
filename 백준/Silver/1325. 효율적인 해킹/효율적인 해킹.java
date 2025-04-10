import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//BFS (or DFS)
public class Main {

    static int count;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        ArrayList<ArrayList<Integer>> computers = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < N+1; i++) {
            computers.add(new ArrayList<Integer>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            //B에서 A로 해킹 가능
            computers.get(B).add(A);
        }

        int maxCount = 0;
        int[] answerComputers = new int[N+1];
        for (int i = 1; i < N+1; i++) {
            visited = new boolean[N + 1];  //방문 초기화
            count = 0;  //해킹 컴퓨터 수 초기화
            bfs(i, computers);  //컴퓨터 i에서 해킹할 수 있는 컴퓨터 탐색

            //최대 해킹 컴퓨터 수 찾기
            if (count > maxCount) {
                maxCount = count;
            }
            answerComputers[i] = count;
        }

        //출력 문자열 만들기
        String answerStr = "";
        for (int i = 1; i < N+1; i++) {
            if (answerComputers[i] == maxCount) {
                answerStr += i + " ";
            }
        }

        answerStr = answerStr.substring(0, answerStr.length() - 1);  //뒤에 공백 제외
        System.out.println(answerStr);
    }

    static void bfs(int start, ArrayList<ArrayList<Integer>> computers) {

        Queue<Integer> q = new LinkedList<Integer>();
        q.offer(start);
        visited[start] = true;
        count += 1;

        while (!q.isEmpty()) {
            int curr = q.poll();

            for (Integer adj : computers.get(curr)) {
                if (!visited[adj]) {
                    visited[adj] = true;
                    q.offer(adj);
                    count += 1;  //해킹 컴퓨터 수 세기
                }
            }
        }
    }
}
