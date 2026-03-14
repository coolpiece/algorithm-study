import java.io.*;
import java.util.*;

class Node { // 위치 저장.
	int r, c, dis;

	Node(int r, int c, int dis) {
		this.r = r;
		this.c = c;
		this.dis = dis;
	}
}

public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	static int dr[] = { -1, 1, 0, 0 }, dc[] = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());
		char map[][] = new char[n][];
		List<Node> list = new ArrayList<>(); // 육지 위치 저장.
		for (int i = 0; i < n; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < m; j++)
				if (map[i][j] == 'L')
					list.add(new Node(i, j, 0));
		}

		int maxDis = 0;
		boolean visited[][] = new boolean[n][m];
		for (Node node : list) {
			Queue<Node> q = new ArrayDeque<>();
			for (int i = 0; i < n; i++) // 개선. 매번 객체 새로 만드는 대신 값 초기화 하기.
				Arrays.fill(visited[i], false);
			q.add(node);
			visited[node.r][node.c] = true;
			while (!q.isEmpty()) {
				Node cur = q.poll();
				maxDis = Math.max(maxDis, cur.dis); // 최대 거리 갱신.
				for (int i = 0; i < 4; i++) {
					int nr = cur.r + dr[i], nc = cur.c + dc[i];
					if (nr < 0 || nr >= n || nc < 0 || nc >= m)
						continue;
					if (map[nr][nc] == 'L' && !visited[nr][nc]) {
						visited[nr][nc] = true;
						q.add(new Node(nr, nc, cur.dis + 1));
					}
				}
			}
		}
		System.out.println(maxDis);
	}
}