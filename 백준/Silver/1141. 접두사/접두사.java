import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        List<String> words = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            words.add(br.readLine());
        }

        //글자 수를 기준으로 정렬 
        Collections.sort(words, new StringComparator());

        int count = 0;  //접두어가 맞는 경우를 세는 변수 
        for (int i = 0; i < words.size(); i++) {
            //뒤의 단어들과 비교 
            for (int j = i + 1; j < words.size(); j++) {
                boolean isPrefix = true;
                for (int k = 0; k < words.get(i).length(); k++) {
                    //접두가 아닌 경우
                    if (words.get(i).charAt(k) != words.get(j).charAt(k)) {
                        isPrefix = false;
                        break;
                    }
                }
                
                if (isPrefix) {
                    count ++;
                    break;
                }
            }
        }

        System.out.println(N-count);
    }
}

class StringComparator implements Comparator<String> {


    @Override
    public int compare(String o1, String o2) {
        
        if (o1.length() < o2.length()) {
            return -1;
        } else if (o1.length() > o2.length()) {
            return 1;
        } else {  //글자 수가 같을 경우 사전 순으로 
            return o1.compareTo(o2);
        }
    }
}

