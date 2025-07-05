import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

//브루트 포스
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        ArrayList<Long>[] decreaseNums = new ArrayList[11];

        //예외처리 -> 1023번째부터는 9876543210보다 자릿수가 크므로 -1 출력
        if (N >= 1023) {
            System.out.println(-1);
            System.exit(0);
        } else if (N <= 10) {  //N이 10까지는 답이 N과 같음
            System.out.println(N);
            System.exit(0);
        }

        //배열의 각 리스트 초기화
        for (int i = 0; i < 11; i++) {
            decreaseNums[i] = new ArrayList<>();
        }
        //첫번째 행의 리스트를 0~9로 초기화
        for (long i = 0; i < 10; i++) {
            decreaseNums[1].add(i);
        }

        //나머지 행의 값 구하기
        for (int i = 2; i <= 10; i++) {
            //첫번째 자릿수에 따라
            for (int first = 1; first < 10; first++) {
                long temp = (long) (first * Math.pow(10, i - 1));  //이전 행의 값 맨 앞에 first를 붙이기 위해
                for (long num : decreaseNums[i - 1]) {  //이전 행의 값을 순회
                    //이전 행의 값의 앞 자리수 추출
                    long numsFirst = (long) (num / Math.pow(10, i - 2));

                    //감소하는 수가 아닌 경우
                    if (first <= numsFirst) {
                        break;
                    }

                    decreaseNums[i].add(temp + num);  //감소하는 수는 리스트에 추가
                }
            }
        }

        //ArrayList 배열을 하나의 리스트로 합치기
        List<Long> result = new ArrayList<>();
        for (ArrayList<Long> decreaseNum : decreaseNums) {
            result.addAll(decreaseNum);
        }
        System.out.println(result.get(N));
    }
}
