import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//구현
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        long k = Integer.parseInt(st.nextToken());

        int sitNum = 1;  //자릿수
        long nines = 9;  //9, 90, 900...을 담을 변수 -> 자릿수에 따른 범위의 숫자 개수
        while (k > nines * sitNum) {  //자릿수를 곱해야 숫자 하나씩 자랐을 때의 개수임
            k -= nines * sitNum;
            nines *= 10;
            sitNum++;
        }
        k--;  //숫자 조정

        //k번째 수를 포함하는 숫자 찾기
        long num = (long) Math.pow(10, sitNum - 1) + (k / sitNum);  //k가 10억일 때 sitNum도 커져서 10^(sitNum)이 커질 수 있기 때문에 long타입

        //예외처리
        if (num > N) {
            System.out.println(-1);
        } else {
            System.out.println(String.valueOf(num).charAt((int) (k % sitNum)));
        }
    }
}
