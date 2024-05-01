import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());

		int INF = Integer.MAX_VALUE;

		int[][] floyd = new int[n + 1][n + 1];
		for (int i = 0; i <= n; i++) {
			Arrays.fill(floyd[i], INF);
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			floyd[a][b] = Math.min(floyd[a][b], c);
		}


		for (int mid = 1; mid <= n; mid++) {
			for (int start = 1; start <= n; start++) {
				for (int end = 1; end <= n; end++) {
					if (start == end) continue;
					if (floyd[start][mid] == INF || floyd[mid][end] == INF) continue;
					
					floyd[start][end] = Math.min(floyd[start][end], floyd[start][mid] + floyd[mid][end]);
				}
			}
		}

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (floyd[i][j] == INF)
					System.out.print(0 + " ");
				else
					System.out.print(floyd[i][j] + " ");
			}
			System.out.println();
		}
	}
}
