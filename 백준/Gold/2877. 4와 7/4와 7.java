import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//답 봄.. 이진수를 이용해야함
public class Main {
    static int digit = 1;
    static int sum = 0;
    static int K;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());

        //자릿수 찾기
        while (K > sum) {
            sum += (int) Math.pow(2, digit++);
        }
        digit--;

        //해당 자릿수들 중 몇 번째 값인지 구하기
        int offset = K - (sum - (int) Math.pow(2, digit)) - 1;  //0번째부터라 1 빼기

        //이진수 변환
        String binary = Integer.toBinaryString(offset);

        //자릿수 맞춰서 앞에 0인 부분에 4로 채우기
        StringBuilder answer = new StringBuilder();
        if (binary.length() < digit) {
            for (int i = 0; i < digit - binary.length(); i++) {
                answer.append("4");
            }
        }

        //0 -> 4, 1 -> 7로 변환
        binary = binary.replace("0", "4");
        binary = binary.replace("1", "7");
        answer.append(binary);
        System.out.println(answer);
    }
}
