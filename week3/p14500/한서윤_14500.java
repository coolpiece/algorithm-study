import java.io.*;
import java.util.*;

public class Main {
	private static int n, m, maxVal = 0, rst = 0, v[][];
	private static int[] dr = { -1, 1, 0, 0 }, dc = { 0, 0, -1, 1 };
	private static boolean[][] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		v = new int[n][m];
		visited = new boolean[n][m];

		for (int i = 0; i < n; i++) { // 입력.
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				v[i][j] = Integer.parseInt(st.nextToken());
				maxVal = Math.max(maxVal, v[i][j]);
			}
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				visited[i][j] = true;
				dfs(i, j, 1, v[i][j]);
				visited[i][j] = false;
			}
		}
		System.out.println(rst);
	}

	static void dfs(int r, int c, int size, int sum) {
		if (sum + (4 - size) * maxVal <= rst) // 이미 구한 값보다 더 커질 수 없는 경우.
			return;
		if (size == 4) { // 4개를 모두 방문한 경우.
			rst = Math.max(rst, sum);
			return;
		}
		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i]; // 다음 좌표 탐색. 상하좌우 순.
			int nc = c + dc[i];
			if (nr < 0 || nr >= n || nc < 0 || nc >= m) // 범위를 넘어가는 경우.
				continue;

			if (!visited[nr][nc]) {
				visited[nr][nc] = true;
				if (size == 2) // ㅏ 모양 탐색.
					dfs(r, c, size + 1, sum + v[nr][nc]); // 다음을 방문 처리하고 제자리에서 다시 탐색.

				dfs(nr, nc, size + 1, sum + v[nr][nc]);
				visited[nr][nc] = false;
			}
		}
	}
}

//public class Main {
//	public static void main(String[] args) throws Exception {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st;
//		st = new StringTokenizer(br.readLine());
//		int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken()), maxVal = 0;
//		int[][] v = new int[n][m];
//
//		for (int i = 0; i < n; i++) { // 입력.
//			st = new StringTokenizer(br.readLine());
//			for (int j = 0; j < m; j++) {
//				v[i][j] = Integer.parseInt(st.nextToken());
//			}
//		}
//		// 직사각형 형태로 범위 정하고 범위 내에서 모양 만들기.
//		for (int i = 0; i < n; i++) // 가로4 * 세로1.
//			for (int j = 0; j < m - 3; j++)
//				maxVal = Math.max(maxVal, v[i][j] + v[i][j + 1] + v[i][j + 2] + v[i][j + 3]);
//
//		for (int i = 0; i < n - 3; i++) // 가로1 * 세로4.
//			for (int j = 0; j < m; j++)
//				maxVal = Math.max(maxVal, v[i][j] + v[i + 1][j] + v[i + 2][j] + v[i + 3][j]);
//
//		for (int i = 0; i < n - 1; i++) // 정사각형. 가로2 * 세로2.
//			for (int j = 0; j < m - 1; j++)
//				maxVal = Math.max(maxVal, v[i][j] + v[i][j + 1] + v[i + 1][j] + v[i + 1][j + 1]);
//
//		for (int i = 0; i < n - 2; i++) { // 가로2 * 세로3.
//			for (int j = 0; j < m - 1; j++) {
//				// 세로 3칸 고정하고 그 왼쪽/오른쪽 칸에서 위, 중간, 아래 순으로 선택.
//				int left = v[i][j] + v[i + 1][j] + v[i + 2][j], right = v[i][j + 1] + v[i + 1][j + 1] + v[i + 2][j + 1];
//				maxVal = Math.max(maxVal, left + v[i][j + 1]);
//				maxVal = Math.max(maxVal, left + v[i + 1][j + 1]);
//				maxVal = Math.max(maxVal, left + v[i + 2][j + 1]);
//				maxVal = Math.max(maxVal, right + v[i][j]);
//				maxVal = Math.max(maxVal, right + v[i + 1][j]);
//				maxVal = Math.max(maxVal, right + v[i + 2][j]);
//				// 번개 모양.
//				maxVal = Math.max(maxVal, v[i][j] + v[i + 1][j] + v[i + 1][j + 1] + v[i + 2][j + 1]);
//				maxVal = Math.max(maxVal, v[i][j + 1] + v[i + 1][j] + v[i + 1][j + 1] + v[i + 2][j]);
//			}
//		}
//
//		for (int i = 0; i < n - 1; i++) { // 가로3 * 세로2.
//			for (int j = 0; j < m - 2; j++) {
//				// 가로 3칸 고정하고 그 위/아래 칸에서 왼쪽, 가운데, 오른쪽 순으로 선택.
//				int up = v[i][j] + v[i][j + 1] + v[i][j + 2], down = v[i + 1][j] + v[i + 1][j + 1] + v[i + 1][j + 2];
//				maxVal = Math.max(maxVal, up + v[i + 1][j]);
//				maxVal = Math.max(maxVal, up + v[i + 1][j + 1]);
//				maxVal = Math.max(maxVal, up + v[i + 1][j + 2]);
//				maxVal = Math.max(maxVal, down + v[i][j]);
//				maxVal = Math.max(maxVal, down + v[i][j + 1]);
//				maxVal = Math.max(maxVal, down + v[i][j + 2]);
//				// 번개 모양.
//				maxVal = Math.max(maxVal, v[i][j] + v[i][j + 1] + v[i + 1][j + 1] + v[i + 1][j + 2]);
//				maxVal = Math.max(maxVal, v[i][j + 1] + v[i][j + 2] + v[i + 1][j + 1] + v[i + 1][j]);
//			}
//		}
//		System.out.println(maxVal);
//	}
//}