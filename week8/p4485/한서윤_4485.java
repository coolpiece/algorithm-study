import java.io.*;
import java.util.*;

class Rupee implements Comparable<Rupee> {
	int r, c, size;

	Rupee(int r, int c, int size) {
		this.r = r;
		this.c = c;
		this.size = size;
	}

	@Override
	public int compareTo(Rupee o) {
		return Integer.compare(this.size, o.size);
	}
}

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int dr[] = { -1, 1, 0, 0 }, dc[] = { 0, 0, -1, 1 };
		StringBuilder rst = new StringBuilder();

		int t = 1, n, board[][], cost[][], r, c, size;
//		boolean visited[][];
		PriorityQueue<Rupee> pq = new PriorityQueue<>(); // 선택 가능한 위치 후보. 비용이 적은 순으로 정렬.
		while (true) {
			n = Integer.parseInt(br.readLine());
			if (n == 0)
				break;
			board = new int[n][n];
			cost = new int[n][n];
//			visited = new boolean[n][n];
			for (int i = 0; i < n; i++) // 최댓값으로 초기화.
				Arrays.fill(cost[i], Integer.MAX_VALUE);

			for (int i = 0; i < n; i++) { // 동굴 현황 입력.
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++)
					board[i][j] = Integer.parseInt(st.nextToken());
			}

			cost[0][0] = board[0][0];
			pq.add(new Rupee(0, 0, board[0][0])); // 시작점 넣기.

			while (!pq.isEmpty()) {
				r = pq.peek().r;
				c = pq.peek().c;
				size = pq.poll().size;

				if (r == n - 1 && c == n - 1) { // (추가) 이미 목적지에 도달함. 조기 종료.
					cost[r][c] = size;
					break;
				}
//				if (visited[r][c])
//					continue; // 이미 선택한 위치.
//
//				visited[r][c] = true; // 방문 처리.
//				cost[r][c] = size; // 최소 비용 업데이트.

				if (size > cost[r][c]) // 이미 더 짧은 경로를 찾은 경우 패스.
					continue;

				for (int i = 0; i < 4; i++) { // 상하좌우 탐색. 아직 선택하지 않은 위치 추가.
					int nr = r + dr[i], nc = c + dc[i];
					if (nr < 0 || nr >= n || nc < 0 || nc >= n)
						continue;
//					if (visited[nr][nc]) // 비용 계산이 끝난 위치는 다시 계산할 필요 없음. (가장 최소인 값만 갱신했기 때문에 더 작아질 수 x.)
//						continue;

					// (추가) 이미 찾은 경로보다 더 적을 때만 큐에 넣음.
					int nextSize = size + board[nr][nc];
					if (cost[nr][nc] > nextSize) {
						cost[nr][nc] = nextSize; // 미리 업데이트.
						pq.add(new Rupee(nr, nc, nextSize));
					}
				}
			}
			rst.append("Problem ").append(t).append(": ").append(cost[n - 1][n - 1]).append("\n");
			t++;
			pq.clear();
		}
		System.out.println(rst);
	}
}