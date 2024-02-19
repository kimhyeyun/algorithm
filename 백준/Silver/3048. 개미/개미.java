import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N1 = Integer.parseInt(st.nextToken());
		int N2 = Integer.parseInt(st.nextToken());

		Map<Character, Integer> index = new HashMap<>();

		char[] ants = new char[N1 + N2];
		String t = br.readLine();
		t = new StringBuilder(t).reverse().toString();

		for (int i = 0; i < N1; i++) {
			ants[i] = t.charAt(i);
			index.put(ants[i], 0);
		}

		t = br.readLine();
		for (int i = 0; i < N2; i++) {
			ants[N1 + i] = t.charAt(i);
			index.put(ants[N1 + i], 1);
		}

		int T = Integer.parseInt(br.readLine());

		if (T == 0) {
			for (char ant : ants) {
				System.out.print(ant);
			}
			return;
		}

		while (T-- > 0) {
			for (int i = 1; i < ants.length; i++) {
				if (index.get(ants[i - 1]).equals(0) && index.get(ants[i]).equals(1)) {
					char tmp = ants[i - 1];
					ants[i - 1] = ants[i];
					ants[i] = tmp;
					i += 1;
				}
			}
		}

		for (char ant : ants) {
			System.out.print(ant);
		}
	}
}
