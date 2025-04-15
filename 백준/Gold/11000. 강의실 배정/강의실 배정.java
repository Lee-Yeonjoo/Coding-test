import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//그리디 - 우선순위 큐 2개 사용
public class Main {

    //회의 시간을 저장하는 클래스
    static class Meeting {

        int start;
        int end;

        Meeting(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        //우선순위 큐. 회의 시작 시간을 기준
        PriorityQueue<Meeting> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.start));
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int S = Integer.parseInt(st.nextToken());
            int T = Integer.parseInt(st.nextToken());
            pq.offer(new Meeting(S, T));
        }

        //회의실 배정이 새로 될 때마다 각 회의실에서의 마지막 회의의 종료 시간을 우선순위 큐에 저장!
        PriorityQueue<Integer> endTimes = new PriorityQueue<>();
        while (!pq.isEmpty()) {

            //큐에서 가장 먼저 시작하는 회의 꺼내기
            Meeting meeting = pq.poll();

            //만약 회의실이 하나도 배정 안되었다면 처음엔 그냥 회의실을 배정(우선순위 큐에 종료시간만 저장) 
            if (endTimes.isEmpty()) {
                endTimes.add(meeting.end);
                continue;
            }

            //가장 먼저 종료되는 회의실의 종료시간이 배정할 회의의 시작시간보다 작거나 같아서 배정이 가능한 경우, 기존 회의실을 큐에서 빼기(다시 갱신할거라서) 
            if (endTimes.peek() <= meeting.start) {
                endTimes.poll();
            }
            endTimes.offer(meeting.end);  //이미 배정된 회의실이라면 종료 시간 갱신. or 새 회의실 배정하므로 종료 시간 추가 
        }

        System.out.println(endTimes.size());
    }
}
