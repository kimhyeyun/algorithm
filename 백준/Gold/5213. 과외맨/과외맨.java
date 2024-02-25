import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static class Pos {
		int x, y;
		List<Integer> path;

		public Pos(int x, int y, List<Integer> path) {
			this.x = x;
			this.y = y;
			this.path = path;
		}
	}

	static int N;
	static int[][] map, numberOfTile;
	static boolean[][] isVisited;
	static final int[] dx = {-1, 0, 0, 1};
	static final int[] dy = {0, -1, 1, 0};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		map = new int[N + 1][2 * N + 1];
		numberOfTile = new int[N + 1][2 * N + 1];
		isVisited = new boolean[N + 1][2 * N + 1];

		int index = 1;
		for (int i = 1; i < N + 1; i++) {
			if (i % 2 == 1) {
				for (int j = 1; j < 2 * N + 1; j += 2) {
					st = new StringTokenizer(br.readLine());

					int a = Integer.parseInt(st.nextToken());
					int b = Integer.parseInt(st.nextToken());

					map[i][j] = a;
					map[i][j + 1] = b;

					numberOfTile[i][j] = numberOfTile[i][j + 1] = index++;
				}

			} else {
				for (int j = 2; j < 2 * N; j += 2) {
					st = new StringTokenizer(br.readLine());

					int a = Integer.parseInt(st.nextToken());
					int b = Integer.parseInt(st.nextToken());

					map[i][j] = a;
					map[i][j + 1] = b;

					numberOfTile[i][j] = numberOfTile[i][j + 1] = index++;
				}
			}
		}

		Pos answer = BFS(1, 1);
		System.out.println(answer.path.size());

		for (int i : answer.path) {
			System.out.print(i + " ");
		}
	}

	private static Pos BFS(int x, int y) {
		Pos answer = null;
		int max = 0;

		Queue<Pos> q = new LinkedList<>();
		List<Integer> path = new ArrayList<>();

		path.add(1);

		q.add(new Pos(x, y, path));
		q.add(new Pos(x, y + 1, path));

		isVisited[x][y] = true;
		isVisited[x][y + 1] = true;

		while (!q.isEmpty()) {
			Pos cur = q.poll();

			int curX = cur.x;
			int curY = cur.y;
			List<Integer> curPath = cur.path;

			if (numberOfTile[curX][curY] > max) {
				max = numberOfTile[curX][curY];
				answer = cur;
			}

			for (int d = 0; d < 4; d++) {
				int nx = curX + dx[d];
				int ny = curY + dy[d];

				if (nx < 1 || ny < 1 || N < nx || 2 * N < ny) continue;
				if (map[nx][ny] == 0 || isVisited[nx][ny]) continue;

				if (map[curX][curY] == map[nx][ny]) {
					List<Integer> tmp = new ArrayList<>();
					tmp.addAll(curPath);
					tmp.add(numberOfTile[nx][ny]);

					q.add(new Pos(nx, ny, tmp));
					isVisited[nx][ny] = true;

					if (numberOfTile[nx][ny] == numberOfTile[nx][ny + 1]) {
						q.add(new Pos(nx, ny + 1, tmp));
						isVisited[nx][ny + 1] = true;
					} else if (numberOfTile[nx][ny] == numberOfTile[nx][ny - 1]) {
						q.add(new Pos(nx, ny - 1, tmp));
						isVisited[nx][ny - 1] = true;
					}
				}
			}
		}
		return answer;
	}
}
