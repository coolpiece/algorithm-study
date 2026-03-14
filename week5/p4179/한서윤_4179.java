import java.io.*;
import java.util.*;

class Node {
	int x, y, time;
	char state; // 'F': 불, 'J': 지훈.

	Node(int x, int y, int time, char state) {
		this.x = x;
		this.y = y;
		this.time = time;
		this.state = state;
	}
}

public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	static int dr[] = { -1, 1, 0, 0 }, dc[] = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken()), c = Integer.parseInt(st.nextToken()), startR = 0, startC = 0;
		char map[][] = new char[r][];
		boolean visited[][] = new boolean[r][c];
		Queue<Node> q = new ArrayDeque<>(); // 불 먼저, 지훈이 이동 다음. 한 번에 저장.
		for (int i = 0; i < r; i++) { // input
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < c; j++) {
				if (map[i][j] == 'J') { // 지훈이 초기 위치 저장.
					map[i][j] = '.';
					startR = i;
					startC = j;
				}
				if (map[i][j] == 'F') {
					q.add(new Node(i, j, 0, map[i][j]));
					visited[i][j] = true;
				}
			}
		} // end of input
		visited[startR][startC] = true;
		q.add(new Node(startR, startC, 0, 'J'));

		int move = 1; // 이동할 수 있는 경우의 수 관리. 0이되면 더 이상 이동할 수 없으므로 종료.
		while (!q.isEmpty()) {
			int curR = q.peek().x, curC = q.peek().y, t = q.peek().time;
			char state = q.poll().state;
			if (state == 'J')
				move--;
			for (int i = 0; i < 4; i++) {
				int nr = curR + dr[i], nc = curC + dc[i];
				if (nr < 0 || nr >= r || nc < 0 || nc >= c) {
					if (state == 'J') { // 탈출 성공 !
						System.out.println(t + 1);
						return;
					}
					continue;
				}

				// 기존 로직 수정
				if (state == 'F') {
					if (map[nr][nc] == '.') {
						map[nr][nc] = 'F'; // 불길이 번짐.
						q.add(new Node(nr, nc, t + 1, state)); // 다음 이동 저장.
					}
				}
				else if (state == 'J') {
					if (!visited[nr][nc] && map[nr][nc] == '.') {
						visited[nr][nc] = true; // 방문 여부 체크.
						q.add(new Node(nr, nc, t + 1, state)); // 다음 이동 저장.
						move++;
					}
				}
			}
			if (move == 0) // 더 이상 이동할 곳이 없음.
				break;
		}
		System.out.println("IMPOSSIBLE");
	}
}