import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[][] gear = new int[5][8];
        for (int i = 1; i <= 4; i++) {
            String[] status = br.readLine().split("");
            for (int j = 0; j < 8; j++) {
                gear[i][j] = Integer.parseInt(status[j]);
            }
        }

        //2번, 6번 위치인 인덱스 저장 - 맞물리는 부분을 가리키는 포인터 역할
        int[][] indexValues = new int[5][2];
        for (int i = 1; i <= 4; i++) {
            indexValues[i][0] = 2;
            indexValues[i][1] = 6;
        }

        //12시 방향 위치의 인덱스 저장 -> 회전할 때마다 갱신
        int[] result = new int[5];

        int K = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int direction = Integer.parseInt(st.nextToken());

            //x번 톱니의 왼쪽 톱니 회전
            int left = 1;
            int[] temp = new int[5];  //기존 인덱스 값을 토대로 맞물렸는지 판단해야해서 임시로 저장
            for (int t = 1; t < 5; t++) {
                temp[t] = indexValues[t][1];
            }
            int temp_direction = direction;  //방향도 계속 바뀌므로 임시로 저장
            //x번 톱니바퀴의 왼쪽 톱니바퀴들에 대해 처리
            while (x > left) {  //left가 증가하면서 x번에서 점점 멀어지게 왼쪽 톱니 바퀴로 이동
                int idx1 = x - left + 1;
                int idx2 = x - left;
                //만약 오른쪽 톱니의 2번 위치와 왼쪽 톱니의 6번 위치가 다른 경우 왼쪽 톱니의 인덱스 갱신
                if (gear[idx1][temp[idx1]] != gear[idx2][indexValues[idx2][0]]) {
                    result[idx2] = (result[idx2] + temp_direction + 8) % 8;  //방향만큼 인덱스 조정
                    indexValues[idx2][0] = (indexValues[idx2][0] + temp_direction + 8) % 8;  //2번, 6번 자리 인덱스 변경
                    indexValues[idx2][1] = (indexValues[idx2][1] + temp_direction + 8) % 8;
                }
                else break;  //같을 경우 톱니가 더이상 회전할 수 없음
                left++;
                temp_direction *= -1;  //방향을 바꿔줘야 함
            }

            //x번 톱니의 오른쪽 톱니 회전
            int right = 1;
            for (int t = 1; t < 5; t++) {
                temp[t] = indexValues[t][0];
            }

            temp_direction = direction;
            while (x + right <= 4 ) {
                int idx1 = x + right - 1;
                int idx2 = x + right;

                if (gear[idx1][temp[idx1]] != gear[idx2][indexValues[idx2][1]]) {
                    result[idx2] = (result[idx2] + temp_direction + 8) % 8;  //방향만큼 인덱스 조정
                    indexValues[idx2][0] = (indexValues[idx2][0] + temp_direction + 8) % 8;
                    indexValues[idx2][1] = (indexValues[idx2][1] + temp_direction + 8) % 8;
                }
                else break;
                right++;
                temp_direction *= -1;  //방향을 바꿔줘야 함
            }

            //x번 톱니 회전
            result[x] = (result[x] - direction + 8) % 8;
            indexValues[x][0] = (indexValues[x][0] - direction + 8) % 8;
            indexValues[x][1] = (indexValues[x][1] - direction + 8) % 8;
        }

        int answer = 0;
        for (int i = 0; i < 4; i++) {
            answer += (int) (gear[i + 1][result[i + 1]] * (Math.pow(2, i)));
        }
        System.out.println(answer);
    }
}
