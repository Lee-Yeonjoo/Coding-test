import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Main {
	 public static void main(String[] args) throws IOException {
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 int N = Integer.parseInt(br.readLine());
		 
		 int[] seq = new int[N];
		 int[] dp = new int[N];  //LIS 크기를 저장
		 String[] dpSeq = new String[N];  //LIS 문자열을 저장
		 StringTokenizer st = new StringTokenizer(br.readLine());
		 for (int i=0; i<N; i++) {
			 seq[i] = Integer.parseInt(st.nextToken());
			 dpSeq[i] = "" + seq[i];
		 }
		 
		 Arrays.fill(dp, 1);
		 for(int i=1; i<N; i++) {
			 for(int j=i-1; j >= 0; j--) {
				 //i의 수보다 작고, LIS의 크기가 더 크다면 dp갱신, LIS 문자열 추가
				 if (seq[i] > seq[j] && dp[i] < dp[j] + 1) {
					 dp[i] = dp[j] + 1;
					 dpSeq[i] = dpSeq[j] + " " + seq[i];
				}
			 }
		 }
		 
		 //가장 긴 LIS와 그 수열 구하기
		 int maxLen = 0;
		 String maxSeq = "";
		 for (int i = 0; i < N; i++) {
			 if (dp[i] > maxLen) {
					maxLen = dp[i];
					maxSeq = dpSeq[i];			
				}
		}
		
		 System.out.println(maxLen);
		 System.out.println(maxSeq);
	}
}
