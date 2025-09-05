import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

//투 포인터
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<Integer> nums = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums.add(Integer.parseInt(st.nextToken()));
        }

        //정렬
        Collections.sort(nums);

        int count = 0;
        //3번째 요소부터 시작
        for (int i = 0; i < N; i++) {
            int target = nums.get(i);
            nums.remove(i);  //타겟을 nums에서 제거

            //투 포인터
            int left = 0;
            int right = nums.size() - 1;
            while (left < right) {
                int sum = nums.get(left) + nums.get(right);
                if (sum == target) {
                   count++;
                   break;
                } else if (sum < target) {  //합이 작으면 왼쪽 포인터 이동
                    left++;
                } else {  //합이 크면 오른쪽 포인터 이동
                    right--;
                }
            }
            //target을 다시 nums 리스트에 넣기
            nums.add(i, target);
        }
        System.out.println(count);
    }
}
