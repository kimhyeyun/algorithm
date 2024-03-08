import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, countOfDirtyArea, answer;
	static char[][] map;
	static int[][] distances;
	static Point[] coordinates;
	static boolean[] isSelected;
	static final int[] dx = {-1, 0, 0, 1};
	static final int[] dy = {0, -1, 1, 0};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		while (true) {
			st = new StringTokenizer(br.readLine());

			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());

			if (N == 0 && M == 0) break;

			coordinates = new Point[11];
			distances = new int[11][11];

			countOfDirtyArea = 1;
			answer = Integer.MAX_VALUE;

			map = new char[N][M];
			for (int i = 0; i < N; i++) {
				map[i] = br.readLine().toCharArray();
				for (int j = 0; j < M; j++) {
					if (map[i][j] == 'o') {
						coordinates[0] = new Point(i, j);
					} else if (map[i][j] == '*') {
						coordinates[countOfDirtyArea++] = new Point(i, j);
					}
				}
			}

			isSelected = new boolean[countOfDirtyArea];
			if (calDist()) {
				cleaning(0, 0, 0);
				System.out.println(answer);
			} else
				System.out.println(-1);

		}
	}

	private static void cleaning(int cur, int count, int sum) {
		if (count == countOfDirtyArea - 1) {
			answer = Math.min(answer, sum);
			return;
		}

		for (int i = 1; i < countOfDirtyArea; i++) {
			if (isSelected[i]) continue;

			isSelected[i] = true;
			cleaning(i, count + 1, sum + distances[cur][i]);
			isSelected[i] = false;
		}
	}

	private static boolean calDist() {
		for (int i = 0; i < countOfDirtyArea; i++) {
			for (int j = i + 1; j < countOfDirtyArea; j++) {
				int dist = getDist(coordinates[i], coordinates[j]);
				
				if (dist == -1) return false;
				distances[i][j] = distances[j][i] = dist;
			}
		}
		return true;
	}

	private static int getDist(Point start, Point end) {
		Queue<int[]> q = new LinkedList<>();
		boolean[][] isVisited = new boolean[N][M];

		isVisited[start.x][start.y] = true;
		q.add(new int[]{start.x, start.y, 0});

		int dist = 0;
		while (!q.isEmpty()) {
			// int size = q.size();
			// ++dist;
			//
			// while (size-- > 0) {
			// 	Point cur = q.poll();
			//
			// 	for (int d = 0; d < 4; d++) {
			// 		int nx = cur.x + dx[d];
			// 		int ny = cur.y + dy[d];
			//
			// 		if (nx < 0 || ny < 0 || N <= nx || M <= ny) continue;
			// 		if (isVisited[nx][ny] || map[nx][ny] == 'x') continue;
			//
			// 		if (nx == end.x && ny == end.y)
			// 			return dist;
			//
			// 		isVisited[nx][ny] = true;
			// 		q.add(new Point(nx, ny));
			// 	}
			// }

			int[] cur = q.poll();

			if (cur[0] == end.x && cur[1] == end.y)
				return cur[2];

			for (int d = 0; d < 4; d++) {
				int nx = cur[0] + dx[d];
				int ny = cur[1] + dy[d];

				if (nx < 0 || ny < 0 || N <= nx || M <= ny) continue;
				if (isVisited[nx][ny] || map[nx][ny] == 'x') continue;

				isVisited[nx][ny] = true;
				q.add(new int[] {nx, ny, cur[2] + 1});
			}
		}
		return -1;
	}
}
