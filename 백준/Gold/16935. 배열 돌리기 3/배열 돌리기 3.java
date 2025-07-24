import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//구현
public class Main {
    static int N, M;
    static int[][] A;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        A = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        //번호에 맞는 연산 실행
        for (int i = 0; i < R; i++) {
            int num = Integer.parseInt(st.nextToken());
            switch (num) {
                case 1:
                    topDown();
                    break;
                case 2:
                    leftRight();
                    break;
                case 3:
                    rotateRight();
                    break;
                case 4:
                    rotateLeft();
                    break;
                case 5:
                    rotateSubRight();
                    break;
                case 6:
                    rotateSubLeft();
                    break;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[0].length; j++) {
                sb.append(A[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
        br.close();
    }

    //1번 연산
    static void topDown() {
        int N = A.length;
        int M = A[0].length;
        int[][] temp = new int[N][M];
        for (int i = 0; i < N; i++) {
            temp[i] = A[N - 1 - i];
        }
        A = temp;
    }

    //2번 연산
    static void leftRight() {
        int N = A.length;
        int M = A[0].length;
        for (int i = 0; i < N; i++) {
            int[] temp = new int[M];  //매 반복마다 새로운 temp 배열을 만들어야함! 안그러면 주소가 동일해서 모든 행이 같아진다.
            for (int j = 0; j < M; j++) {
                temp[j] = A[i][M - 1 - j];
            }
            A[i] = temp;
        }
    }

    //3번 연산
    static void rotateRight() {
        int N = A.length;
        int M = A[0].length;
        int[][] temp = new int[M][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                temp[j][N - 1 - i] = A[i][j];
            }
        }
        A = temp;
    }

    //4번 연산
    static void rotateLeft() {
        int N = A.length;
        int M = A[0].length;
        int[][] temp = new int[M][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                temp[j][i] = A[i][M - 1 - j];
            }
        }
        A = temp;
    }

    //5번 연산 - 시계방향
    static void rotateSubRight() {
        int N = A.length;
        int M = A[0].length;
        int n = A.length / 2;
        int m = A[0].length / 2;
        int[][] temp = new int[n][m];
        //1번 구역 temp에 저장
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                temp[i][j] = A[i][j];
            }
        }

        //4번 -> 1번
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                A[i][j] = A[i + n][j];
            }
        }

        //3번 -> 4번
        for (int i = n; i < N; i++) {
            for (int j = 0; j < m; j++) {
                A[i][j] = A[i][j + m];
            }
        }

        //2번 -> 3번
        for (int i = n; i < N; i++) {
            for (int j = m; j < M; j++) {
                A[i][j] = A[i - n][j];
            }
        }

        //1번(temp) -> 2번
        for (int i = 0; i < n; i++) {
            for (int j = m; j < M; j++) {
                A[i][j] = temp[i][j - m];
            }
        }
    }

    //6번 연산 - 반시계방향
    static void rotateSubLeft() {
        int N = A.length;
        int M = A[0].length;
        int n = A.length / 2;
        int m = A[0].length / 2;
        int[][] temp = new int[n][m];
        //1번 구역 temp에 저장
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                temp[i][j] = A[i][j];
            }
        }

        //2번 -> 1번
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                A[i][j] = A[i][j + m];
            }
        }

        //3번 -> 2번
        for (int i = 0; i < n; i++) {
            for (int j = m; j < M; j++) {
                A[i][j] = A[i + n][j];
            }
        }

        //4번 -> 3번
        for (int i = n; i < N; i++) {
            for (int j = m; j < M; j++) {
                A[i][j] = A[i][j - m];
            }
        }

        //1번(temp) -> 4번
        for (int i = n; i < N; i++) {
            for (int j = 0; j < m; j++) {
                A[i][j] = temp[i - n][j];
            }
        }
    }
}
