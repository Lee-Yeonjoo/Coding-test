import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//구현
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int[][] blocks = new int[H][W];
        st = new StringTokenizer(br.readLine());
        for (int j = 0; j < W; j++) {
            int block = Integer.parseInt(st.nextToken());
            for (int i = H - 1; i >= H - block; i--) {
                blocks[i][j] = 1;
            }
        }

        int answer = 0;
        for (int i = 0; i < H; i++) {
            boolean check = false;
            int tempCount = 0;
            for (int j = 0; j < W; j++) {
                //1을 만나면 체크하고, 지금까지 센 0의 개수를 합한다.
                if (blocks[i][j] == 1) {
                    check = true;
                    answer += tempCount;
                    tempCount = 0;
                }

                //1을 만난 적 있고, 0을 만난 경우
                if (blocks[i][j] == 0 && check) {
                    tempCount++;
                }
            }
        }

        System.out.println(answer);
    }
}
