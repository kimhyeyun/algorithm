import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int test_case = Integer.parseInt(br.readLine());
		while (test_case-- > 0) {
			int N = Integer.parseInt(br.readLine());
			int[][] map = new int[2][N];

			int answer = 0;

			for (int i = 0; i < 2; i++) {
				String s = br.readLine();

				for (int j = 0; j < N; j++) {
					if (s.charAt(j) == '*') {
						map[i][j] = -2;
					}
					else if (s.charAt(j) == '#')
						map[i][j] = -1;
					else
						map[i][j] = s.charAt(j) - '0';
				}

				for (int j = 0; j < N; j++) {
					if (j == 0) {
						if (map[0][0] != 0 && map[0][1] != 0) {
							answer += 1;
							map[0][0] -= 1;
							map[0][1] -= 1;
						}
					} else if (j == N - 1) {
						if (map[0][N - 1] != 0 && map[0][N - 2] != 0) {
							answer += 1;
							map[0][N - 1] -= 1;
							map[0][N - 2] -= 1;
						}
					} else {
						if (map[0][j] != 0 && map[0][j - 1] != 0 && map[0][j + 1] != 0) {
							answer += 1;
							map[0][j - 1] -= 1;
							map[0][j] -= 1;
							map[0][j + 1] -= 1;
						}
					}
				}
			}
			sb.append(answer).append("\n");
		}
		System.out.println(sb);
	}

}
