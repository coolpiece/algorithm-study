import java.io.*;
import java.util.*;

class Pos { // 위치 저장.
	int x, y;

	Pos(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

class Turn { // 방향 전환.
	int time;
	String dir;

	Turn(int time, String dir) {
		this.time = time;
		this.dir = dir;
	}
}

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine()), k = Integer.parseInt(br.readLine());
		int board[][] = new int[n + 1][n + 1]; // 0: 빈 칸, 1: 뱀, 2: 사과.
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()), c = Integer.parseInt(st.nextToken());
			board[r][c] = 2;
		}
		int l = Integer.parseInt(br.readLine());
		Queue<Turn> q = new ArrayDeque<>(); // 방향 전환 저장.
		for (int i = 0; i < l; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			String c = st.nextToken();
			q.add(new Turn(x, c));
		}
		Deque<Pos> snake = new ArrayDeque<>();
		snake.add(new Pos(1, 1));
		board[1][1] = 1;

		int dr[] = { -1, 0, 1, 0 }, dc[] = { 0, 1, 0, -1 }; // 상 우 하 좌.
		int time = 1, dir = 1; // 처음 진행 방향 오른쪽.
		while (true) { // 시뮬레이션.
			int nr = snake.peek().x + dr[dir], nc = snake.peek().y + dc[dir];
			if (nr <= 0 || nr > n || nc <= 0 || nc > n) // 벽에 부딪히면 게임 종료.
				break;
			if (board[nr][nc] == 1) // 자기자신의 몸과 부딪히면 게임 종료.
				break;

			if (board[nr][nc] == 0) { // 꼬리 한 칸 앞으로 전진.
				Pos tail = snake.pollLast();
				board[tail.x][tail.y] = 0;
			}
			// else if (board[nr][nc] == 2) ; // 사과를 먹으면 한 칸 늘어남. (꼬리 움직일 필요 x.)
			snake.addFirst(new Pos(nr, nc)); // 머리 앞으로 이동.
			board[nr][nc] = 1;

			if (!q.isEmpty() && time == q.peek().time) { // 방향 전환 확인.
				switch (q.poll().dir) {
				case "L": // 왼쪽으로 회전.
					dir = (dir + 3) % 4;
					break;
				case "D": // 오른쪽으로 회전.
					dir = (dir + 1) % 4;
					break;
				}
			}
			time++;
		}
		System.out.println(time);
	}

	static void printMap(int time, int n, int board[][]) { // 확인용.
		System.out.println(time);
		for (int i = 0; i < n; i++) {
			System.out.println(Arrays.toString(board[i]));
		}
		System.out.println();
	}
}