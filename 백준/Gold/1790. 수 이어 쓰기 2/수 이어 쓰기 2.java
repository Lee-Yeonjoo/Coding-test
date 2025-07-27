import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//구현
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] count = new int[10]; // 자리수별 숫자 개수 저장
        long len = 0;

        for (int i = 1; i < 10; i++) {
            int start = (int) Math.pow(10, i - 1);
            int end = (int) Math.min(N, Math.pow(10, i) - 1);

            if (start > N) break;

            count[i] = end - start + 1;
            len += (long) count[i] * i;
        }

        if (len < k) {
            System.out.println(-1);
            return;
        }

        int i = 1;
        while (k > count[i] * i) {
            k -= count[i] * i;
            i++;
        }

        int q = (k - 1) / i;
        int r = (k - 1) % i;
        int num = (int) Math.pow(10, i - 1) + q;
        System.out.println(Integer.toString(num).charAt(r));
    }
}
