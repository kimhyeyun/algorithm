	import java.io.BufferedReader;
	import java.io.IOException;
	import java.io.InputStreamReader;
	import java.util.ArrayList;
	import java.util.Arrays;
	import java.util.List;
	import java.util.StringTokenizer;

	public class Main {
		static int N, M;
		static final long INF = Long.MAX_VALUE;
		static List<int[]> adj;
		public static void main(String[] args) throws IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			StringTokenizer st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			adj = new ArrayList<>();

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());

				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());

				adj.add(new int[] {a, b, c});
			}

			bellmanFord();
		}

		private static void bellmanFord() {
			long[] dist = new long[N + 1];
			Arrays.fill(dist, INF);

			dist[1] = 0;

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					int[] edge = adj.get(j);

					if (dist[edge[0]] != INF && dist[edge[1]] > dist[edge[0]] + edge[2]) {
						dist[edge[1]] = dist[edge[0]] + edge[2];
					}
				}
			}

			for (int i = 0; i < M; i++) {
				int[] cur = adj.get(i);

				if (dist[cur[0]] != INF && dist[cur[1]] > dist[cur[0]] + cur[2]) {
					System.out.println(-1);
					return;
				}
			}

			for (int i = 2; i <= N; i++) {
				if (dist[i] == INF)
					System.out.println(-1);
				else
					System.out.println(dist[i]);
			}
		}
	}
