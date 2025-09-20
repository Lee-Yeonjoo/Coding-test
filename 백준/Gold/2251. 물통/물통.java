import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

//dfs -> 각 노드가 물통의 상태 정보라는게 어려웠드아
public class Main {
    static int A, B, C;
    static boolean[][] visited;
    static Set<Integer> answers = new HashSet<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        //물통의 용량
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        visited = new boolean[A + 1][B + 1];
        dfs(0, 0, C);
        List<Integer> list = answers.stream().sorted().collect(Collectors.toList());  //오름차순 정렬, 자바 11에서는 toList()를 지원x -> Collectors.toList()로 해야함 
        StringBuilder sb = new StringBuilder();
        for (Integer i : list) {
            sb.append(i).append(" ");
        }
        System.out.println(sb);
    }

    static void dfs(int a, int b, int c) {
        visited[a][b] = true;  //상태에 대해 방문 표시

        //A의 용량이 0일 때마다 set에 C의 현재 용량 추가
        if (a == 0) {
            answers.add(c);
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == j) continue;

                int[] result = move(i, j, a, b, c);
                if (!visited[result[0]][result[1]]) {
                    dfs(result[0], result[1], result[2]);
                }
            }
        }
    }

    //from에서 to로 물통 이동하는 함수
    static int[] move(int from, int to, int a, int b, int c) {
        int[] water = {a, b, c};
        int[] limit = {A, B, C};

        int moveAmount = Math.min(water[from], limit[to] - water[to]);
        water[from] -= moveAmount;
        water[to] += moveAmount;
        return water;
    }
}
