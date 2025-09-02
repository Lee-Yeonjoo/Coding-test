import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//그리디, 구현 아이디어가 어려웠드아
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

        //세로 길이 구하기 위해 일자별로 카운트
        int height = 0;
        int[] dayCount = new int[366];
        for (Schedule schedule : scheduleList) {
            for (int i = schedule.start; i <= schedule.end; i++) {
                dayCount[i]++;
            }
        }

        //가로 길이
        int widthStart = scheduleList.get(0).start;
        int widthEnd = scheduleList.get(0).end;
        int area = 0;
        for (int i = 1; i < N; i++) {
            if (scheduleList.get(i).start <= widthEnd + 1) {  //문제 잘 못 이해해서 틀림ㅠ widthEnd와 같거나 작은게 아니라 widthEnd+1보다 작거나 같아야 연속된 일정임
                widthEnd = Math.max(widthEnd, scheduleList.get(i).end);
            } else {
                //세로 길이 구하기
                for (int j = widthStart; j <= widthEnd; j++) {
                    height = Math.max(height, dayCount[j]);
                }
                area += (widthEnd - widthStart + 1) * height;  //면적 더하기
                //초기화 -> 다음 일정 그룹
                widthStart = scheduleList.get(i).start;
                widthEnd = scheduleList.get(i).end;
                height = 0;
            }
        }
        //마지막 일정 그룹 면적 구하고 합하기
        for (int j = widthStart; j <= widthEnd; j++) {
            height = Math.max(height, dayCount[j]);
        }
        area += (widthEnd - widthStart + 1) * height;
        System.out.println(area);
    }
}
