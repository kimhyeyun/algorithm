import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int R, C, K;
	static int[][] map;
	static int[] pos;
	static List<Integer> dirs;
	static int[] dx = {0, -1, 1, 0, 0};
	static int[] dy = {0, 0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		K = Integer.parseInt(br.readLine());

		map = new int[R][C];

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			map[x][y] = -1;
		}

		st = new StringTokenizer(br.readLine());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());

		pos = new int[] {x, y};

		dirs = new LinkedList<>();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			dirs.add(Integer.parseInt(st.nextToken()));
		}

		map[pos[0]][pos[1]] = 1;
		move(0);

		System.out.println(pos[0] + " " + pos[1]);
	}

	private static void move(int d) {
		int count = 0;
		while (true) {
			int nx = pos[0] + dx[dirs.get(d)];
			int ny = pos[1] + dy[dirs.get(d)];

			if (nx < 0 || ny < 0 || R <= nx || C <= ny || map[nx][ny] != 0) {
				d = (d + 1) % 4;
				count += 1;
			} else {
				map[nx][ny] = 1;
				pos = new int[] {nx, ny};
				count = 0;
			}

			if (count == 4) break;
		}
	}
}
