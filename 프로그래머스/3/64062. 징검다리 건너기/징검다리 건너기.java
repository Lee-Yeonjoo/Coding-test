import java.util.*;
class Solution {
    public int solution(int[] stones, int k) {
        int answer = 0;
        
        int start = 1;
        int end = 0;
        for (int i=0; i<stones.length; i++) {
            end = Math.max(end, stones[i]);
        }
        
        //이분탐색 -> 몇 명이 건널지를 탐색
        while (start <= end) {
            int mid = (start + end) / 2;
            
            //n번 반복 -> 최종 시간복잡도 O(nlogm) n=배열의 길이, m=최대 사람 건너는 수(2억)
            int count = 0;
            boolean isValid = true;
            for (int i=0; i<stones.length; i++) {
                if (stones[i] - mid <= 0) {
                    count++;
                } else count = 0;  //연속이 아니므로 초기화
                
                if (count >= k) {  //연속 k번 이상이므로 false
                    isValid = false;
                }
            }
                        
            //연속 k번 0이하인 경우 -> 다리를 못 건넘 -> 인원 줄이기
            if (!isValid) {
                end = mid - 1;
            } else {  //다리를 건널 수 있음 -> 인원 늘리기
                answer = mid;
                start = mid + 1;
            }
        }
        return answer + 1;
    }
}