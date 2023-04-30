import java.util.*;
public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int N2 = N;
        int i =0;
        while(N2!=0){
            N2/=10;
            i++;
        }
        int[] arr = new int[i];
        i=0;
        while(N!=0){
            arr[i] = N%10;
            N/=10;
            i++;
        }

        bubbleSort(arr);
        for(int j=0; j<arr.length;j++){
            System.out.print(arr[j]);
        }
    }
    public static void bubbleSort(int[] arr) { //내림차순 버블소트
        for(int i = 0; i < arr.length; i++) {
            for(int j = 0 ; j < arr.length - i - 1 ; j++) {
                if(arr[j] < arr[j+1]) {
                    int temp = arr[j+1];
                    arr[j+1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }

}
