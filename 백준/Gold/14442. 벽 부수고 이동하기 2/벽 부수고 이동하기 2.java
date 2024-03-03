import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, K;
	static int[][] map;
	static final int[] dx = {-1, 0, 0, 1};
	static final int[] dy = {0, -1, 1, 0};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j) - '0';
			}
		}

		System.out.println(BFS(0, 0));
	}

	private static int BFS(int x, int y) {
		Queue<int[]> q = new LinkedList<>();
		boolean[][][] isVisited = new boolean[N][M][K + 1];


		q.add(new int[] {x, y, K, 1});
		isVisited[x][y][K] = true;

		while (!q.isEmpty()) {
			int[] cur = q.poll();

			if (cur[0] == N - 1 && cur[1] == M - 1) return cur[3];

			for (int d = 0; d < 4; d++) {
				int nx = cur[0] + dx[d];
				int ny = cur[1] + dy[d];

				if (nx < 0 || ny < 0 || N <= nx || M <= ny || isVisited[nx][ny][cur[2]]) continue;

				if (map[nx][ny] == 0) {
					isVisited[nx][ny][cur[2]] = true;
					q.add(new int[] {nx, ny, cur[2], cur[3] + 1});
				} else {
					if (cur[2] > 0) {
						isVisited[nx][ny][cur[2]] = true;
						q.add(new int[] {nx, ny, cur[2] - 1, cur[3] + 1});
					}
				}
			}
		}
		return -1;

	}
}
