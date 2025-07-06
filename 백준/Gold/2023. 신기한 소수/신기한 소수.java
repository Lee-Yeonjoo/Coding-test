import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//dfs
public class Main {
    static int N;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        sb = new StringBuilder();

        //시작노드 - 1, 2, 3, 5, 7
        for (int i : new int[]{2, 3, 5, 7}) {
            dfs(i, 1);
        }
        System.out.println(sb);
    }

    public static void dfs(int x, int depth) {
        //깊이가 N이 되면 탐색 종료
        if (depth >= N) {
            sb.append(x).append("\n");
            return;
        }

        //0~9 탐색
        for (int i = 0; i <= 9; i++) {
            int nx = x * 10 + i;  //다음 값 구하기

            if (!isPrime(nx)) continue;  //소수가 아니라면 continue

            dfs(nx, depth + 1);
        }
    }

    //소수 판별
    public static boolean isPrime(int num) {
        //2 ~ 루트n 까지 나누어떨어지는지 검사
        for (int i = 2; i <= (int) Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}
