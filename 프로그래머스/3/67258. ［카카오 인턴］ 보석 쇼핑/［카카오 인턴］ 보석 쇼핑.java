import java.util.*;
class Solution {
    public int[] solution(String[] gems) {
        int[] answer = new int[2];
        
        //보석의 종류의 개수 구하기
        Set<String> set = new HashSet<>(Arrays.asList(gems));
        int targetSize = set.size();
        
        Map<String, Integer> countMap = new HashMap<>();
        int left = 0;
        int right = 0;
        int startIndex = 0;
        int minLength = Integer.MAX_VALUE;
        
        while (right < gems.length) {
            String jewel = gems[right];
            if (countMap.containsKey(jewel)) {
                countMap.put(jewel, countMap.get(jewel)+1);
            } else {
                countMap.put(jewel, 1);
            }
            right++;
            
            //보석의 종류가 구간에 모두 포함된 경우
            while (countMap.size() == targetSize) {
                //최소구간이면 갱신
                int currLen = right - left;
                if (currLen < minLength) {
                    minLength = currLen;
                    startIndex = left;
                }
                
                //left를 움직여서 구간 줄이기
                countMap.put(gems[left], countMap.get(gems[left]) - 1);  //보석도 빼기
                if (countMap.get(gems[left]) == 0) {
                    countMap.remove(gems[left]);  //만약 보석의 개수가 0이면 아예 Map에서 지우기
                }                                 //그래야 Map크기와 Set크기를 비교
                left++;
            }
        }
        
        answer[0] = startIndex + 1;
        answer[1] = startIndex + minLength;
        return answer;
    }
}