import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static char[][] map = new char[5][9];
	static boolean[] isVisited = new boolean[13];
	static List<int[]> list = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s;

		for (int i = 0; i < 5; i++) {
			s = br.readLine();

			for (int j = 0; j < 9; j++) {
				map[i][j] = s.charAt(j);

				if (map[i][j] == 'x') {
					list.add(new int[] {i, j});
				} else if (map[i][j] != '.') {
					isVisited[map[i][j] - 'A'] = true;
				}
			}
		}

		DFS(0);
	}

	private static void DFS(int index) {
		if (index == list.size()) {
			if (check()) {
				for (int i = 0; i < 5; i++) {
					for (int j = 0; j < 9; j++) {
						System.out.print(map[i][j]);
					}
					System.out.println();
				}
				System.exit(0);
			}
			return;
		}

		for (int i = 0; i < 12; i++) {
			if (!isVisited[i]) {
				int[] cur = list.get(index);
				char alpha = (char)(65 + i);
				map[cur[0]][cur[1]] = alpha;
				isVisited[i] = true;

				DFS(index + 1);

				isVisited[i] = false;
				map[cur[0]][cur[1]] = '.';
			}
		}
	}

	private static boolean check() {
		int sum1 = (map[0][4] - 'A' + 1) + (map[1][3] - 'A' + 1) + (map[2][2] - 'A' + 1) + (map[3][1] - 'A' + 1);
		int sum2 = (map[0][4] - 'A' + 1) + (map[1][5] - 'A' + 1) + (map[2][6] - 'A' + 1) + (map[3][7] - 'A' + 1);
		int sum3 = (map[1][1] - 'A' + 1) + (map[1][3] - 'A' + 1) + (map[1][5] - 'A' + 1) + (map[1][7] - 'A' + 1);
		int sum4 = (map[3][1] - 'A' + 1) + (map[3][3] - 'A' + 1) + (map[3][5] - 'A' + 1) + (map[3][7] - 'A' + 1);
		int sum5 = (map[4][4] - 'A' + 1) + (map[3][3] - 'A' + 1) + (map[2][2] - 'A' + 1) + (map[1][1] - 'A' + 1);
		int sum6 = (map[4][4] - 'A' + 1) + (map[3][5] - 'A' + 1) + (map[2][6] - 'A' + 1) + (map[1][7] - 'A' + 1);

		return sum1 == 26 && sum2 == 26 && sum3 == 26 && sum4 == 26 && sum5 == 26 && sum6 == 26;
	}
}
