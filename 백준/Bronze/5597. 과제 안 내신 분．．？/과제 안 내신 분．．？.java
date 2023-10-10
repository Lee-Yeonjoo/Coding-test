import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] arr = new int[31];  //자바는 기본적으로 int,short,byte,long 배열은 0으로 초기화.

        int x;
        for (int i = 1; i <= 28; i++) {
            x = sc.nextInt();
            arr[x] = 1;
        }

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == 0) {
                System.out.println(i);
            }
        }
    }
}
