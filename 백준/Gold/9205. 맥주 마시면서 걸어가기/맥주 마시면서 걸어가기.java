import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int testCase = Integer.parseInt(br.readLine());

		List<int[]> points;
		List<List<Integer>> graph;

		while (testCase-- > 0) {
			int N = Integer.parseInt(br.readLine());
			points = new ArrayList<>();

			for (int i = 0; i < N + 2; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());

				points.add(new int[] {x, y});
			}

			graph = new ArrayList<>();
			for (int i = 0; i < N + 2; i++) {
				graph.add(new ArrayList<>());
			}

			for (int i = 0; i < N + 2; i++) {
				for (int j = i + 1; j < N + 2; j++) {
					if (getDist(points.get(i), points.get(j)) <= 1000) {
						graph.get(i).add(j);
						graph.get(j).add(i);
					}
				}
			}

			sb.append((BFS(graph, N) ? "happy" : "sad")).append("\n");
		}
		System.out.println(sb);
	}

	private static boolean BFS(List<List<Integer>> graph, int N) {
		Queue<Integer> q = new LinkedList<>();
		q.add(0);

		boolean[] isVisited = new boolean[N + 2];
		isVisited[0] = true;

		while (!q.isEmpty()) {
			int cur = q.poll();

			if (cur == N + 1) return true;

			for (int next : graph.get(cur)) {
				if (!isVisited[next]) {
					isVisited[next] = true;
					q.add(next);
				}
			}
		}
		return false;
	}

	private static int getDist(int[] p1, int[] p2) {
		return Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1]);
	}
}
