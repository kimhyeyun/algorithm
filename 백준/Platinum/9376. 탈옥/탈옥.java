import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main{
	static int N, M;
	static int[][] map;
	static final int BLACK = 0;
	static final int WALL = -1;
	static final int PRISONER = 2;
	static final int DOOR = 1;
	static final int[] dx = {-1, 0, 0, 1};
	static final int[] dy = {0, -1, 1, 0};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int test_case = Integer.parseInt(br.readLine());
		while (test_case-- > 0) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			Point sanggeun = new Point(0, 0);
			Point prisoner1 = new Point(-1, -1);
			Point prisoner2 = new Point(-1, -1);

			map = new int[N + 2][M + 2];
			for (int i = 1; i < N + 1; i++) {
				String s = br.readLine();

				for (int j = 1; j < M + 1; j++) {
					if (s.charAt(j - 1) == '*')
						map[i][j] = WALL;
					else if (s.charAt(j - 1) == '.')
						map[i][j] = BLACK;
					else if (s.charAt(j - 1) == '$') {
						map[i][j] = BLACK;
						if (prisoner1.x == -1)
							prisoner1 = new Point(i, j);
						else
							prisoner2 = new Point(i, j);
					} else
						map[i][j] = DOOR;
				}
			}

			int[][] dist1 = BFS(sanggeun);
			int[][] dist2 = BFS(prisoner1);
			int[][] dist3 = BFS(prisoner2);

			int answer = (N + 2) * (M + 2);
			for (int i = 0; i < N + 2; i++) {
				for (int j = 0; j < M + 2; j++) {
					if (map[i][j] == WALL) continue;

					int temp = dist1[i][j] + dist2[i][j] + dist3[i][j];
					if (map[i][j] == DOOR) temp -= 2;

					answer = answer < temp ? answer : temp;
				}
			}
			System.out.println(answer);
		}
	}

	private static int[][] BFS(Point start) {
		int[][] dist = new int[N + 2][M + 2];
		for (int i = 0; i < N + 2; i++) {
			Arrays.fill(dist[i], Integer.MAX_VALUE);
		}

		Queue<Point> q = new LinkedList<>();
		q.add(start);
		dist[start.x][start.y] = 0;

		while (!q.isEmpty()) {
			Point cur = q.poll();

			for (int d = 0; d < 4; d++) {
				int nx = cur.x + dx[d];
				int ny = cur.y + dy[d];

				if (nx < 0 || ny < 0 || N + 2 <= nx || M + 2 <= ny) continue;
				if (map[nx][ny] == WALL) continue;

				if (map[nx][ny] == BLACK) {
					if (dist[nx][ny] > dist[cur.x][cur.y]) {
						dist[nx][ny] = dist[cur.x][cur.y];
						q.add(new Point(nx, ny));
					}
				} else if (map[nx][ny] == DOOR) {
					if (dist[nx][ny] > dist[cur.x][cur.y] + 1) {
						dist[nx][ny] = dist[cur.x][cur.y] + 1;
						q.add(new Point(nx, ny));
					}
				}
			}
		}
		return dist;
	}
}
