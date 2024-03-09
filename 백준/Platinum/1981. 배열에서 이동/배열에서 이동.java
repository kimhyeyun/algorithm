import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static int[][] map;
	static boolean[][] isVisited;
	static int min, max;
	static final int[] dx = {-1, 0, 0, 1};
	static final int[] dy = {0, -1, 1, 0};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		min = Integer.MAX_VALUE;
		max = Integer.MIN_VALUE;

		n = Integer.parseInt(br.readLine());
		map = new int[n][n];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());

				max = Math.max(max, map[i][j]);
				min = Math.min(min, map[i][j]);
			}
		}

		int left = 0, right = max - min;
		int answer = Integer.MAX_VALUE;

		while (left <= right) {
			int mid = (left + right) / 2;
			boolean flag = false;

			for (int i = min; i <= max; i++) {
				if (i <= map[0][0] && map[0][0] <= i + mid) {
					if (BFS(i, i + mid)) {
						flag = true;
						break;
					}
				}
			}
			if (flag) {
				right = mid - 1;
				answer = Math.min(answer, mid);
			} else {
				left = mid + 1;
			}
		}
		System.out.println(answer);
	}

	private static boolean BFS(int min, int max) {
		Queue<int[]> q = new LinkedList<>();
		isVisited = new boolean[n][n];

		q.add(new int[] {0, 0});
		isVisited[0][0] = true;

		while (!q.isEmpty()) {
			int[] cur = q.poll();

			if (cur[0] == n - 1 && cur[1] == n - 1)
				return true;

			for (int d = 0; d < 4; d++) {
				int nx = cur[0] + dx[d];
				int ny = cur[1] + dy[d];

				if (nx < 0 || ny < 0 || n <= nx || n <= ny || isVisited[nx][ny])
					continue;

				if (min <= map[nx][ny] && map[nx][ny] <= max) {
					isVisited[nx][ny] = true;
					q.add(new int[] {nx, ny});
				}
			}
		}
		return false;
	}
}
