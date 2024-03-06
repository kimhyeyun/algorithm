import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int test_case = Integer.parseInt(br.readLine());
		int K;
		int[] files;
		int[][] dp;

		while (test_case-- > 0) {
			K = Integer.parseInt(br.readLine());
			files = new int[K + 1];

			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= K; i++) {
				files[i] = Integer.parseInt(st.nextToken()) + files[i - 1];
			}

			dp = new int[K + 1][K + 1];
			for (int gap = 1; gap < K; gap++) {
				for (int start = 1; start + gap <= K; start++) {
					int end = start + gap;

					dp[start][end] = Integer.MAX_VALUE;
					for (int mid = start; mid < end; mid++) {
						dp[start][end] = Math.min(dp[start][end],
							(dp[start][mid] + dp[mid + 1][end] + files[end] - files[start - 1]));
					}
				}
			}

			sb.append(dp[1][K]).append("\n");
		}
		System.out.println(sb);
	}
}
