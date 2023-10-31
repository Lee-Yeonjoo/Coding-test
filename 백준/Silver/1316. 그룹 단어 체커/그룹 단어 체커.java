import java.util.Scanner;

public class Main {
    public static boolean grpCheck(String str) {
        int[] check = new int[30];
        for (int i = 0; i < str.length(); i++) {
            char x = str.charAt(i);
            if( i>0 && str.charAt(i-1)!=x) {
                if(check[x-97]==1)
                    return false;
            }
            check[x-97] = 1;
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        String str;
        int cnt = 0;

        sc.nextLine(); //N입력 후 엔터를 먹는 용도
        for (int i = 0; i < N; i++) {
            str = sc.nextLine();
            if(grpCheck(str)){
                cnt++;
            }
        }
        System.out.println(cnt);
    }
}