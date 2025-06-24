import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

//구현, 브루트포스, 조합
public class Main {
    static String[] str;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int L = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        str = new String[C];
        for (int i = 0; i < C; i++) {
            str[i] = st.nextToken();
        }

        //입력받은 알파벳 정렬
        Arrays.sort(str);

        //모음 Set 생성
        vowel.add('a');
        vowel.add('e');
        vowel.add('i');
        vowel.add('o');
        vowel.add('u');
        combination(0, 0, C, L);
    }

    //조합 - start 인덱스를 증가시켜 이미 조합에 쓴 문자를 제외하는 방식 
    static String output = "";
    static void combination(int start, int depth, int n, int r) {
        //조합 완성
        if (depth == r) {
            //자모음 개수 조건 체크
            if (isValid(output)) {
                System.out.println(output); 
            }
            return;
        }

        //조합 찾기
        for (int i = start; i < n; i++) {
            output += str[i];
            combination(i + 1, depth + 1, n, r);  //재귀로 다음 depth에 올 문자 찾기 
            output = output.substring(0, depth);  //백트래킹 - 현재 output 문자열의 맨 뒤 글자 제거
        }
    }

    //자음, 모음 개수 체크
    static Set<Character> vowel = new HashSet<>();
    static boolean isValid(String output) {
        int vowelCount = 0;  //모음 개수
        int consonantCount = 0;  //자음 개수
        for (int i = 0; i < output.length(); i++) {
            char x = output.charAt(i);
            //자모음 개수 세기
            if (vowel.contains(x)) {
                vowelCount++;
            }
            else consonantCount++;
        }

        //자모음 개수 만족하면 true
        if (vowelCount >= 1 && consonantCount >= 2) {
            return true;
        }
        return false;
    }
}
