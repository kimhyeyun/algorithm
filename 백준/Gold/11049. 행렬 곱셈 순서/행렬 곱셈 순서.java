import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		int[][] mat = new int[N][2];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			mat[i][0] = a;
			mat[i][1] = b;
		}

		System.out.println(sol(mat));
	}

	private static int sol(int[][] mat) {
		int size = mat.length;
		int[][] dp = new int[size][size];

		for (int i = 0; i < size - 1; i++) {
			dp[i][i + 1] = mat[i][0] * mat[i][1] * mat[i + 1][1];
		}

		for (int gap = 2; gap < size; gap++) {
			for (int start = 0; start + gap < size; start++) {
				int end = start + gap;

				dp[start][end] = Integer.MAX_VALUE;

				for (int mid = start; mid < end; mid++) {
					dp[start][end] = Math.min(dp[start][end],
						dp[start][mid] + dp[mid + 1][end] + (mat[start][0] * mat[mid][1] * mat[end][1]));
				}
			}
		}

		return dp[0][size - 1];
	}
}
