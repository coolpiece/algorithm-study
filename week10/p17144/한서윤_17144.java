import java.io.*;
import java.util.*;

class Dust {
	int r, c, amount;

	Dust(int r, int c, int amount) {
		this.r = r;
		this.c = c;
		this.amount = amount;
	}
}

public class Main {
	static int dr[] = { -1, 0, 1, 0 }, dc[] = { 0, 1, 0, -1 }; // 상, 우, 하, 좌
	static int r, c, room[][], up, down;
	static Queue<Dust> q;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		int t = Integer.parseInt(st.nextToken());
		room = new int[r][c];
		q = new ArrayDeque<>(); // 미세먼지 위치 저장.

		up = down = -1;
		for (int i = 0; i < r; i++) { // 입력.
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < c; j++) {
				room[i][j] = Integer.parseInt(st.nextToken());
				if (room[i][j] > 0)
					q.add(new Dust(i, j, room[i][j]));
				if (room[i][j] == -1 && up == -1) { // 공기청정기 위치 저장.
					up = i;
					down = i + 1;
				}
			}
		}

		int rst = 0;
		for (int i = 1; i <= t; i++) {
			dust();
			blow();
			if (i == t) { // t초가 모두 지난 경우 계산 후 종료.
				for (int j = 0; j < r; j++) {
					for (int k = 0; k < c; k++) {
						if (room[j][k] > 0)
							rst += room[j][k];
					}
				}
				break;
			}

			// 다음 수행을 위해 다시 큐에 넣기.
			for (int j = 0; j < r; j++) {
				for (int k = 0; k < c; k++) {
					if (room[j][k] > 0)
						q.add(new Dust(j, k, room[j][k]));
				}
			}
		}
		System.out.println(rst);
	}

	static void dust() { // 미세먼지 확산.
		while (!q.isEmpty()) {
			Dust cur = q.poll();
			int cnt = 0;
			for (int i = 0; i < 4; i++) {
				int nr = cur.r + dr[i], nc = cur.c + dc[i];
				if (nr < 0 || nr >= r || nc < 0 || nc >= c) // 범위를 넘어가는 경우.
					continue;
				if (room[nr][nc] == -1) // 공기청정기가 있는 경우.
					continue;
				room[nr][nc] += cur.amount / 5;
				cnt++;
			}
			room[cur.r][cur.c] -= cur.amount / 5 * cnt;
		}
	}

	static void blow() {
		// 위쪽 공기청정기 바람.
		int nr = up - 1, nc = 0;
		for (int i = 0; i < 4; i++) { // 상, 우, 하, 좌 순으로 이동.
			while (true) { // 벽에 닿으면 방향 전환.
				if (nr + dr[i] < 0 || nr + dr[i] >= down || nc + dc[i] < 0 || nc + dc[i] >= c)
					break;
				if (room[nr + dr[i]][nc + dc[i]] == -1) {
					room[nr][nc] = 0;
					break;
				}
				room[nr][nc] = room[nr + dr[i]][nc + dc[i]];
				nr += dr[i];
				nc += dc[i];
			}
		}

		// 아래쪽 공기청정기 바람.
		nr = down + 1;
		nc = 0;
		for (int i = 2; i >= -1; i--) { // 하, 우, 상, 좌 순으로 이동.
			if (i == -1)
				i = 3;
			while (true) { // 벽에 닿으면 방향 전환.
				if (nr + dr[i] < down || nr + dr[i] >= r || nc + dc[i] < 0 || nc + dc[i] >= c)
					break;
				if (room[nr + dr[i]][nc + dc[i]] == -1) {
					room[nr][nc] = 0;
					break;
				}
				room[nr][nc] = room[nr + dr[i]][nc + dc[i]];
				nr += dr[i];
				nc += dc[i];
			}
			if (i == 3)
				break;
		}
	}

	static void printRoom() { // 확인용.
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++)
				System.out.print(room[i][j] + " ");
			System.out.println();
		}
		System.out.println();
	}
}