import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		char[] gusaga = br.readLine().toCharArray();
		char[] cubelover = br.readLine().toCharArray();

		int len = gusaga.length;

		Arrays.sort(gusaga);
		Arrays.sort(cubelover);

		char[] answer = new char[len];

		int startIndex = 0, endIndex = len - 1;
		int gStartIndex = 0, gEndIndex = len / 2 + len % 2 - 1;
		int cStartIndex = len - 1, cEndIndex = gEndIndex + 1;

		int index = 0;

		while (index < len) {
			if (index % 2 == 0) {
				if (gusaga[gStartIndex] < cubelover[cStartIndex]) {
					answer[startIndex++] = gusaga[gStartIndex++];
				} else {
					answer[endIndex--] = gusaga[gEndIndex--];
				}
			} else {
				if (cubelover[cStartIndex] > gusaga[gStartIndex]) {
					answer[startIndex++] = cubelover[cStartIndex--];
				} else {
					answer[endIndex--] = cubelover[cEndIndex++];
				}
			}

			index += 1;
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < len; i++) {
			sb.append(answer[i]);
		}

		System.out.println(sb);

	}
}
