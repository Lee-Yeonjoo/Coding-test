import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

//구현
public class Main {
    static int N;
    static ArrayList<Info> conveyorBeltTop;
    static ArrayList<Info> conveyorBeltBottom;
    static int durabilityCount;

    //각 칸이 갖는 정보 클래스
    static class Info {
        int durability;  //칸의 내구도
        boolean isRobot;  //로봇 유무

        public Info(int durability, boolean isRobot) {
            this.durability = durability;
            this.isRobot = isRobot;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        conveyorBeltTop = new ArrayList<>();
        conveyorBeltBottom = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        //윗 라인 내구도 받기
        for (int i = 0; i < N; i++) {  //벨트 이동 시, 0번에서 넣고, N-1번에서 빼기
            conveyorBeltTop.add(new Info(Integer.parseInt(st.nextToken()), false));
        }
        //아랫 라인 내구도 받기
        for (int i = 0; i < N; i++) {  //0번에서 넣고, N-1번에서 빼기
            conveyorBeltBottom.add(new Info(Integer.parseInt(st.nextToken()), false));
        }

        int answer = 0;  //단계 카운트
        durabilityCount = 0;  //내구도가 0인 경우 카운트
        while (durabilityCount < K) {  //K개 이상이 되면 while문 종료
            answer++;

            //벨트 이동
            beltMove();

            //로봇 이동
            robotMove();

            //로봇 올리기
            addRobot();
        }
        System.out.println(answer);
    }

    //벨트 이동
    static void beltMove() {
        //윗라인 -> 아랫라인 이동
        Info temp = conveyorBeltTop.remove(N - 1);
        conveyorBeltBottom.add(0, temp);

        //아랫라인 -> 윗라인 이동
        temp = conveyorBeltBottom.remove(N);  //아랫라인은 하나 늘었으므로 맨 끝 인덱스가 N이다 주의!
        conveyorBeltTop.add(0, temp);

        //로봇이 N-1칸에 오는 경우 내리기
        conveyorBeltTop.get(N - 1).isRobot = false;
    }

    //로봇 이동 - 윗라인에서만
    static void robotMove() {
        for (int i = N - 2; i >= 0; i--) {
            Info curr = conveyorBeltTop.get(i);
            Info next = conveyorBeltTop.get(i + 1);
            if (curr.isRobot && !next.isRobot && next.durability > 0) {  //현재 칸에 로봇이 있고, 다음 칸이 비었고, 내구도가 남았다면
                curr.isRobot = false;  //로봇이 이동하므로 없어짐
                //다음 칸에 로봇 추가 및 내구도 깎기
                if (i != N - 2) {
                    next.isRobot = true;  //i가 N-2면 다음 칸에서 로봇을 내리므로 false
                }
                next.durability--;
                //내구도 0이 된 경우 카운트
                if (next.durability == 0) {
                    durabilityCount++;
                }
            }
        }
    }

    //로봇 올리기
    static void addRobot() {
        Info first = conveyorBeltTop.get(0);
        if (!first.isRobot && first.durability > 0) {
            first.isRobot = true;
            first.durability--;
            //내구도 0이 된 경우 카운트
            if (first.durability == 0) {
                durabilityCount++;
            }
        }
    }
}
