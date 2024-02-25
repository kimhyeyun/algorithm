import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main{
	static int N, M;
	static char[][] map;
	static Queue<int[]> fire, sanggeun;
	static final int[] dx = {-1, 0, 0, 1};
	static final int[] dy = {0, -1, 1, 0};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int testCase = Integer.parseInt(br.readLine());
		while (testCase-- > 0) {
			st = new StringTokenizer(br.readLine());

			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());

			map = new char[N][M];
			fire = new LinkedList<>();
			sanggeun = new LinkedList<>();

			for (int i = 0; i < N; i++) {
				String s = br.readLine();

				for (int j = 0; j < M; j++) {
					map[i][j] = s.charAt(j);

					if (map[i][j] == '@') {
						sanggeun.add(new int[] {i, j, 0});
					} else if (map[i][j] == '*') {
						fire.add(new int[] {i, j});
					}
				}
			}

			int answer = -1;
			out: while (true) {
				int fSize = fire.size();
				for (int i = 0; i < fSize; i++) {
					int[] pos = fire.poll();
					fireSpread(pos);
				}

				int sSize = sanggeun.size();
				for (int i = 0; i < sSize; i++) {
					int[] pos = sanggeun.poll();
					answer = moveSanggeun(pos);

					if (answer != -1) break out;
				}

				if (sanggeun.isEmpty()) break;
			}

			if (answer != -1) sb.append(answer).append("\n");
			else sb.append("IMPOSSIBLE").append("\n");
		}

		System.out.println(sb);
	}

	private static int moveSanggeun(int[] pos) {
		for (int d = 0; d < 4; d++) {
			int nx = pos[0] + dx[d];
			int ny = pos[1] + dy[d];

			if (nx < 0 || ny < 0 || N <= nx || M <= ny) return pos[2] + 1;

			if (map[nx][ny] == '.') {
				map[nx][ny] = '@';
				sanggeun.add(new int[] {nx, ny, pos[2] + 1});
			}
		}
		return -1;
	}

	private static void fireSpread(int[] pos) {
		for (int d = 0; d < 4; d++) {
			int nx = pos[0] + dx[d];
			int ny = pos[1] + dy[d];

			if (nx < 0 || ny < 0 || N <= nx || M <= ny) continue;

			if (map[nx][ny] == '.' || map[nx][ny] == '@') {
				map[nx][ny] = '*';
				fire.add(new int[] {nx, ny});
			}
		}
	}
}
