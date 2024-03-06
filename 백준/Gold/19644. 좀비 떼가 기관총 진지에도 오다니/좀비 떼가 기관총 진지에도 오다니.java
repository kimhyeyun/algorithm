import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int L, maxLen, damage, countOfMine;
	static int[] zombies;
	static Queue<Integer> q;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		L = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());
		maxLen = Integer.parseInt(st.nextToken());
		damage = Integer.parseInt(st.nextToken());

		countOfMine = Integer.parseInt(br.readLine());

		zombies = new int[L + 5];
		for (int i = 1; i <= L; i++) {
			zombies[i] = Integer.parseInt(br.readLine());
		}

		q = new LinkedList<>();

		System.out.println(defense(1, 1));
	}

	private static String defense(int left, int right) {
		if (right > L)
			return "YES";

		while (!q.isEmpty() && q.peek() < left) q.poll();

		int d = Math.max((right - left + 1) * damage - q.size() * damage, damage);
		if (zombies[right] > d) {
			q.add(right);
			if (countOfMine > 0)
				countOfMine -= 1;
			else
				return "NO";
		}

		if (right - left + 1 < maxLen)
			return defense(left, right + 1);
		else
			return defense(left + 1, right + 1);
	}
}
