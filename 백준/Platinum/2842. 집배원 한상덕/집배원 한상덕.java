import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main{

    private static int N, target;
    private static int[] start;
    private static char[][] map;
    private static int[][] heights;
    private static final int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    private static final int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        target = 0;

        heights = new int[N][N];
        map = new char[N][N];

        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();

            for (int j = 0; j < N; j++) {
                if (map[i][j] == 'P') {
                    start = new int[]{i, j};
                } else if (map[i][j] == 'K') {
                    target += 1;
                }
            }
        }

        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        List<Integer> height = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                heights[i][j] = Integer.parseInt(st.nextToken());
                height.add(heights[i][j]);

                if (map[i][j] == 'P' || map[i][j] == 'K') {
                    max = Math.max(max, heights[i][j]);
                    min = Math.min(min, heights[i][j]);
                }
            }
        }

        Collections.sort(height);
        int left = 0;
        int right = height.indexOf(max);
        int minIndex = height.indexOf(min);
        int maxIndex = height.size();
        int answer = Integer.MAX_VALUE;

        while (left <= minIndex && left <= right && right < maxIndex) {
            int l = height.get(left);
            int r = height.get(right);

            if (BFS(l, r, target)) {
                answer = Math.min(answer, (r - l));
                left += 1;
            } else right += 1;
        }

        System.out.println(answer);
    }

    private static boolean BFS(int left, int right, int target) {
        Queue<int[]> q = new LinkedList<>();
        boolean[][] isVisited = new boolean[N][N];

        q.add(start);
        isVisited[start[0]][start[1]] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            if(target == 0) return true;

            for (int d = 0; d < 8; d++) {
                int nx = cur[0] + dx[d];
                int ny = cur[1] + dy[d];

                if (nx < 0 || ny < 0 || N <= nx || N <= ny) continue;
                if (isVisited[nx][ny] || heights[nx][ny] < left || heights[nx][ny] > right) continue;

                isVisited[nx][ny] = true;
                if (map[nx][ny] == 'K') target -= 1;
                q.add(new int[]{nx, ny});
            }
        }
        return false;
    }
}
