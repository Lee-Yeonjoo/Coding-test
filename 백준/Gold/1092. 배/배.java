import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.util.Collections.max;

//그리디
public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        List<Integer> crane = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            crane.add(Integer.parseInt(st.nextToken()));
        }

        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        List<Integer> box = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            box.add(Integer.parseInt(st.nextToken()));
        }

        //크레인 무게와 박스 무게를 각 각 오름차순 정렬
        box.sort(Collections.reverseOrder());

        //크레인의 최대값보다 큰 박스가 있다면 프로그램 종료
        int maxCrane = max(crane);
        for (Integer i : box) {
            if (maxCrane < i) {
                System.out.println(-1);
                return;
            }
        }

        int answer = 0;
        //박스가 빌 때까지 반복
        while (!box.isEmpty()) {
            for (Integer c : crane) {
                for (int i = 0; i < box.size(); i++) {
                    if (c >= box.get(i)) {  //박스가 크레인보다 작거나 같으면 제거
                        box.remove(i);
                        break;
                    }
                }
            }
            answer++;  //크레인 모두 순회했으면 시간 증가
        }

        System.out.println(answer);
    }
}
