import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//구현, 순열, 브루트포스, 백트래킹
public class Main {
    static int N, M, K;
    static boolean[] visited;  //순열 구할 때 방문 여부
    static int[][] rotation;  //회전 연산 저장
    static int[] sequence;  //순열로 구한 회전 연산 순서를 저장
    static int[][] arr;  //배열 저장
    static int[][] arrCopy;  //배열 복사본 저장
    static int min = Integer.MAX_VALUE;  //최종 답 - 배열의 최솟값 저장
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        arrCopy = new int[N][M];

        //회전 연산 저장
        rotation = new int[K][3];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                rotation[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        visited = new boolean[K];
        sequence = new int[K];

        permutation(0);
        System.out.println(min);
    }

    //재귀와 백트래킹으로 순열 구현
    static void permutation(int depth) {
        //깊이가 K가 되면 순열 완성 - 회전 연산 실행
        if (depth == K) {
            rotate();  //구한 순서로 회전 연산
            return;
        }

        for (int i = 0; i < K; i++) {
            if (!visited[i]) {
                visited[i] = true;
                sequence[depth] = i;  //순서 저장
                permutation(depth + 1);  //다음 순서에 올 값 구하기
                visited[i] = false;  //다른 순서를 정하기 위해 백트래킹
            }
        }
    }

    //회전 연산
    static void rotate() {
        //arr복사본 딥 카피로 초기화
        for (int a = 0; a < N; a++) {  //2차원 배열의 deep copy는 반복문을 통해 할 수 있다! 주의!!
            arrCopy[a] = arr[a].clone();
        }

        //sequence에 저장한 순서대로 회전
        for (int i = 0; i < K; i++) {
            int idx = sequence[i];
            //정사각형 좌표 구하기
            int r1 = rotation[idx][0] - rotation[idx][2] - 1;  //0,0 기준이라 1씩 빼주기
            int c1 = rotation[idx][1] - rotation[idx][2] - 1;
            int r2 = rotation[idx][0] + rotation[idx][2] - 1;
            int c2 = rotation[idx][1] + rotation[idx][2] - 1;

            //바깥쪽부터 회전
            while (r1 != r2) {  //가장 안쪽은 r1 == r2이고, 회전할 필요x
                int temp = arrCopy[r1][c1];
                //정사각형의 왼쪽 변의 값들 옮기기
                for (int j = r1; j < r2; j++) {
                    arrCopy[j][c1] = arrCopy[j + 1][c1];
                }

                //아래쪽 값 옮기기
                for (int j = c1; j < c2; j++) {
                    arrCopy[r2][j] = arrCopy[r2][j + 1];
                }

                //오른쪽 값 옮기기
                for (int j = r2; j > r1; j--) {
                    arrCopy[j][c2] = arrCopy[j - 1][c2];
                }

                //위쪽 값 옮기기
                for (int j = c2; j > c1; j--) {
                    arrCopy[r1][j] = arrCopy[r1][j - 1];
                }
                arrCopy[r1][c1 + 1] = temp;

                //좌표 이동
                r1++;
                c1++;
                r2--;
                c2--;
            }
        }

        //회전 후, 합 구해서 배열의 값 구하기
        findArrMinValue();
    }

    //배열의 값 구해서 최소값 갱신
    static void findArrMinValue() {
        for (int i = 0; i < N; i++) {
            int sum = 0;
            for (int j = 0; j < M; j++) {
                sum += arrCopy[i][j];
            }

            //최소값 갱신
            if (min > sum) {
                min = sum;
            }
        }
    }
}
