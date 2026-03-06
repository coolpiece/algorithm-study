import java.io.*;
import java.util.*;

class Info { // 좌표, 위치 저장.
	int r, c, height;

	Info(int r, int c, int height) {
		this.r = r;
		this.c = c;
		this.height = height;
	}
}

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int dr[] = { -1, 1, 0, 0 }, dc[] = { 0, 0, -1, 1 };
		int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());
		int ice[][] = new int[n][m];
		int startR = 0, startC = 0, iceCnt = 0; // iceCnt: 빙산 수.
		for (int i = 0; i < n; i++) { // 입력.
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				ice[i][j] = Integer.parseInt(st.nextToken());
				if (ice[i][j] > 0) {
					iceCnt++;
					if (startR == 0 && startC == 0) { // 맨 처음 탐색 시작점 저장.
						startR = i;
						startC = j;
					}
				}
			}
		}

		int year = 0;
		List<Info> list = new ArrayList<>(); // 확인된 빙산 위치 저장.
		while (true) {
//			for (int i = 0; i < n; i++) {
//				for (int j = 0; j < m; j++)
//					System.out.print(ice[i][j] + " ");
//				System.out.println();
//			}

			boolean visited[][] = new boolean[n][m]; // 선택 여부 저장.
			Queue<Info> q = new ArrayDeque<>(); // bfs용.

			if (!list.isEmpty()) { // 탐색 시작점 찾기.
				for (Info info : list) {
					if (info.height != 0) {
						startR = info.r;
						startC = info.c;
						break;
					}
				}
			}
			list.clear();
			visited[startR][startC] = true;
			q.add(new Info(startR, startC, ice[startR][startC]));

			while (!q.isEmpty()) {
				Info cur = q.poll(); // 맨 앞에 있는 위치 꺼내서 다른 칸과 연결 확인 && 계산.
				int r = cur.r, c = cur.c, h = cur.height;
				for (int i = 0; i < 4; i++) { // 4방향 탐색.
					int nr = r + dr[i], nc = c + dc[i];
					if (nr < 0 || nr >= n || nc < 0 || nc >= m)
						continue;
					if (ice[nr][nc] > 0 && !visited[nr][nc]) {
						q.add(new Info(nr, nc, ice[nr][nc]));
						visited[nr][nc] = true;
					} else if (ice[nr][nc] == 0)
						h--;
				}
				if (h < 0)
					h = 0;
				list.add(new Info(r, c, h));
			}
			if (list.size() != iceCnt) { // 연결된 빙산 수와 총 빙산 수가 다르면 연결이 끊어진 것.
				System.out.println(year);
				return;
			}

			for (Info info : list) { // 빙산 높이 업데이트.
				ice[info.r][info.c] = info.height;
				if (info.height == 0)
					iceCnt--;
			}
			year++;
			if (iceCnt == 0)
				break;
		}
		System.out.println(0); // 여기까지 왔다면 빙산이 끊어지지 않은 것이다...
	}
}