import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

//구현 - modular 계산해서 회전 횟수 구하기
public class Main {
    static int N, M;
    static int[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //i, j는 각 테두리의 시작 좌표
        int i = 0;
        int j = 0;
        int endI = N / 2;
        int endJ = M / 2;
        while (i < endI && j < endJ) {
            //mod계산 -> count 구하기
            int endi = N - i;
            int endj = M - j;
            int mod = (endi - i) * 2 + (endj - j - 2) * 2;

            //회전
            rotate(R % mod, i, j);
            i++;
            j++;
        }

        StringBuilder sb = new StringBuilder();
        for (int[] ints : arr) {
            for (int anInt : ints) {
                sb.append(anInt).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    //회전 - mod 계산한 회전 횟수 전달
    public static void rotate(int count, int i, int j) {
        //덱에 회전할 값들을 저장
        Deque<Integer> dq = new LinkedList<>();
        int endI = N - 1 - i;
        int endJ = M - 1 - j;

        //왼쪽 변
        for (int s = i; s <= endI; s++) {
            dq.add(arr[s][j]);
        }
        //아랫 변
        for (int t = j + 1; t <= endJ; t++) {
            dq.add(arr[endI][t]);
        }
        //오른쪽 변
        for (int s = endI - 1; s >= i; s--) {
            dq.add(arr[s][endJ]);
        }
        //윗 변
        for (int t = endJ - 1; t > j; t--) {
            dq.add(arr[i][t]);
        }

        //dq를 회전
        for (int r = 0; r < count; r++) {
            if (!dq.isEmpty()) {
                int temp = dq.pollLast();
                dq.addFirst(temp);
            }
        }

        //회전한 순서대로 다시 저장
        //왼쪽 변
        for (int s = i; s <= endI; s++) {
            if (!dq.isEmpty()) arr[s][j] = dq.pollFirst();
        }
        //아랫 변
        for (int t = j + 1; t <= endJ; t++) {
            if (!dq.isEmpty()) arr[endI][t] = dq.pollFirst();
        }
        //오른쪽 변
        for (int s = endI - 1; s >= i; s--) {
            if (!dq.isEmpty()) arr[s][endJ] = dq.pollFirst();
        }
        //윗 변
        for (int t = endJ - 1; t > j; t--) {
            if (!dq.isEmpty()) arr[i][t] = dq.pollFirst();
        }
    }
}
