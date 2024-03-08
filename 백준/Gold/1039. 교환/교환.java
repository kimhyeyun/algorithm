import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, K, answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		answer = -1;

		BFS();

		System.out.println(answer);
	}

	private static void BFS() {
		Queue<int[]> q = new LinkedList<>();
		boolean[][] isVisited = new boolean[1000001][K + 1];

		q.add(new int[] {N, 0});
		isVisited[N][0] = true;

		while (!q.isEmpty()) {
			int[] cur = q.poll();

			if (cur[1] == K) {
				answer = Math.max(answer, cur[0]);
				continue;
			}

			int len = String.valueOf(cur[0]).length();
			for (int i = 0; i < len - 1; i++) {
				for (int j = i + 1; j < len; j++) {
					int next = swap(cur[0], i, j);

					if (next != -1 && !isVisited[next][cur[1] + 1]) {
						q.add(new int[] {next, cur[1] + 1});
						isVisited[next][cur[1] + 1] = true;
					}
				}
			}
		}
	}

	private static int swap(int cur, int i, int j) {
		char[] arr = String.valueOf(cur).toCharArray();

		if (i == 0 && arr[j] == '0') return -1;

		char tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;

		return Integer.parseInt(new String(arr));
	}
}
