import java.io.*;
import java.util.*;

public class Main {
	public static char[][] list = new char[5][5]; // 입력 받은 배열.
//	public static boolean[] selected = new boolean[25]; // 선택 여부 저장.
	public static int[] picked = new int[7]; // 선택한 7명의 번호.
	public static int rst = 0;
	public static int[] dx = { -1, 0, 0, 1 }, dy = { 0, -1, 1, 0 };

	public static boolean isConnected() {
		Queue<Integer> q = new ArrayDeque<>(); // bfs로 탐색.
		boolean[] visited = new boolean[7];
		q.add(picked[0]);
		visited[0] = true;
		int connect = 1;
		while (!q.isEmpty()) {
			int cur = q.poll();
			int x = cur / 5, y = cur % 5;
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i], ny = y + dy[i];
				if (nx < 0 || nx >= 5 || ny < 0 || ny >= 5)
					continue; // 상하좌우로 나아갈 수 없으면
				for (int j = 0; j < 7; j++) {
					if (!visited[j] && picked[j] == (nx * 5 + ny)) {
						visited[j] = true;
						q.add(picked[j]);
						connect++;
					}
				}
			}
		}
		return connect == 7;
	}

	public static void pick(int start, int count, int cntY) {
		if (cntY >= 4)
			return;
		if (count == 7) { // 7명을 다 뽑았으면,
			if (isConnected()) {
				rst++; // 모두 연결되어 있는지 확인.
//				for (int i = 0; i < 7; i++)
//					System.out.print(picked[i] + " ");
//				System.out.println();
			}
			return;
		}

		for (int i = start; i < 25; i++) { // 7명 뽑기. (0~25로 숫자 매기고 추후에 좌표로 변환)
			picked[count] = i;
			if (list[i / 5][i % 5] == 'Y')
				pick(i + 1, count + 1, cntY + 1);
			else
				pick(i + 1, count + 1, cntY);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 0; i < 5; i++) {
			String input = br.readLine();
			for (int j = 0; j < 5; j++) // 입력 받기.
				list[i][j] = input.charAt(j);
		}
		pick(0, 0, 0);
		System.out.println(rst);
	}
}