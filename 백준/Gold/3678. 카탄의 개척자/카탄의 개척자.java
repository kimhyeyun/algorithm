import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
	static int[][] map = new int[2001][2001];
	static int[] answer = new int[10001];
	static Map<Integer, Integer> frequency = new HashMap<>();
	static boolean[] isUsed = new boolean[6];
	static int x = 1000, y = 1000;
	static final int[] dx = {-1, 1, 2, 1, -1, -2};
	static final int[] dy = {-1, -1, 0, 1, 1, 0};


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int i = 1; i <= 5; i++) {
			frequency.put(i, 0);
		}

		map[x][y] = 1;
		frequency.put(1, frequency.get(1) + 1);
		answer[1] = 1;

		map[x - 1][y + 1] = 2;
		frequency.put(2, frequency.get(2) + 1);
		answer[2] = 2;

		x = x - 1;
		y = y + 1;

		int d = -1;
		for (int i = 3; i < 10001; i++) {
			int nd = (d + 1) % 6;
			int nx = x + dx[nd];
			int ny = y + dy[nd];

			if (map[nx][ny] != 0) {
				nx = x + dx[d];
				ny = y + dy[d];
				nd = d;
			}

			if (nx < 0 || ny < 0 || 2001 <= nx || 2001 <= ny) {
				System.out.println(i);
				System.out.println(nx + " " + ny);
				break;
			}

			isUsed = new boolean[6];
			for (int k = 0; k < 6; k++) {
				int nnx = nx + dx[k];
				int nny = ny + dy[k];

				if (nnx < 0 || nny < 0 || 2001 <= nnx || 2001 <= nny)
					continue;
				if (map[nnx][nny] == 0)
					continue;

				isUsed[map[nnx][nny]] = true;
			}

			List<Integer> list = new ArrayList<>(frequency.keySet());
			list.sort((o1, o2) -> {
				if (frequency.get(o1).equals(frequency.get(o2))) {
					return o1 - o2;
				}

				return frequency.get(o1) - frequency.get(o2);
			});

			for (int k : list) {
				if (!isUsed[k]) {
					map[nx][ny] = k;
					frequency.put(k, frequency.get(k) + 1);
					answer[i] = k;
					break;
				}
			}

			x = nx;
			y = ny;
			d = nd;
		}

		int c = Integer.parseInt(br.readLine());
		while (c-- > 0) {
			int n = Integer.parseInt(br.readLine());

			System.out.println(answer[n]);
		}

	}
}
