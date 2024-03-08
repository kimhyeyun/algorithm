import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		StringTokenizer st2 = new StringTokenizer(br.readLine());

		int[] mem = new int[N];
		int[] cost = new int[N];
		int[][] dp = new int[N][10001];

		for (int i = 0; i < N; i++) {
			mem[i] = Integer.parseInt(st.nextToken());
			cost[i] = Integer.parseInt(st2.nextToken());
		}

		int answer = Integer.MAX_VALUE;

		for (int i = 0; i < N; i++) {
			int curCost = cost[i];
			int curMem = mem[i];

			for (int j = 0; j <= 10000; j++) {
				if (i == 0) {
					if (j >= curCost) dp[i][j] = curMem;
				} else {
					if (j >= curCost)
						dp[i][j] = Math.max(dp[i - 1][j - curCost] + curMem, dp[i - 1][j]);
					else
						dp[i][j] = dp[i - 1][j];
				}

				if (dp[i][j] >= M)
					answer = Math.min(answer, j);
			}
		}

		System.out.println(answer);
	}
}
