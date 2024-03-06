import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String s = br.readLine();
		String p = br.readLine();

		int len = p.length();

		int startIndex = 0;
		int count = 0;
		int answer = 0;

		while (startIndex < len) {
			for (int i = 1; i < len + 1; i++) {
				if (startIndex + i > len)
					break;

				String tmp = p.substring(startIndex, startIndex + i);

				if (s.contains(tmp)) {
					count = i;
				} else
					break;
			}

			startIndex = startIndex + count;
			answer += 1;
		}
		System.out.println(answer);
	}
}
