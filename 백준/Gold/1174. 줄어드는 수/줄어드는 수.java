import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//브루트포스, 백트래킹 -> 자릿수별로 반복이 아니라 딱 한번만 반복해서 1023번째 수까지 다 구하는 방법 -> 이 방법은 백트래킹도 필요없음 올바른 범위만 탐색하기 때문
public class Main {
    static int N;
    static List<Long> decreasingNums = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        //예외 처리
        if (N > 1023) {
            System.out.println(-1);
            System.exit(0);
        }

        //각 반복 시 0~9로 시작하는 수에 대해 줄어드는 수 구하기
        for (int i = 0; i <= 9; i++) {
            solution(i, i);
        }

        //0으로 시작하는 수, 1로 시작하는 수... 이렇게 따로 구했으므로 정렬 필요
        Collections.sort(decreasingNums);
        System.out.println(decreasingNums.get(N - 1));
    }

    static void solution(long num, int lastNum) {
        //num은 무조건 줄어드는 수이므로 list에 추가
        decreasingNums.add(num);

        //새로 추가할 숫자가 lastNum보다 작아야함
        for (int i = 0; i < lastNum; i++) {
            solution((num * 10) + i, i);  //추가한 값에 대해 또 탐색
        }
    }
}
