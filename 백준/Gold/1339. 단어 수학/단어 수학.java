import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//그리디
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] temp = new String[N];
        int maxLen = 0;
        for (int i = 0; i < N; i++) {
            temp[i] = br.readLine();
            maxLen = Math.max(maxLen, temp[i].length());
        }

        //2차원 배열에 단어들 저장
        char[][] words = new char[N][maxLen];
        for (int i = 0; i < N; i++) {
            int idx = maxLen - temp[i].length();
            int k = 0;
            for (int j = 0; j < maxLen; j++) {
                if (idx == j) {
                    words[i][j] = temp[i].charAt(k);
                    k++;
                    idx++;
                } else {
                    words[i][j] = '0';  //끝에서 길이를 맞추어 앞에 남는 자리에 0으로 채우기
                }
            }
        }

        Map<Character, Integer> map = new HashMap<>();  //Map에 알파벳에 해당하는 가중치 저장
        int weight = (int) Math.pow(10, maxLen - 1);
        //단어들을 한글자씩 순회하며 가중치 구하기
        for (int j = 0; j < maxLen; j++) {
            for (int i = 0; i < N; i++) {
                if (words[i][j] == '0') continue;  //2차원 배열에서 빈 부분은 패스

                //가중치 저장 -> 자릿수에 따른 가중치가 있고, 많이 나올수록 가중치만큼 더하기
                if (map.containsKey(words[i][j])) {
                    map.put(words[i][j], map.get(words[i][j]) + weight);
                } else {
                    map.put(words[i][j], weight);
                }
            }
            weight /= 10;
        }

        List<Map.Entry<Character, Integer>> alphabets = new ArrayList<>(map.entrySet());  //리스트에 옮기기
        alphabets.sort((a, b) -> Integer.compare(b.getValue(), a.getValue()));  //리스트 정렬 -> 가중치가 큰 순서대로

        int count = 9;
        for (Map.Entry<Character, Integer> alphabet : alphabets) {
            map.put(alphabet.getKey(), count--);  //가중치에 해당하는 값을 매핑
        }

        //매핑한 값에 따른 최종 합 구하기
        int sum = 0;
        int ten = (int) Math.pow(10, maxLen - 1);  //자릿수 맞추기 위한 변수
        for (int j = 0; j < maxLen; j++) {
            for (int i = 0; i < N; i++) {
                if (words[i][j] == '0') continue;  //2차원 배열에서 빈 부분은 패스
                sum += map.get(words[i][j]) * ten;
            }
            ten /= 10;
        }
        System.out.println(sum);
    }
}
