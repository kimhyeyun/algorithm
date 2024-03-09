import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int n, m, t;
	static int s, g, h;
	static List<Integer> destCandidates;
	static List<int[]>[] adj;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int test_case = Integer.parseInt(br.readLine());
		while (test_case-- > 0) {
			st = new StringTokenizer(br.readLine());

			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			t = Integer.parseInt(st.nextToken());

			adj = new List[n + 1];
			for (int i = 0; i <= n; i++) {
				adj[i] = new LinkedList<>();
			}

			st = new StringTokenizer(br.readLine());

			s = Integer.parseInt(st.nextToken());
			g = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());

			int essentialRoute = -1;

			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());

				adj[a].add(new int[] {b, d});
				adj[b].add(new int[] {a, d});

				if ((a == g && b == h) || (a == h && b == g)) {
					essentialRoute = d;
				}
			}

			destCandidates = new LinkedList<>();

			for (int i = 0; i < t; i++) {
				destCandidates.add(Integer.parseInt(br.readLine()));
			}

			int[] dist1 = BFS(s);
			int[] dist2 = BFS(g);
			int[] dist3 = BFS(h);

			Collections.sort(destCandidates);

			for (int dest : destCandidates) {
				int sd = dist1[dest];
				int sg = dist1[g];
				int sh = dist1[h];
				int gd = dist2[dest];
				int hd = dist3[dest];

				if ((sd == sg + essentialRoute + hd) || (sd == sh + essentialRoute + gd)) {
					sb.append(dest).append(" ");
				}
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	private static int[] BFS(int start) {
		PriorityQueue<int[]> q = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
		int[] dist = new int[n + 1];

		Arrays.fill(dist, Integer.MAX_VALUE);

		q.add(new int[] {start, 0});
		dist[start] = 0;

		while (!q.isEmpty()) {
			int[] cur = q.poll();

			if (cur[1] > dist[cur[0]]) continue;
			for (int[] next : adj[cur[0]]) {
				if (dist[next[0]] > dist[cur[0]] + next[1]) {
					dist[next[0]] = dist[cur[0]] + next[1];
					q.add(new int[] {next[0], dist[next[0]]});
				}
			}
		}
		return dist;
	}

}
