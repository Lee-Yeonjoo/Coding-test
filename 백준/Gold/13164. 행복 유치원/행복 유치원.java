import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//그리디
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] heightDiff = new int[N - 1];  //키 차이는 N-1개 존재
        int tempHeight = Integer.parseInt(st.nextToken());
        //키 차이를 저장
        for (int i = 0; i < N - 1; i++) {
            int height = Integer.parseInt(st.nextToken());
            heightDiff[i] = height - tempHeight;
            tempHeight = height;
        }

        //키 차이를 오름차순 정렬
        Arrays.sort(heightDiff);

        //가장 큰 키차이 K-1개를 빼고 나머지 다 더하기
        int sum = 0;
        for (int i = 0; i < N - K; i++) {
            sum += heightDiff[i];
        }
        System.out.println(sum);
    }
}
