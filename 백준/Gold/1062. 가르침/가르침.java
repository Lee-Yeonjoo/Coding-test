import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//브루트 포스, 비트 마스킹
public class Main {
    static int learned;
    static int K;
    static int[] words;
    static int maxCount;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        words = new int[N];
        for (int i = 0; i < N; i++) {
            String word = br.readLine();
            //쉬프트 연산과 비트 연산으로 해당 알파벳만 1로 표시한 이진수 만들기
            for (char c : word.toCharArray()) {
                words[i] = words[i] | (1 << (c - 'a'));
            }
        }

        //예외처리
        if (K < 5) {
            System.out.println(0);
            return;
        }

        //antic은 무조건 배워야 하는 문자 -> 이진수로 변환
        for (char c : "antic".toCharArray()) {
            learned |= (1 << (c - 'a'));
        }

        combination(0, 0);
        System.out.println(maxCount);
    }

    //(k-5)개의 알파벳을 뽑기 위한 함수
    static void combination(int depth, int start) {
        //(k-5)개를 다 뽑은 경우
        if (depth == K - 5) {
            //비트 연산해서 읽을 수 있는 단어 카운트
            countReadableWord();
            return;
        }

        for (int i = start; i < 26; i++) {
            //이미 배운 a,n,t,i,c은 무시
            if (i == 0 || i == 2 || i == 8 || i == 13 || i == 19) continue;

            //뽑은 단어를 learned에 추가
            int temp = learned;
            learned |= (1 << i);
            combination(depth + 1, i + 1);  //start 아니고 i값을 인자로
            learned = temp;  //백트래킹
        }
    }

    //읽을 수 있는 단어 카운트하는 함수
    static void countReadableWord() {
        int count = 0;
        for (int word : words) {
            if ((~learned & word) == 0) {  //learned에 word가 포함되는지를 봐야하므로 learned를 반전시켜야함!
                count++;
            }
        }
        maxCount = Math.max(maxCount, count);  //단어 개수 최댓값 갱신
    }
}
