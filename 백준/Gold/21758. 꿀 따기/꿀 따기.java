import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//그리디, 누적합
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] honey = new int[N];
        int[] prefixSum = new int[N + 1];
        //입력 받으면서 누적합 구하기
        for (int i = 0; i < N; i++) {
            honey[i] = Integer.parseInt(st.nextToken());
            prefixSum[i + 1] = prefixSum[i] + honey[i];
        }

        int max = 0;
        //벌-벌-통인 경우
        for (int i = 1; i < N - 1; i++) {  //i는 벌2의 인덱스
            int temp = prefixSum[N] - prefixSum[1];  //벌1 - 통의 구간합
            temp = temp - honey[i] + (prefixSum[N] - prefixSum[i + 1]);
            max = Math.max(max, temp);  //최댓값 갱신
        }

        //통-벏-벌인 경우
        for (int i = 1; i < N - 1; i++) {
            int temp = prefixSum[N - 1];
            temp = temp - honey[i] + prefixSum[i];
            max = Math.max(max, temp);
        }

        //벌-통-벌인 경우
        for (int i = 1; i < N - 1; i++) {  //i는 벌통의 인덱스
            int temp = 0;
            temp += prefixSum[i + 1] - prefixSum[1];  //벌1과 통의 구간합
            temp += prefixSum[N - 1] - prefixSum[i];  //벌2와 통의 구간합
            max = Math.max(max, temp);  //최댓값 갱신
        }
        System.out.println(max);
    }
}
