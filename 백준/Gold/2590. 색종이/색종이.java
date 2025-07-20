import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//그리디, 구현
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] papers = new int[7];
        for (int i = 1; i <= 6; i++) {
            papers[i] = Integer.parseInt(br.readLine());
        }

        int count = 0;
        //6번 색종이는 판의 개수 그대로 반영
        count += papers[6];

        //5번 색종이
        count += papers[5];  //5번 색종이만큼 판 추가
        papers[1] -= papers[5] * 11;  //남은 칸 1번 색종이로 채우기

        //4번 색종이
        while (papers[4] > 0) {
            count++;  //4번 색종이 붙이기 위해 판 추가
            //남는 자리 2번 색종이부터 채우기 - 5개까지 가능
            int temp = Math.min(papers[2], 5);
            papers[2] -= temp;
            papers[1] -= (20 - temp * 4);  //2번 색종이 채우고 남은 자리에 1번 색종이 채우기
            papers[4]--;
        }

        //3번 색종이
        count += papers[3] / 4;
        int temp = papers[3] % 4;  //남은 색종이 수
        if (temp != 0) count++;  //색종이3이 남으므로 판 추가

        //남은 색종이3에 따라 2번, 1번 색종이 채우기
        if (temp == 3) {
            if (papers[2] > 0) {  //색종이2가 존재
                papers[2]--;  //하나 채울 수 있음
                papers[1] -= 5;  //나머지 1로 채우기
            } else {
                if (papers[1] > 0) {
                    papers[1] -= 9;
                }
            }
        } else if (temp == 2) {
            if (papers[2] > 0) {
                int num2 = Math.min(papers[2], 3);
                papers[2] -= num2;
                papers[1] -= (18 - num2 * 4);
            } else {
                if (papers[1] > 0) {
                    papers[1] -= 18;
                }
            }
        } else if (temp == 1) {
            if (papers[2] > 0) {
                int num2 = Math.min(papers[2], 5);
                papers[2] -= num2;
                papers[1] -= (27 - num2 * 4);
            } else {
                if (papers[1] > 0) {
                    papers[1] -= 27;
                }
            }
        }

        //2번 색종이
        if (papers[2] > 0) {  //2번이 남음
            count += papers[2] / 9;
            int num2 = papers[2] % 9;
            if (num2 != 0) {  //남은 자리에 1번 채우기
                count++;
                papers[1] -= (36 - num2 * 4);
            }
        }

        //1번 색종이
        if (papers[1] > 0) {
            count += papers[1] / 36;
            if (papers[1] % 36 != 0) {
                count++;
            }
        }
        System.out.println(count);
    }
}
