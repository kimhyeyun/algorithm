
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int size, answer;
	static char[][] map;
	static List<int[]> starts;
	static final int[] dx = {-1, 0, 0, 1};
	static final int[] dy = {0, -1, 1, 0};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int test_case = 1;
		String s = "";
		while ((s = br.readLine()) != null && !s.isEmpty()) {
			st = new StringTokenizer(s);

			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			map = new char[N][M];
			boolean[][] isVisited = new boolean[N][M];
			starts = new ArrayList<>();

			answer = Integer.MAX_VALUE;

			for (int i = 0; i < N; i++) {
				s = br.readLine();
				for (int j = 0; j < M; j++) {
					map[i][j] = s.charAt(j);

					if (map[i][j] == '.') starts.add(new int[] {i, j, 0});
					else isVisited[i][j] = true;
				}
			}

			size = starts.size() - 1;
			for (int[] start : starts) {
				boolean[][] tmp = copyArray(isVisited);
				tmp[start[0]][start[1]] = true;
				solve(start, tmp, 0);
			}

			answer = answer == Integer.MAX_VALUE ? -1 : answer;
			sb.append("Case ").append(test_case++).append(": ").append(answer).append("\n");
		}
		System.out.println(sb);
	}

	private static void solve(int[] cur, boolean[][] isVisited, int count) {
		if (cur[2] == size) {
			answer = Math.min(answer, count);
			return;
		}

		for (int d = 0; d < 4; d++) {
			int nx = cur[0] + dx[d];
			int ny = cur[1] + dy[d];

			if (nx < 0 || ny < 0 || N <= nx || M <= ny || isVisited[nx][ny])
				continue;

			boolean[][] tmp = copyArray(isVisited);
			int[] next;

			switch (d) {
				case 0 -> {
					next = goUp(cur, tmp);
					solve(next, tmp, count + 1);
				}
				case 1 -> {
					next = goLeft(cur, tmp);
					solve(next, tmp, count + 1);
				}
				case 2 -> {
					next = goRight(cur, tmp);
					solve(next, tmp, count + 1);
				}
				case 3 -> {
					next = goDown(cur, tmp);
					solve(next, tmp, count + 1);
				}
			}
		}
	}

	private static int[] goDown(int[] cur, boolean[][] isVisited) {
		for (int i = cur[0] + 1; i < N; i++) {
			if (isVisited[i][cur[1]]) {
				return new int[] {i - 1, cur[1], cur[2] + (i - cur[0] - 1)};
			}
			isVisited[i][cur[1]] = true;
		}
		return new int[] {N - 1, cur[1], cur[2] + (N - cur[0] - 1)};
	}

	private static int[] goRight(int[] cur, boolean[][] isVisited) {
		for (int j = cur[1] + 1; j < M; j++) {
			if (isVisited[cur[0]][j]) {
				return new int[] {cur[0], j - 1, cur[2] + (j - cur[1] - 1)};
			}
			isVisited[cur[0]][j] = true;
		}
		return new int[] {cur[0], M - 1, cur[2] + (M - cur[1] - 1)};
	}

	private static int[] goLeft(int[] cur, boolean[][] isVisited) {
		for (int j = cur[1] - 1; j >= 0; j--) {
			if (isVisited[cur[0]][j]) {
				return new int[] {cur[0], j + 1, cur[2] + cur[1] - j - 1};
			}
			isVisited[cur[0]][j] = true;
		}
		return new int[] {cur[0], 0, cur[2] + cur[1]};
	}

	private static int[] goUp(int[] cur, boolean[][] isVisited) {
		for (int i = cur[0] - 1; i >= 0; i--) {
			if (isVisited[i][cur[1]])
				return new int[] {i + 1, cur[1], cur[2] + cur[0] - i - 1};

			isVisited[i][cur[1]] = true;
		}
		return new int[] {0, cur[1], cur[2] + cur[0]};
	}

	private static boolean[][] copyArray(boolean[][] array) {
		boolean[][] copy = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				copy[i][j] = array[i][j];
			}
		}
		return copy;
	}
}
