import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int result = 0;
        String[] str = sc.nextLine().split("-"); //-를 기준으로 문자열을 쪼갠다.

        /*for (int i = 0; i < str.length; i++) {
            System.out.println(str[i]);
        }*/

        for (int i = 0; i < str.length; i++) {
            String[] add = str[i].split("\\+"); //+를 기준으로 쪼개기.

            int sum = 0; //+를 기준으로 쪼갠 후 그 합을 구할 변수.
            for (int j = 0; j < add.length; j++) {
                sum += Integer.parseInt(add[j]);
            }

            if (i == 0) {
                result = sum;
            }
            else result -= sum;
        }

        System.out.println(result);
    }
}