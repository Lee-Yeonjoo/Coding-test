import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

//구현
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String ticTacToe = br.readLine();
        StringBuilder sb = new StringBuilder();
        while (!ticTacToe.equals("end")) {
            Set<Integer> xList = new HashSet<>();
            Set<Integer> oList = new HashSet<>();
            for (int i = 0; i < ticTacToe.length(); i++) {
                char temp = ticTacToe.charAt(i);
                if (temp == 'X') {
                    xList.add(i);
                } else if (temp == 'O') {
                    oList.add(i);
                }
            }
            int[] count = countTicTacToe(xList, oList);

            //다 채워진 경우
            if (xList.size() == 5 && oList.size() == 4) {
                //비긴 경우 or x가 1개 만족, o는 만족x
                if ((count[0] == 0 && count[1] == 0) || (count[0] == 1 && count[1] == 0)) {
                    sb.append("valid").append("\n");
                }
                //x가 2개 만족, o는 만족x
                else if (count[0] == 2 && count[1] == 0) {
                    sb.append("valid").append("\n");
                } else {
                    sb.append("invalid").append("\n");
                }
            }

            //x가 이긴 경우
            else if (xList.size() - oList.size() == 1) {
                if (count[0] == 1 && count[1] == 0) {
                    sb.append("valid").append("\n");
                } else {
                    sb.append("invalid").append("\n");
                }
            }

            //o가 이긴 경우
            else if (xList.size() == oList.size()) {
                if (count[0] == 0 && count[1] == 1) {
                    sb.append("valid").append("\n");
                } else {
                    sb.append("invalid").append("\n");
                }
            } else {
                sb.append("invalid").append("\n");
            }
            ticTacToe = br.readLine();
        }
        System.out.println(sb);
    }

    //가로, 세로, 대각선 만족하는 것의 개수를 반환
    static int[] countTicTacToe(Set<Integer> xList, Set<Integer> oList) {
        int[] count = new int[2];

        //가로 만족 개수 세기
        for (int i = 0; i <= 6; i += 3) {
            boolean x = true;
            boolean o = true;
            for (int j = i; j < i + 3; j++) {
                if (!xList.contains(j) && x) x = false;
                if (!oList.contains(j) && o) o = false;
            }
            if (x) count[0]++;
            if (o) count[1]++;
        }

        //세로 만족 개수 세기
        for (int i = 0; i <= 2; i++) {
            boolean x = true;
            boolean o = true;
            for (int j = i; j <= i + 6; j += 3) {
                if (!xList.contains(j) && x) x = false;
                if (!oList.contains(j) && o) o = false;
            }
            if (x) count[0]++;
            if (o) count[1]++;
        }

        //대각선 만족 개수 세기
        boolean x = true;
        boolean o = true;
        for (int i = 0; i <= 8; i += 4) {
            if (!xList.contains(i) && x) x = false;
            if (!oList.contains(i) && o) o = false;
        }
        if (x) count[0]++;
        if (o) count[1]++;

        x = true;
        o = true;
        for (int i = 2; i <= 6; i += 2) {
            if (!xList.contains(i) && x) x = false;
            if (!oList.contains(i) && o) o = false;
        }
        if (x) count[0]++;
        if (o) count[1]++;
        return count;
    }
}
