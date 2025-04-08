import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

//그리디
public class Main {

    //각 일들의 작업 시간과 마감 시간을 가지는 클래스
    static class Time {

        int Ttime;
        int Stime;

        Time(int T, int S) {
            this.Ttime = T;
            this.Stime = S;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        StringTokenizer st;
        int[] T = new int[N];
        int[] S = new int[N];
        Time[] times = new Time[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            times[i] = new Time(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        //S를 기준으로 오름차순 정렬
        Arrays.sort(times, new Comparator<Time>() {
            @Override
            public int compare(Time o1, Time o2) {
                return o1.Stime - o2.Stime;
            }
        });

        int start = times[0].Stime - times[0].Ttime;  //가장 먼저 끝내야하는 일의 가장 늦은 시작 시간부터 start
        boolean isValid = false;  //start가 처음부터 음수일 수도 있어서 false로 초기화해야 한다!!!

        //start를 감소시키면서 반복
        while (start >= 0) {
            int x = start;
            isValid = true;

            //각 일들의 시간을 순회
            for (Time time : times) {
                //걸리는 시간을 더하면서 마감시간S를 넘길 경우 break
                x += time.Ttime;
                if (x > time.Stime) {
                    isValid = false;
                    break;
                }
            }

            //모든 일을 마감시간 내에 마쳤으므로 반복을 끝냄
            if (isValid) {
                break;
            }
            start--;
        }

        if (isValid) {
            System.out.println(start);
        }
        else System.out.println(-1);
    }
}
