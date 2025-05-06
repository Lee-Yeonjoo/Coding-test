import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//스택으로 풀면 시간초과
public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] nums = new int[N];
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        boolean[][] isPalindrome = new boolean[N][N];
        //초기화 - 한 자리 수는 무조건 펠린드롬
        for (int i = 0; i < N; i++) {
            isPalindrome[i][i] = true;

            //두 자리 수는 같아야 펠린드롬
            if (i < N - 1 && nums[i] == nums[i+1]) {
                isPalindrome[i][i+1] = true;
            }
        }

        //가운데 수는 [i+1][j-1]에 이미 구해서 펠린드롬인지 알 수 있고, 양 끝이 같다면 펠린드롬
        for (int i = N - 2; i >= 0; i--) {
            for (int j = i + 2; j < N; j++) {
                if (isPalindrome[i + 1][j - 1] && nums[i] == nums[j]) {
                    isPalindrome[i][j] = true;
                }
            }
        }

        int M = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        //최대 백만 번 연산
        for (int i = 0; i < M; i++) {

            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken()) - 1;
            int E = Integer.parseInt(st.nextToken()) - 1;

            if (isPalindrome[S][E]) {
                sb.append(1).append("\n");
            } else sb.append(0).append("\n");
        }
        System.out.println(sb);
    }
}
