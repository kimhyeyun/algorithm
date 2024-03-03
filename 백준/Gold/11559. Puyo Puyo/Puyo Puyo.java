import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static char[][] map;

	static int answer;
	static boolean isConnected;

	static final int[] dx = {-1, 0, 0, 1};
	static final int[] dy = {0, -1, 1, 0};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		map = new char[12][6];
		for (int i = 0; i < 12; i++) {
			String s = br.readLine();
			for (int j = 0; j < 6; j++) {
				map[i][j] = s.charAt(j);
			}
		}

		answer = 0;
		while (true) {
			isConnected = false;

			pop();
			check();

			if (!isConnected) break;
			answer += 1;
		}

		System.out.println(answer);
	}

	private static void check() {
		for (int i = 10; i >= 0; i--) {
			down(i);
		}
	}

	private static void down(int row) {
		for (int j = 0; j < 6; j++) {
			int index = -1;
			if (map[row][j] != '.') {
				for (int i = row + 1; i < 12; i++) {
					if (map[i][j] == '.') index = i;
					else break;
				}

				if (index != -1) {
					map[index][j] = map[row][j];
					map[row][j] = '.';
				}
			}
		}
	}

	private static void pop() {
		Queue<Puyo> q = new LinkedList<>();
		boolean[][] isVisited = new boolean[12][6];

		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 6; j++) {
				if (isVisited[i][j] || map[i][j] == '.')
					continue;

				List<int[]> sameColor = new LinkedList<>();

				q.add(new Puyo(i, j, map[i][j]));
				sameColor.add(new int[] {i, j});
				isVisited[i][j] = true;

				while (!q.isEmpty()) {
					Puyo cur = q.poll();

					for (int d = 0; d < 4; d++) {
						int nx = cur.x + dx[d];
						int ny = cur.y + dy[d];

						if (nx < 0 || ny < 0 || 12 <= nx || 6 <= ny) continue;
						if (isVisited[nx][ny] || map[nx][ny] != cur.color) continue;

						q.add(new Puyo(nx, ny, map[nx][ny]));
						sameColor.add(new int[] {nx, ny});
						isVisited[nx][ny] = true;
					}
				}

				if (sameColor.size() >= 4) {
					for (int[] list : sameColor) {
						map[list[0]][list[1]] = '.';
						isConnected = true;
					}
				}
			}
		}
	}

	static class Puyo {
		int x, y;
		char color;

		public Puyo(int x, int y, char color) {
			this.x = x;
			this.y = y;
			this.color = color;
		}
	}

}
