import java.io.*;
import java.util.*;

public class Main {
	private static int map[][], dr[] = { -1, 1, 0, 0 }, dc[] = { 0, 0, -1, 1 }, n;
	private static char[][] input;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		input = new char[n][];
		map = new int[n][n];
		for (int i = 0; i < n; i++) { // 입력.
			String str = br.readLine();
			input[i] = str.toCharArray();
		}

		int num = 1;
		for (int i = 0; i < n; i++) { // 같은 섬 찾아 묶기.
			for (int j = 0; j < n; j++) {
				if (input[i][j * 2] == '1' && map[i][j] == 0) {
					sameIsland(i, j, num);
					num++;
				}
			}
		}

		int minDis = Integer.MAX_VALUE;
		for (int i = 1; i < num; i++)
			minDis = Math.min(minDis, bridge(i));

//		for (int[] i : map) {
//			for (int j : i)
//				System.out.print(j + " ");
//			System.out.println();
//		}
		System.out.println(minDis);
	}

	static void sameIsland(int r, int c, int num) {
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] { r, c });
		map[r][c] = num;
		while (!q.isEmpty()) {
			r = q.peek()[0];
			c = q.poll()[1];
			for (int i = 0; i < 4; i++) {
				int nr = r + dr[i], nc = c + dc[i];
				if (nr < 0 || nr >= n || nc < 0 || nc >= n)
					continue;
				if (input[nr][nc * 2] == '1' && map[nr][nc] == 0) {
					q.add(new int[] { nr, nc });
					map[nr][nc] = num;
				}
			}
		}
	}

	static int bridge(int num) {
		boolean visited[][] = new boolean[n][n];
		Queue<int[]> q = new ArrayDeque<>();
		for (int i = 0; i < n; i++) { // 출발점들 넣기.
			for (int j = 0; j < n; j++) {
				if (map[i][j] == num) {
					visited[i][j] = true;
					q.add(new int[] { i, j, 0 });
				}
			}
		}

		while (!q.isEmpty()) {
			int r = q.peek()[0], c = q.peek()[1], len = q.poll()[2];
			for (int i = 0; i < 4; i++) {
				int nr = r + dr[i], nc = c + dc[i];
				if (nr < 0 || nr >= n || nc < 0 || nc >= n)
					continue;
				if (map[nr][nc] == 0) {
					if (visited[nr][nc] == false) { // 다리를 놓을 수 있는 경우.
						visited[nr][nc] = true;
						q.add(new int[] { nr, nc, len + 1 });
					}
				} else if (map[nr][nc] != num) // 다른 섬에 도달한 경우.
					return len;
			}
		}
		return Integer.MAX_VALUE;
	}
}