import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//그리디, 구현 아이디어가 어려웠드아/ 더 간단한 풀이
public class Main {
    static class Schedule implements Comparable<Schedule>{
        int start;
        int end;

        Schedule(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Schedule other) {
            if (this.start != other.start) {  //시작일이 같지 않다면 먼저 시작일로 오름차순 정렬
                return Integer.compare(this.start, other.start);
            }
            return Integer.compare(other.end, this.end);  //시작일이 같다면 종료일로 내림차순 정렬
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        List<Schedule> scheduleList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            scheduleList.add(new Schedule(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        //정렬 - 1. 시작일(오름차순) 2. 종료일(내림차순)
        Collections.sort(scheduleList);

        //일자별로 일정 카운트
        int[] dayCount = new int[366];
        for (Schedule schedule : scheduleList) {
            for (int i = schedule.start; i <= schedule.end; i++) {
                dayCount[i]++;
            }
        }

        //면적 구하기
        int area = 0;
        int width = 0;
        int height = 0;
        for (int i = 1; i < dayCount.length; i++) {
            if (dayCount[i] == 0) {
                area += width * height;
                width = 0;
                height = 0;
            } else {
                width++;
                height = Math.max(height, dayCount[i]);
            }
        }
        //마지막 일정 면적 더하기
        area += width * height;
        System.out.println(area);
    }
}
