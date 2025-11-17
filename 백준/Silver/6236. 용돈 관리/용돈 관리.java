import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//이분 탐색
public class Main {
    static int[] cost;
    static int N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        cost = new int[N];
        long start = 1;
        long end = 1000000000;  //N이 최대 10만이고, 각 금액은 최대 1만이니까 M이 1일 경우, K는 최대 10억
        for (int i = 0; i < N; i++) {
            cost[i] = Integer.parseInt(br.readLine());
            start = Math.max(start, cost[i]);  //탐색 범위의 최솟값 = 비용의 최댓값
        }

        long mid = (start + end) / 2;
        while (start <= end) {
            mid = (start + end) / 2;

            //K = mid일 때, 조건을 만족하면 K를 줄이기(최소 K를 찾아야하니까), 불만족하면 K를 늘리기
            if (isValid(mid)) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        System.out.println(start);
    }

    //M번 인출만에 가능한지
    public static boolean isValid(long K) {
        long curr = 0;  //현재 잔액
        int count = 0;  //인출 횟수 카운트
        for (int i = 0; i < N; i++) {
            if (curr < cost[i]) {  //잔액 부족 -> 인출
                curr = K;
                count++;
            }
            curr -= cost[i];  //비용 지출
        }
        return count <= M;
    }
}
