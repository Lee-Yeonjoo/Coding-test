import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Queue<Integer> q = new LinkedList<>();
        int N, K;
        int cnt = 1; //K만큼 세는 변수
        N = sc.nextInt();
        K = sc.nextInt();
        int[] result = new int[N];

        for (int i = 0; i < N; i++) {
            q.add(i + 1);
        }

        int i=0;
        while (!q.isEmpty()) {
            if(cnt!=K)
                q.add(q.poll());
            else {
                result[i] = q.poll();
                cnt=0;
                i++;
            }
            cnt++;
        }

        String str = "<";
        for(int j= 0; j<N; j++) {
            str += result[j];
            if(j==N-1)
                break;
            str+=", ";
        }
        str+=">";
        System.out.println(str);
    }
}
