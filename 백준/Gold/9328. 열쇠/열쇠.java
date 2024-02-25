import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main{

	static int N, M, answer;
	static char[][] map;
	static boolean[][] isVisited;
	static int keyBit;
	static Map<Integer, Queue<int[]>> doorList;
	static final int[] dx = {-1, 0, 0, 1};
	static final int[] dy = {0, -1, 1, 0};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int testCase = Integer.parseInt(br.readLine());
		while (testCase-- > 0) {
			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			map = new char[N][M];
			isVisited = new boolean[N][M];

			List<int[]> entrances = new ArrayList<>();
			doorList = new HashMap<>();

			keyBit = 0;
			answer = 0;

			for (int i = 0; i < N; i++) {
				String s = br.readLine();
				for (int j = 0; j < M; j++) {
					map[i][j] = s.charAt(j);

					if (map[i][j] != '*') {
						if (i == 0 || i == N - 1 || j == 0 || j == M - 1) {
							if (map[i][j] == '$') {
								answer += 1;
								map[i][j] = '.';
							} else if ('a' <= map[i][j] && map[i][j] <= 'z') {
								keyBit |= 1 << (map[i][j] - 'a');
							}
							entrances.add(new int[] {i, j});
						}
					}
				}
			}

			char[] keyList = br.readLine().toCharArray();
			for (char key : keyList) {
				if (key == '0') break;
				keyBit |= (1 << (key - 'a'));
			}

			for (int[] pos : entrances) {
				int x = pos[0];
				int y = pos[1];

				if ('A' <= map[x][y] && map[x][y] <= 'Z') {
					int door = 1 << (map[x][y] - 65);
					if ((keyBit & door) != door) {
						putDoorQueue(x, y, door);
						continue;
					}
				}

				BFS(x, y);
			}
			System.out.println(answer);
		}
	}

	private static void BFS(int x, int y) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {x, y});

		isVisited[x][y] = true;

		while (!q.isEmpty()) {
			int[] cur = q.poll();

			for (int d = 0; d < 4; d++) {
				int nx = cur[0] + dx[d];
				int ny = cur[1] + dy[d];

				if (nx < 0 || ny < 0 || N <= nx || M <= ny) continue;
				if (isVisited[nx][ny] || map[nx][ny] == '*') continue;

				if (map[nx][ny] == '$') answer += 1;
				else if ('a' <= map[nx][ny] && map[nx][ny] <= 'z') {
					keyBit |= 1 << (map[nx][ny] - 'a');
					for (int door : doorList.keySet()) {
						if ((keyBit & door) == door) {
							waitingQueueExtract(q, door);
						}
					}
				} else if ('A' <= map[nx][ny] && map[nx][ny] <= 'Z') {
					int door = 1 << (map[nx][ny] - 'A');
					if ((keyBit & door) != door) {
						putDoorQueue(nx, ny, door);
						continue;
					}
				}

				isVisited[nx][ny] = true;
				q.add(new int[] {nx, ny});
			}
		}
	}

	private static void waitingQueueExtract(Queue<int[]> q, int door) {
		Queue<int[]> waitingQueue = doorList.get(door);
		while (!waitingQueue.isEmpty()) {
			int[] wq = waitingQueue.poll();
			q.add(wq);
		}
	}

	private static void putDoorQueue(int x, int y, int door) {
		if (doorList.containsKey(door)) {
			doorList.get(door).add(new int[] {x, y});
		} else {
			Queue<int[]> q = new LinkedList<>();
			q.add(new int[] {x, y});
			doorList.put(door, q);
		}
	}
}
