import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] A = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        quickSort(A, 0, A.length-1);

        sb.append(A[K - 1]);
        System.out.println(sb);
    }

    public static void quickSort(int[] a, int low, int high) {
        if(low>=high) return;
        int pivot = partition(a, low, high);
        quickSort(a, low, pivot);
        quickSort(a, pivot + 1, high);
    }

    /*public static int partition(int[] a, int left, int right) {
        int low = left;
        int high = right;
        int pivot = a[left];

        while (low < high) {
            while (a[high] > pivot && low < high) {
                high--;
            }

            while (a[low] <= pivot && low < high) {
                low++;
            }

            swap(a, low, high);
        }

        swap(a, left, low);

        return low;
    }*/

    private static int partition(int[] a, int left, int right) {

        // lo와 hi는 각각 배열의 끝에서 1 벗어난 위치부터 시작한다.
        int lo = left - 1;
        int hi = right + 1;
        int pivot = a[(left + right) / 2];		// 부분리스트의 중간 요소를 피벗으로 설정


        while(true) {

            /*
             * 1 증가시키고 난 뒤의 lo 위치의 요소가 pivot보다 큰 요소를
             * 찾을 떄 까지 반복한다.
             */
            do {
                lo++;
            } while(a[lo] < pivot);


            /*
             * 1 감소시키고 난 뒤의 hi 위치가 lo보다 크거나 같은 위치이면서
             * hi위치의 요소가 pivot보다 작은 요소를 찾을 떄 까지 반복한다.
             */
            do {
                hi--;
            } while(a[hi] > pivot && lo <= hi);


            /*
             * 만약 hi가 lo보다 크지 않다면(엇갈린다면) swap하지 않고 hi를 리턴한다.
             */
            if(lo >= hi) {
                return hi;
            }


            // 교환 될 두 요소를 찾았으면 두 요소를 바꾼다.
            swap(a, lo, hi);
        }

    }

    public static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
