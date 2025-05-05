import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//최대 약 30만번 연산하므로 시간초과가 나지 않는다.
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        Stack<Integer> qValue = new Stack<>();
        for (int i = 0; i < N; i++) {
            //큐 일때만 값을 스택에 저장
            if (st.nextToken().equals("0")) {
                qValue.add(Integer.parseInt(st2.nextToken()));
            } else {  //스택일 땐 어차피 출력될 일이 없어서 무시
                st2.nextToken();
            }
        }

        int M = Integer.parseInt(br.readLine());
        int[] C = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            C[i] = Integer.parseInt(st.nextToken());
        }

        StringBuilder sb = new StringBuilder();
        int j = 0;
        for (int i = 0; i < M; i++) {
            //큐 값을 저장한 스택에서 하나씩 pop해서 출력
            if (!qValue.isEmpty()) {
                sb.append(qValue.pop()).append(" ");
            } else{  //큐 값을 다 pop했다면 수열 C의 값들이 앞에서부터 출력한다.
                sb.append(C[j]).append(" ");
                j++;
            }
        }
        System.out.println(sb);
    }
}
