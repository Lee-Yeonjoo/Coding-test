import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        long ATK = Integer.parseInt(st.nextToken());
        int[][] roomInfo = new int[N][3];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                roomInfo[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        long start = 1;
        long end = 123455876544000001L;  //maxHP의 최대값을 1e18로 넉넉하게 잡는다..
        long mid = 0;
        while (start <= end) {
            mid = (start + end) / 2;
            long hp = mid;  //hp를 mid값으로 초기화
            long atk = ATK;
            boolean alive = true;  //용사의 생존 여부

            //직접 hp로 시작해 방들을 통과해본다.
            for (int i = 0; i < N; i++) {
                //몬스터 방인 경우
                if (roomInfo[i][0] == 1) {
                    //hp를 깎는 과정
                    long turns = (roomInfo[i][2] + atk - 1) / atk;  // 올림 나눗셈
                    long damage = roomInfo[i][1] * (turns - 1);    // 몬스터 공격 횟수는 -1
                    hp -= damage;

                    if (hp <= 0) {  //용사가 죽음 -> 조건을 불만족
                        alive = false;
                        break;  //start를 변경했으니 for문을 빠져나가야함
                    }
                } else {  //포션 방인 경우
                    atk += roomInfo[i][1];
                    hp = Math.min(mid, hp + roomInfo[i][2]);  //피 회복이 최대 체력까지만 가능!
                }
            }

            //생존 여부에 따라 start, end를 조정
            if (alive) {
                end = mid - 1;
            } else start = mid + 1;
        }

        System.out.println(start);
    }
}
