import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//dfs - 답봄
public class Main {
    static int[] order;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] openDoor = new int[2];
        openDoor[0] = Integer.parseInt(st.nextToken());
        openDoor[1] = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(br.readLine());
        order = new int[k];
        for (int i = 0; i < k; i++) {
            order[i] = Integer.parseInt(br.readLine());
        }

        System.out.println(dfs(openDoor[0], openDoor[1], 0));
    }

    //open1, open2는 열려있는 벽장의 번호, idx는 order의 인덱스
    //open1,2에 대해 각 각의 경우를 dfs 탐색
    static int dfs(int open1, int open2, int idx) {
        if (idx == order.length) {
            return 0;
        }

        int move1 = Math.abs(open1 - order[idx]);
        int move2 = Math.abs(open2 - order[idx]);

        return Math.min(move1 + dfs(open2, order[idx], idx + 1),
                move2 + dfs(open1, order[idx], idx + 1));
    }
}
