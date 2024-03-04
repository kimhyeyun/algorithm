import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, answer;
	static int[][] map;
	static boolean[][] isPlanted;
	static final int[] dx = {-1, 0, 0, 1};
	static final int[] dy = {0, -1, 1, 0};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		answer = Integer.MAX_VALUE;

		map = new int[N][N];
		isPlanted = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		DFS(0, 0);
		System.out.println(answer);
	}

	private static void DFS(int count, int sum) {
		if (count == 3) {
			answer = Math.min(answer, sum);
			return;
		}

		for (int i = 1; i < N - 1; i++) {
			for (int j = 1; j < N - 1; j++) {
				if (isPlanted[i][j] || !isPossible(i,j)) continue;

				isPlanted[i][j] = true;
				DFS(count + 1, sum + map[i][j] + getCost(i, j));

				isPlanted[i][j] = false;
				clear(i, j);
			}
		}
	}

	private static void clear(int x, int y) {
		for (int d = 0; d < 4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];

			isPlanted[nx][ny] = false;
		}
	}

	private static int getCost(int x, int y) {
		int sum = 0;
		for (int d = 0; d < 4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];

			isPlanted[nx][ny] = true;
			sum += map[nx][ny];
		}
		return sum;
	}

	private static boolean isPossible(int x, int y) {
		for (int d = 0; d < 4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];

			if (nx < 0 || ny < 0 || N <= nx || N <= ny) return false;
			if (isPlanted[nx][ny]) return false;
		}
		return true;
	}
}
