import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

//그리디
public class Main {
    static class Puddle {
        int start;
        int end;
        Puddle(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        List<Puddle> puddles = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            puddles.add(new Puddle(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        //물 웅덩이 시작점 기준으로 정렬
        puddles.sort((o1, o2) -> Integer.compare(o1.start, o2.end));

        int endPoint = 0;  //이전 널빤지가 끝난 지점
        int count = 0;  //널빤지 개수
        for (Puddle puddle : puddles) {  //널빤지 카운트
            if (puddle.start > endPoint) {  //널빤지가 이어지지 않고, 새로 시작하는 경우 -> 시작지점에 새 널빤지
                endPoint = puddle.start;
            }
            //널빤지가 웅덩이를 다 덮을 때까지 추가
            while (endPoint < puddle.end) {
                endPoint += L;
                count++;
            }
        }
        System.out.println(count);
    }
}
