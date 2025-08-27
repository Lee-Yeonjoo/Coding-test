import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//브루트포스, 백트래킹
public class Main {
    static int N;
    static int digits;  //자릿수의 개수
    static int count;  //몇번째인지 세는 변수 - 수가 완성될 때마다 증가
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        //마지막 수가 1023번째이므로 그 이후의 수는 예외처리
        if (N > 1023) {
            System.out.println(-1);
            System.exit(0);
        }

        //각 자릿수에 대해 반복
        for (int i = 1; i <= 10; i++) {
            digits = i;
            solution(0, 0, 10);  //depth를 0부터 시작해야 한자리수도 구할 수 있음
        }
    }

    static void solution(int depth, long num, int lastNum) {
        //자릿수를 다 채워서 숫자가 완성된 경우
        if (depth == digits) {
            //완성된 수가 N번째인 경우
            if (++count == N) {
                System.out.println(num);
                System.exit(0);
            }
        }

        //숫자 0~9까지 탐색
        for (int i = 0; i <= 9; i++) {
            if (lastNum <= i) {
                continue;
            }

            long temp = num;
            num = (num * 10) + i;  //다음 자릿수를 추가한 값
            solution(depth + 1, num, i);  //추가한 값에 대해 또 탐색
            num = temp;  //백트래킹
        }
    }
}
