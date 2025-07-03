import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

//그리디
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] sensors = new int[N];
        for (int i = 0; i < N; i++) {
            sensors[i] = Integer.parseInt(st.nextToken());
        }
        Integer[] distance = new Integer[N - 1];  //센서 간 거리를 저장

        //집중국이 센서보다 많거나 같을 경우 -> 센서마다 설치하면 되므로 수신 가능 영역의 합이 무조건 0
        if (K >= N) {
            System.out.println(0);
            System.exit(0);
        }

        //센서 좌표 정렬
        Arrays.sort(sensors);

        //센서 간 거리 구하고 내림차순 정렬
        for (int i = 0; i < N - 1; i++) {
            distance[i] = sensors[i + 1] - sensors[i];
        }
        Arrays.sort(distance, Collections.reverseOrder());  //distance가 int[]가 아닌 Integer[]여야 내림차순 가능

        //수신 가능 영역의 합 구하기 -
        int sum = 0;
        for (int i = K - 1; i < N - 1; i++) {
            sum += distance[i];
        }
        System.out.println(sum);
    }
}
