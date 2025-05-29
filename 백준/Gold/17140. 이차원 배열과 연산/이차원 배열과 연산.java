import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//구현, 정렬
public class Main {
    static int[][] A;
    static class Value {
        int num;
        int count;
        public Value(int num, int count) {
            this.num = num;
            this.count = count;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        A = new int[3][3];
        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //100초까지
        for (int i = 0; i <= 100; i++) {
            if ((r-1) < A.length && (c-1) < A[0].length && A[r - 1][c - 1] == k) {
                System.out.println(i);
                System.exit(0);
            }

            //행 크기와 열 크기 비교
            //행이 더 큰 경우 행 정렬
            if (A.length >= A[0].length) {
                RowSort();
            } else {  //열 정렬
                ColSort();
            }
        }
        System.out.println(-1);
    }

    //각 행을 정렬
    static void RowSort() {
        int maxSize = 3;
        ArrayList<ArrayList<Value>> sortList = new ArrayList<>();
        for (int i = 0; i < A.length; i++) {
            sortList.add(new ArrayList<>());
            int[] count = new int[101];
            int j = 0;
            //행의 각 값의 개수 세기
            while (j < A[i].length) {
                if (A[i][j] != 0) count[A[i][j]]++;  //0은 세지 않는다.
                j++;
            }

            //값의 개수를 ArrayList에 옮기기
            for (int k = 1; k <= 100; k++) {
                if (count[k] != 0) {
                    sortList.get(i).add(new Value(k, count[k]));
                }
            }

            //ArrayList를 정렬 - 먼저 개수에 대해 오름차순 정렬한 후, 동일할 땐 값에 대해 오름차순 정렬
            sortList.get(i).sort((o1, o2) -> {  //람다 표현식으로 정렬의 다중 조건 지정
                if (o1.count < o2.count) return -1;
                else if (o1.count == o2.count) {
                    return Integer.compare(o1.num, o2.num);
                } else return 1;
            });

            //행의 최대 크기 찾기
            maxSize = Math.max(maxSize, sortList.get(i).size() * 2);
        }

        //크기 통일 - A 새로 선언
        A = new int[A.length][maxSize];
        //값 채우기
        for (int i = 0; i < sortList.size(); i++) {
            int k = 0;  //A의 열을 가리키는 인덱스
            for (int j = 0; j < sortList.get(i).size(); j++) {
                A[i][k] = sortList.get(i).get(j).num;
                A[i][k + 1] = sortList.get(i).get(j).count;
                k += 2;
            }
        }
    }

    //각 열을 정렬
    static void ColSort() {
        int maxSize = 3;
        ArrayList<ArrayList<Value>> sortList = new ArrayList<>();  //얘한텐 행과 열 바꿔서 저장
        for (int j = 0; j < A[0].length; j++) {
            sortList.add(new ArrayList<>());
            int[] count = new int[101];
            int i = 0;
            //행의 각 값의 개수 세기
            while (i < A.length) {
                if (A[i][j] != 0) count[A[i][j]]++;  //0은 세지 않는다.
                i++;
            }

            for (int k = 1; k <= 100; k++) {
                if (count[k] != 0) {
                    sortList.get(j).add(new Value(k, count[k]));
                }
            }
            
            //정렬
            sortList.get(j).sort((o1, o2) -> {  //람다 표현식으로 정렬의 다중 조건 지정
                if (o1.count < o2.count) return -1;
                else if (o1.count == o2.count) {
                    return Integer.compare(o1.num, o2.num);
                } else return 1;
            });

            //최대 크기 찾기
            maxSize = Math.max(maxSize, sortList.get(j).size() * 2);
        }

        //크기 통일 - A 새로 선언
        A = new int[maxSize][A[0].length];
        //값 채우기
        for (int i = 0; i < sortList.size(); i++) {
            int k = 0;  //A의 행을 가리키는 인덱스
            for (int j = 0; j < sortList.get(i).size(); j++) {
                A[k][i] = sortList.get(i).get(j).num;
                A[k + 1][i] = sortList.get(i).get(j).count;
                k += 2;
            }
        }
    }
}
