import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		int home[][] = new int[n + 1][n + 1]; // 집의 상태 저장.

		// dp[dir][r][c] = cnt. dir: 방향(0-가로, 1-세로, 2-대각선.), cnt:(r, c)에 도착하는 경우의 수.
		int dp[][][] = new int[3][n + 1][n + 1];

		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= n; j++)
				home[i][j] = Integer.parseInt(st.nextToken());
		}

		dp[0][1][2] = 1;
		for (int r = 1; r <= n; r++) {
			for (int c = 1; c <= n; c++) {
				// 벽이면 패스.
				if (home[r][c] == 1)
					continue;

				// 가로로 도착하는 경우.
				dp[0][r][c] += dp[0][r][c - 1] + dp[2][r][c - 1];
				// 세로로 도착하는 경우.
				dp[1][r][c] += dp[1][r - 1][c] + dp[2][r - 1][c];
				// 대각선으로 도착하는 경우.
				if (home[r - 1][c] != 1 && home[r][c - 1] != 1)
					dp[2][r][c] += dp[0][r - 1][c - 1] + dp[1][r - 1][c - 1] + dp[2][r - 1][c - 1];
			}
		}

		// 아래 방식으로도 가능.
		// for (int r = 1; r <= n; r++) {
		// 	for (int c = 3; c <= n; c++) {
		// 		// 벽이면 패스.
		// 		if (home[r][c] == 1)
		// 			continue;

		// 		// 가로로 도착하는 경우.
		// 		dp[0][r][c] = dp[0][r][c - 1] + dp[2][r][c - 1];
		// 		// 세로로 도착하는 경우.
		// 		dp[1][r][c] = dp[1][r - 1][c] + dp[2][r - 1][c];
		// 		// 대각선으로 도착하는 경우.
		// 		if (home[r - 1][c] != 1 && home[r][c - 1] != 1)
		// 			dp[2][r][c] = dp[0][r - 1][c - 1] + dp[1][r - 1][c - 1] + dp[2][r - 1][c - 1];
		// 	}
		// }

		// 확인용 출력.
		// for (int i = 0; i < 3; i++) {
		// 	for (int j = 0; j <= n; j++)
		// 		System.out.println(Arrays.toString(dp[i][j]));
		// 	System.out.println();
		// }
		
		int rst = 0;
		for (int i = 0; i < 3; i++) // 가로, 세로, 대각선으로 도착한 경우를 합치면 됨.
			rst += dp[i][n][n];
		System.out.println(rst);
	}
}
