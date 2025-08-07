import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//그리디 - N이 최대 100만이라 O(N)알고리즘이어야 한다! 이게 어려웠음
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] balloonHeight = new int[N];
        int maxHeight = 0;
        for (int i = 0; i < N; i++) {
            balloonHeight[i] = Integer.parseInt(st.nextToken());
            maxHeight = Math.max(maxHeight, balloonHeight[i]);
        }

        int arrowCount = 0;
        int[] arrows = new int[maxHeight + 1];  //인덱스가 높이인 화살수를 기록
        for (int i = 0; i < N; i++) {
            if (arrows[balloonHeight[i]] > 0) {  //해당 높이를 맞출 수 있는 화살이 있다면 1 감소
                arrows[balloonHeight[i]]--;
            } else {  //없다면 화살개수 추가
                arrowCount++;
            }
            arrows[balloonHeight[i] - 1]++;  //현재 높이 - 1 높이에서 터트릴 수 있게됨
        }
        System.out.println(arrowCount);
    }
}
