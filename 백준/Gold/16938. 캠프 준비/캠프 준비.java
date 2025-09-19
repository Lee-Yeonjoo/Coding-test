import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//브루트포스, dfs
public class Main {
    static int N, L, R, X;
    static int[] A;
    static int count = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        A = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        dfs(0, 0, Integer.MIN_VALUE, Integer.MAX_VALUE, 0);
        System.out.println(count);
    }

    //문제 고르기
    static void dfs(int start, int depth, int max, int min, int sum) {
        //System.out.println(sum + " max: " + max + "  min: " + min + "  depth: " + depth);

        //문제를 2개 이상 고른 경우 -> 조건 체크, 만족하면 카운트
        if (depth >= 2) {  //문제 난이도가 '정수'라서 범위 넘어가도 다시 범위 내로 들어올 수 있음 
            //조건 만족하면 카운트
            if (L <= sum && sum <= R && (max - min) >= X) {
                count++;
            }
        }

        //다음 문제 고르기 
        for (int i = start; i < N; i++) {
            dfs(i+1, depth+1, Math.max(max, A[i]), Math.min(min, A[i]), sum + A[i]);
        }
    }
}
