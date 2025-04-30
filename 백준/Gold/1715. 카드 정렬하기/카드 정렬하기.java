import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

//그리디
public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        //우선순위 큐 사용
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            pq.offer(Integer.parseInt(br.readLine()));
        }

        int sum = 0;
        while (pq.size() >= 2) {  //큐에 최소 2묶음은 남아 있어야 한다. 
            int A = pq.poll();
            int B = pq.poll();

            int x = A + B;
            sum += x;
            pq.offer(x);  //(A+B)장인 새로운 카드 묶음을 다시 큐에 넣는다!! 이걸 몰랐음ㅜㅜ
        }
        System.out.println(sum);
    }
}
