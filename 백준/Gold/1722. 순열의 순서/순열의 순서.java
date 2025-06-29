import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int num = Integer.parseInt(st.nextToken());

        //1~N까지의 수를 저장
        ArrayList<Integer> nums = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            nums.add(i);
        }

        if (num == 1) {
            //k와 bundle은 long타입이어야야 한다!
            long k = Long.parseLong(st.nextToken()) - 1;  //인덱스라서 1 빼준다.

            for (int i = 0; i < N; i++) {
                //i번째 자리의 수가 채워졌을 때의 나머지 경우의 수 구하기
                long bundle = 1;
                for (int j = 1; j <= N - 1 - i; j++) {
                    bundle *= j;
                }

                int index = (int) (k / bundle);
                System.out.print(nums.get(index) + " ");  //해당 인덱스의 리스트값 출력
                k = k % bundle;  //k값 갱신
                nums.remove(index);  //출력한 수는 리스트에서 제거
            }
        } else {
            long sum = 1;  //답을 담는 변수인데 인덱스로 구하는거라 1을 더해줘야 함
            for (int i = 0; i < N; i++) {
                //수열을 한 글자씩 처리
                int input = Integer.parseInt(st.nextToken());

                //묶음의 구성 요소의 개수를 구함
                long bundle = 1;
                for (int j = N - 1 - i; j >= 1; j--) {
                    bundle *= j;
                }
                int index = nums.indexOf(input);  //리스트에서 input값의 인덱스를 구한다.
                sum += bundle * index;  //각 번들에서 몇번째인지가 구해지고 sum에 더하기
                nums.remove(index);  //리스트에서 input값 제거
            }
            System.out.println(sum);
        }
    }
}
