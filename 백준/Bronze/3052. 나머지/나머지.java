import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int cnt = 0;
        int index;
        ArrayList<Boolean> remainder = new ArrayList<>(); //나머지의 여부를 체크하기 위한 리스트.
        for (int i = 0; i < 42; i++) {
            remainder.add(false); //어레이 리스트를 false로 초기화.
        }
        //System.out.println(remainder.get(35));

        for (int i = 0; i < 10; i++) {
            index = (Integer.parseInt(br.readLine()))%42;  //입력받은 값을 42로 나누어 나머지를 구한다.
            /*remainder.remove(index);
            remainder.add(index, true);*/
            remainder.set(index, true);
        }

        for (int i = 0; i < remainder.size(); i++) {
            if (remainder.get(i) == true) {
                cnt++;
            }
        }

        System.out.println(cnt);
    }
}