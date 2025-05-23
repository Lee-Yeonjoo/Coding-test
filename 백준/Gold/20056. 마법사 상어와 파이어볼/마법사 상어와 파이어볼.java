import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
    static int answer;
    static class Fireball {
        int m;  //질량
        int s;  //속력
        int d;  //방향
        public Fireball(int m, int s, int d) {
            this.m = m;
            this.s = s;
            this.d = d;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        List<Fireball>[][] grid = new ArrayList[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                grid[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            grid[r-1][c-1].add(new Fireball(m, s, d));
        }

        //K번 명령
        for (int k = 0; k < K; k++) {
            answer = 0;
            //새로운 그리드에 이동한 파이어볼을 표시해야 한다.
            List<Fireball>[][] move = new ArrayList[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    move[i][j] = new ArrayList<>();
                }
            }

            //파이어볼 이동
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (grid[i][j].isEmpty()) continue;

                    for (int g = 0; g < grid[i][j].size(); g++) {
                        Fireball fireball = grid[i][j].get(g);
                        int nx = (i + (fireball.s * dx[fireball.d])) % N; //칸을 벗어나는 경우 때문에 %4 해줘야 함
                        int ny = (j + (fireball.s * dy[fireball.d])) % N;
                        if (nx < 0) nx = N + nx;
                        if (ny < 0) ny = N + ny;
                        move[nx][ny].add(new Fireball(fireball.m, fireball.s, fireball.d));
                        answer += fireball.m;  //파이어볼들의 질량 세기
                    }
                }
            }
           
            //파이어볼 합치기
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (move[i][j].size() <= 1) continue;

                    int fireballCount = move[i][j].size();
                    int mSum = 0;
                    int sSum = 0;
                    int oddCount = 0;  //홀수 방향 세기
                    for (int g = 0; g < fireballCount; g++) {
                        Fireball fireball = move[i][j].get(g);
                        mSum += fireball.m;
                        sSum += fireball.s;
                        if (fireball.d % 2 != 0) {
                            oddCount++;
                        }
                        answer -= fireball.m;  //합쳐질 질량은 빼주기
                    }

                    int nm = mSum / 5;
                    int ns = sSum / fireballCount;
                    move[i][j].clear();
                    if (nm == 0) continue;  //질량이 0이 될 경우 소멸

                    //합쳐진 파이어볼이 4개로 나눠져 새로 추가
                    for (int s = 0; s < 4; s++) {
                        //방향이 모두 홀수거나 짝수 -> 0,2,4,6
                        if (oddCount == 0 || oddCount == fireballCount) {
                            move[i][j].add(new Fireball(nm, ns, 2*s));
                        }
                        else move[i][j].add(new Fireball(nm, ns, 2*s + 1));  //1,3,5,7
                    }

                    //합쳐진 질량 더하기
                    answer += nm * 4;
                }
            }
           
            //move 그리드를 grid로 참조
            grid = move;
        }

        System.out.println(answer);
    }
}
