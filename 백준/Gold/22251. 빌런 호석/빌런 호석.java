import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

//브루트포스
public class Main {
    static int range, digit, maxInversion, currFloor;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        range = Integer.parseInt(st.nextToken());
        digit = Integer.parseInt(st.nextToken());
        maxInversion = Integer.parseInt(st.nextToken());
        currFloor = Integer.parseInt(st.nextToken());
        int answer = 0;
        boolean[][] currLED = changeToLED(currFloor);  //현재 층을 LED로 변환

        //현재 층과 모든 층의 LED를 비교
        for (int i = 1; i <= range; i++) {
            //현재 층과 같은 층은 패스
            if (i == currFloor) continue;

            boolean[][] tempLED = changeToLED(i);  //비교할 층도 LED로 변환
            //두 LED를 비교
            int inversionCount = 0;
            for (int j = 0; j < digit; j++) {
                for (int k = 0; k < 7; k++) {
                    if (currLED[j][k] != tempLED[j][k]) {
                        inversionCount++;
                    }
                }
            }

            //반전해야할 LED의 수가 maxInversion보다 작거나 같아야 함
            if (inversionCount <= maxInversion) {
                answer++;
            }
        }
        System.out.println(answer);
    }

    //숫자를 LED 숫자로 변환하는 함수
    static boolean[][] changeToLED(int num) {
        boolean[][] ledNums = new boolean[digit][7];

        //각 자릿수를 led 구성 숫자로 변환하기
        int digitIdx = 0;
        while (num != 0) {
            int temp = num % 10;
            num /= 10;

            if (temp == 0) {
                ledNums = mark(new int[]{0, 1, 2, 4, 5, 6}, ledNums, digitIdx);

            } else if (temp == 1) {
                ledNums = mark(new int[]{2, 5}, ledNums, digitIdx);

            } else if (temp == 2) {
                ledNums = mark(new int[]{0, 2, 3, 4, 6}, ledNums, digitIdx);

            } else if (temp == 3) {
                ledNums = mark(new int[]{0, 2, 3, 5, 6}, ledNums, digitIdx);

            } else if (temp == 4) {
                ledNums = mark(new int[]{1, 2, 3, 5}, ledNums, digitIdx);

            } else if (temp == 5) {
                ledNums = mark(new int[]{0, 1, 3, 5, 6}, ledNums, digitIdx);

            } else if (temp == 6) {
                ledNums = mark(new int[]{0, 1, 3, 4, 5, 6}, ledNums, digitIdx);

            } else if (temp == 7) {
                ledNums = mark(new int[]{0, 2, 5}, ledNums, digitIdx);

            } else if (temp == 8) {
                ledNums = mark(new int[]{0, 1, 2, 3, 4, 5, 6}, ledNums, digitIdx);

            } else if (temp == 9) {
                ledNums = mark(new int[]{0, 1, 2, 3, 5, 6}, ledNums, digitIdx);
            }
            digitIdx++;
        }
        //남은 앞 자리 수를 0으로 채우기
        if (digitIdx < digit) {
            int remain = digit - digitIdx;
            for (int i = 0; i < remain; i++) {
                ledNums = mark(new int[]{0, 1, 2, 4, 5, 6}, ledNums, digitIdx);
                digitIdx++;
            }
        }
        return ledNums;
    }

    //LED로 변환할 때, 배열에 true로 표시하는 함수
    static boolean[][] mark(int[] ledNumList, boolean[][] ledNums, int digitIdx) {

        for (int num : ledNumList) {
            ledNums[digitIdx][num] = true;
        }
        return ledNums;
    }
}
