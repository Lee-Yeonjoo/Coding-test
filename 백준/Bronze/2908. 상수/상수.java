import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int A, B;

        String s = String.valueOf(sc.nextInt());
        StringBuilder sb = new StringBuilder(s);
        sb = sb.reverse();
        A = Integer.parseInt(sb.toString()); //A 뒤집기 완료

        s = String.valueOf(sc.nextInt());
        sb = new StringBuilder(s);
        sb = sb. reverse();
        B = Integer.parseInt(sb.toString()); //B 뒤집기 완료

        if (A > B) {
            System.out.println(A);
        }
        else System.out.println(B);
    }
}
