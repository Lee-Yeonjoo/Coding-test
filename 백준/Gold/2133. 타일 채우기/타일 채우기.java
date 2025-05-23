import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] dp = new int[N + 1];
		dp[0] = 1; 

		//짝수에 대해서만 구하면 된다. 홀수일 땐 경우의 수가 0 
		for (int i=2; i < N + 1; i += 2) {
			dp[i] = dp[i-2] * 3;
			for(int j = i-4; j >= 0; j-=2) {
				dp[i] += dp[j] * 2;
			}
		}
		System.out.println(dp[N]);
	}
}
