import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class Main {
    static int N;
    static int[][] seat;
    static int[][] friends;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int answer = 0;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        seat = new int[N][N];
        friends = new int[N * N + 1][4];
        StringTokenizer st;
        for (int i = 0; i < N * N; i++) {
            st = new StringTokenizer(br.readLine());
            int studentNum = Integer.parseInt(st.nextToken());
            //좋아하는 학생 저장
            for (int j = 0; j < 4; j++) {
                friends[studentNum][j] = Integer.parseInt(st.nextToken());
            }

            List<Point> result = process1(studentNum);  //자리 후보 리스트 반환
            //결과 자리가 한 개면 바로 배정
            if (result.size() == 1) {
                Point point = result.get(0);
                seat[point.x][point.y] = studentNum;
                continue;
            }

            result = process2(result);  //두번째 자리 후보 리스트 반환
            //프로세스3 - 좌표들을 행과 열에 대해 오름차순 정렬
            result.sort((o1,o2)-> {
                if (o1.x < o2.x) {
                    return -1;
                } else if (o1.x == o2.x) {
                    return Integer.compare(o1.y, o2.y);
                } else return 1;
            });
            Point point = result.get(0);  //가장 행과 열이 작은 자리에 배정
            seat[point.x][point.y] = studentNum;
        }

        //만족도 조사
        score();
        System.out.println(answer);
    }

    static List<Point> process1(int studentNum) {
        //인접한 지 카운트하는 배열
        int[][] adjSeat = new int[N][N];

        //친구 목록을 set에 담기
        Set<Integer> friendSet = new HashSet<>();
        for (int i = 0; i < 4; i++) {
            friendSet.add(friends[studentNum][i]);
        }

        int max = 0;
        List<Point> p1List = new ArrayList<>();  //반환할 리스트 - 인접 좌표 리스트
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                //좋아하는 친구가 있는 자리라면
                if (friendSet.contains(seat[i][j])) {
                    //상하좌우 체크
                    for (int k = 0; k < 4; k++) {
                        int ni = i + dx[k];
                        int nj = j + dy[k];

                        if (ni < 0 || ni >= N || nj < 0 || nj>=N) continue;
                        if (seat[ni][nj] != 0) continue;  //이미 누가 앉은 경우 패스

                        adjSeat[ni][nj]++;  //인접 자리를 카운트
                        if (max == adjSeat[ni][nj]) {  //max값과 카운트값이 같다면
                            p1List.add(new Point(ni, nj));  //자리 후보 리스트에 추가
                        }
                        if (max < adjSeat[ni][nj]) {  //max값이 갱신된다면 새로운 후보 리스트 생성
                            max = adjSeat[ni][nj];
                            p1List = new ArrayList<>();
                            p1List.add(new Point(ni, nj));  //후보 자리에 추가
                        }
                    }
                }
            }
        }

        //좋아하는 친구와 인접한 자리가 없는 경우
        //그냥 빈 자리 모두를 넣는다.
        if (max == 0) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (seat[i][j] == 0) {
                        p1List.add(new Point(i, j));
                    }
                }
            }
        }

        return p1List;
    }

    //프로세스1에서 구한 maxSeat에서 빈칸이 많은 자리 찾는 함수
    static List<Point> process2(List<Point> maxSeat) {
        List<Point> p2List = new ArrayList<>();
        int maxCount = 0;
        for (Point p : maxSeat) {
            int count = 0;
            for (int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny>=N) continue;

                //빈칸이라면 카운트
                if (seat[nx][ny] == 0) {
                    count++;
                }
            }

            if (maxCount == count) {  //빈칸 개수가 max값과 동일하면 후보 리스트에 추가
                p2List.add(p);
            }
            //빈 칸의 개수가 갱신되는 경우 새로운 후보 리스트 생성
            if (maxCount < count) {
                maxCount = count;
                p2List = new ArrayList<>();
                p2List.add(p);
            }
        }

        return p2List;
    }

    //점수 계산
    static void score() {

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int friendsCount = 0;
                //친구 목록을 set에 담기
                Set<Integer> friendSet = new HashSet<>();
                for (int k = 0; k < 4; k++) {
                    friendSet.add(friends[seat[i][j]][k]);
                }

                //인접한 자리에 친구 몇 명인지 세기
                for (int k = 0; k < 4; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];

                    if (nx < 0 || nx >= N || ny < 0 || ny>=N) continue;
                    if (friendSet.contains(seat[nx][ny])) friendsCount++;
                }

                if (friendsCount == 1) {
                    answer += 1;
                } else if (friendsCount == 2) {
                    answer += 10;
                } else if (friendsCount == 3) {
                    answer += 100;
                } else if (friendsCount == 4) {
                    answer += 1000;
                }
            }
        }
    }
}
