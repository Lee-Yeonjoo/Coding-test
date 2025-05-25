import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

//그리디, 우선순위 큐
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> positive = new PriorityQueue<>(Comparator.reverseOrder());  //양수는 내림차순
        PriorityQueue<Integer> negative = new PriorityQueue<>();  //음수와 0은 오름차순
        for (int i = 0; i < N; i++) {
            int x = Integer.parseInt(br.readLine());
            if (x > 0) {  //양수만 저장
                positive.add(x);
            } else {  //0과 음수 저장
                negative.add(x);
            }
        }

        int sum = 0;
        //양수 큰 거부터 묶기
        while (positive.size() >= 2) {
            int x = positive.poll();
            int y = positive.poll();
            //1에 대한 예외처리 주의!!
            if (x == 1 || y == 1) {  //1+2 < 1*2인 예외
                sum += (x + y);
            } else {
                sum += (x * y);
            }
        }
        //남은 수 있으면 더하기
        if (!positive.isEmpty()) {
            sum += positive.poll();
        }

        //음수 작은 거부터 묶기
        while (negative.size() >= 2) {
            int x = negative.poll();
            int y = negative.poll();
            sum += (x * y);
        }
        if (!negative.isEmpty()) {
            sum += negative.poll();
        }

        System.out.println(sum);
    }
}
