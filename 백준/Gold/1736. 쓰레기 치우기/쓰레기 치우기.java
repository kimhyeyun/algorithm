import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		PriorityQueue<Integer>[] pq = new PriorityQueue[M];
		for (int i = 0; i < M; i++) {
			pq[i] = new PriorityQueue<>();
		}

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				int a = Integer.parseInt(st.nextToken());

				if (a == 1) {
					pq[j].add(i);

				}
			}
		}

		int answer = 0;
		for (int i = 0; i < M; i++) {
			int count = 0;
			int curHeight = N - 1;

			for (int j = M - 1; j >= 0; j--) {
				if (pq[j].isEmpty()) continue;
				int prevHeight = pq[j].peek();

				if (prevHeight <= curHeight) {
					while (!pq[j].isEmpty() && pq[j].peek() <= curHeight) {
						pq[j].poll();
						count += 1;
					}
					curHeight = prevHeight;
				}
			}

			if (count > 0) answer += 1;
			else break;

		}

		System.out.println(answer);
	}
}
