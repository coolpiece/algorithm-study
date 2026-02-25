import java.io.*;
import java.util.*;

public class Main {
	static int k, v[] = new int[49], pick[] = new int[6];
	static StringBuilder rst = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		while (true) {
			st = new StringTokenizer(br.readLine());
			k = Integer.parseInt(st.nextToken());
			if (k == 0)
				break;
			for (int i = 0; i < k; i++)
				v[i] = Integer.parseInt(st.nextToken());
			combi(0, 0);
			rst.append("\n");
		}
		System.out.println(rst);
	}

	static void combi(int start, int cnt) {
		if (cnt == 6) { // 다 뽑은 경우.
			for (int i = 0; i < 6; i++)
				rst.append(pick[i]).append(" ");
			rst.append("\n");
			return;
		}
		for (int i = start; i < k; i++) {
			pick[cnt] = v[i];
			combi(i + 1, cnt + 1);
		}
	}
}