import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
/*
Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        Arr[] arr = new Arr[N];

        for (int i = 0; i < N; i++) {
            int data = sc.nextInt();
            arr[i] = new Arr(i, data);
        }
*/

        //이렇게 해야 메모리 초과가 안나는듯?
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Arr[] arr = new Arr[N];
        for (int i = 0; i < N; i++) {
            arr[i] = new Arr(i, Integer.parseInt(br.readLine()));
        }

        Arrays.sort(arr);  //Comparable 인터페이스의 구현으로 Arr의 data기준으로 정렬

        int max=0;
        for (int i = 0; i < N; i++) {
            if(max < arr[i].getOldIdx()-i)
                max = arr[i].getOldIdx()-i;

        }

        System.out.println(max+1);
    }
}

class Arr implements Comparable<Arr> {
    private int oldIdx;
    private int data;

    public Arr(int oldIdx, int data) {
        super();
        this.oldIdx = oldIdx;
        this.data = data;
    }

    public int getOldIdx() {
        return this.oldIdx;
    }

    public int getData() {
        return this.data;
    }

    @Override
    public int compareTo(Arr o) {
        Arr a = (Arr) o;
        if(this.data < a.data){
            return -1;
        } else if (this.data == a.data) {
            return 0;
        }else{
            return 1;}
    }
}
