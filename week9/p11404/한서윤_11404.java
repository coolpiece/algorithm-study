import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine()), m = Integer.parseInt(br.readLine());
		long cost[][] = new long[n + 1][n + 1];

		// 플로이드-워셜 수행을 위해 최댓값의 절반값으로 배열 초기화.
		for (int i = 1; i <= n; i++) {
			Arrays.fill(cost[i], Long.MAX_VALUE / 2);
			// 자기 자신에게 가는 비용.
			cost[i][i] = 0;
		}

		// 입력.
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken()),
					c = Integer.parseInt(st.nextToken());
			cost[a][b] = Math.min(cost[a][b], c);
		}

		// 플로이드-워셜 수행.
		for (int k = 1; k <= n; k++) {
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n; j++) {
					if (cost[i][k] != Long.MAX_VALUE / 2 && cost[k][j] != Long.MAX_VALUE / 2)
						cost[i][j] = Math.min(cost[i][j], cost[i][k] + cost[k][j]);
				}
			}
		}

		// 출력.
		StringBuilder rst = new StringBuilder();
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++)
				rst.append((cost[i][j] == Long.MAX_VALUE / 2) ? 0 : cost[i][j]).append(" ");
			rst.append("\n");
		}
		System.out.println(rst);
	}
}
