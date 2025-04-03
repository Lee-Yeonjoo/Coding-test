import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String word = "";
        List<int[]> dic = new ArrayList<>();
        while (true) {
            word = br.readLine();

            if (word.equals("-")) {
                break;
            }

            int[] alphabetCount = new int[26];
            for (int i = 0; i < word.length(); i++) {
                alphabetCount[word.charAt(i)-65] += 1;
            }
            dic.add(alphabetCount);
        }

        while (true) {
            int[] answerCount = new int[26];
            String puzzle = br.readLine();

            if (puzzle.equals("#")) {
                break;
            }

            int[] puzzleAlphabetCount = new int[26];
            for (int i = 0; i < puzzle.length(); i++) {
                puzzleAlphabetCount[puzzle.charAt(i) - 65] += 1;
            }

            //사전의 단어를 순회
            for (int[] dicWord : dic) {

                boolean isPossible = true;
                //퍼즐로 사전의 단어를 만들 수 있는지 확인
                for (int i = 0; i < 26; i++) {
                    //하나라도 개수가 적은 알파벳이 있다면 사전의 단어를 만들 수 없음
                    if (puzzleAlphabetCount[i] < dicWord[i]) {
                        isPossible = false;
                        break;
                    }
                }

                //만들 수 있다면 그 사전의 알파벳을 카운트
                if (isPossible) {
                    for (int i = 0; i < 26; i++) {
                        if (dicWord[i] > 0) {
                            answerCount[i] += 1;
                        }
                    }
                }
            }

            //
            int max = Arrays.stream(answerCount).max().orElse(Integer.MIN_VALUE);
            int min = max;
            for (int i = 0; i < 26; i++) {
                if (puzzle.contains(String.valueOf((char) (i+65)))) {
                    if (min > answerCount[i]) {
                        min = answerCount[i];
                    }
                }
            }

            String maxAnswer = "";
            String minAnswer = "";
            for (int i = 0; i < 26; i++) {
                if (!puzzle.contains(String.valueOf((char) (i + 65)))) {
                    continue;
                }

                if (answerCount[i] == max) {
                    maxAnswer += (char) (i + 65);
                }

                if (answerCount[i] == min) {
                    minAnswer += (char) (i + 65);
                }
            }

            System.out.println(minAnswer + " " + min + " " + maxAnswer + " " + max);
        }
    }
}
