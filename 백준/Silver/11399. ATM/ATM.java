import java.util.*;
public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] arr = new int[N];
        int sum=0;
        for(int i=0; i<N; i++){
            arr[i] = sc.nextInt();
        }
        insertionSort(arr);
        for(int i=0; i<N; i++){
            for(int j=0; j<=i; j++){
                sum+=arr[j];
            }
        }
        System.out.println(sum);
    }

    public static void insertionSort(int[] arr){
        int temp, min;
        for(int i=1; i<arr.length;i++){
            min = i;
            for(int j=i-1; j>=0; j--){
                if(arr[min]<arr[j]){
                    temp = arr[min];
                    arr[min] = arr[j];
                    arr[j] = temp;
                    min = j;
                }
            }
        }
    }
}