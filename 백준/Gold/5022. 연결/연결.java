import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Point {
		int x, y;
		int dist;

		public Point(int x, int y, int dist) {
			this.x = x;
			this.y = y;
			this.dist = dist;
		}
	}

	static int N, M;
	static int fDist, sDist;
	static Point[][] map;
	static int min = Integer.MAX_VALUE;
	static final int[] dx = {-1, 0, 0, 1};
	static final int[] dy = {0, -1, 1, 0};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		Point[] p = new Point[4];
		for (int i = 0; i < 4; i++) {
			st = new StringTokenizer(br.readLine());

			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			p[i] = new Point(x, y, 0);
		}

		int answer1 = BFS(p[0], p[1], p[2], p[3]);
		int answer2 = BFS(p[2], p[3], p[0], p[1]);

		if (answer1 == Integer.MAX_VALUE && answer2 == Integer.MAX_VALUE) {
			System.out.println("IMPOSSIBLE");
			return;
		}
		System.out.println(Math.min(answer1, answer2));
	}

	private static int BFS(Point start1, Point end1, Point start2, Point end2) {
		fDist = 0;
		sDist = 0;

		map = new Point[N + 1][M + 1];
		boolean[][] isVisited = new boolean[N + 1][M + 1];
		Queue<Point> q = new LinkedList<>();

		q.add(start1);
		isVisited[start1.x][start1.y] = true;
		isVisited[start2.x][start2.y] = true;
		isVisited[end2.x][end2.y] = true;

		while (!q.isEmpty()) {
			Point cur = q.poll();

			if (cur.x == end1.x && cur.y == end1.y) {
				fDist = cur.dist;
				break;
			}

			for (int d = 0; d < 4; d++) {
				int nx = cur.x + dx[d];
				int ny = cur.y + dy[d];
				int dist = cur.dist + 1;

				if (nx < 0 || ny < 0 || N < nx || M < ny) continue;
				if (isVisited[nx][ny]) continue;

				q.add(new Point(nx, ny, dist));
				isVisited[nx][ny] = true;
				map[nx][ny] = cur;
			}
		}

		isVisited = new boolean[N + 1][M + 1];

		Point cur = end1;
		isVisited[cur.x][cur.y] = true;

		while (true) {
			isVisited[cur.x][cur.y] = true;
			if (cur.x == start1.x && cur.y == start1.y) break;

			cur = map[cur.x][cur.y];
		}

		q = new LinkedList<>();
		q.add(start2);
		isVisited[start2.x][start2.y] = true;

		while (!q.isEmpty()) {
			cur = q.poll();

			if (cur.x == end2.x && cur.y == end2.y) {
				sDist = cur.dist;
				break;
			}

			for (int d = 0; d < 4; d++) {
				int nx = cur.x + dx[d];
				int ny = cur.y + dy[d];
				int dist = cur.dist + 1;

				if (nx < 0 || ny < 0 || N < nx || M < ny) continue;
				if (isVisited[nx][ny]) continue;

				q.add(new Point(nx, ny, dist));
				isVisited[nx][ny] = true;
				map[nx][ny] = cur;
			}
		}

		if (sDist == 0) return Integer.MAX_VALUE;
		else return sDist + fDist;
	}
}
