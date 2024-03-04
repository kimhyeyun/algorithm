import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, answer;
	static int[] start, end;
	static int[][] map;
	static final int[] dx = {-1, 0, 0, 1};
	static final int[] dy = {0, -1, 1, 0};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		start = new int[] {Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1};

		st = new StringTokenizer(br.readLine());
		end = new int[] {Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1};

		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		answer = Integer.MAX_VALUE;
		BFS();

		answer = answer == Integer.MAX_VALUE ? -1 : answer;
		System.out.println(answer);
	}

	private static void BFS() {
		Queue<int[]> q = new LinkedList<>();
		boolean[][][] isVisited = new boolean[N][M][2];

		q.add(new int[] {start[0], start[1], 0, 0});
		isVisited[start[0]][start[1]][0] = true;

		while (!q.isEmpty()) {
			int[] cur = q.poll();

			// if (cur[0] == end[0] && cur[1] == end[1]) return cur[3];

			for (int d = 0; d < 4; d++) {
				int nx = cur[0] + dx[d];
				int ny = cur[1] + dy[d];

				if (nx < 0 || ny < 0 || N <= nx || M <= ny) continue;
				if (nx == end[0] && ny == end[1]) {
					answer = Math.min(answer, cur[3] + 1);
					continue;
				}

				if (map[nx][ny] == 1) {
					if (cur[2] == 0 && !isVisited[nx][ny][1]) {
						q.add(new int[] {nx, ny, 1, cur[3] + 1});
						isVisited[nx][ny][1] = true;
					}
				} else if (!isVisited[nx][ny][cur[2]]){
					q.add(new int[] {nx, ny, cur[2], cur[3] + 1});
					isVisited[nx][ny][cur[2]] = true;
				}
			}
		}
	}
}
