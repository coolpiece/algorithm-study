import java.io.*;
import java.util.*;

class Edge implements Comparable<Edge> {
	int idx, weight;

	Edge(int idx, int weight) {
		this.idx = idx;
		this.weight = weight;
	}

	@Override
	public int compareTo(Edge o) { // 가중치 오름차순 정렬.
		return this.weight - o.weight;
	}
}

public class Main {
	static BufferedReader br;
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int cost[][] = new int[n][n]; // 비용.
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				cost[i][j] = Integer.parseInt(st.nextToken());
			}
		} // end of input.

		// 우선순위 큐를 사용한 프림 알고리즘으로 해결.
		int minEdge[] = new int[n]; // MST에 연결하기 위한 최소 간선 가중치.
		boolean visited[] = new boolean[n]; // MST 포함 여부.
		Arrays.fill(minEdge, Integer.MAX_VALUE);

		minEdge[0] = 0; // 시작 정점.
		long sum = 0; // MST 총 가중치 합. 수가 크므로 int 말고 long 사용.
		PriorityQueue<Edge> pq = new PriorityQueue<>(); // 값 저장.
		pq.add(new Edge(0, 0));

		while (!pq.isEmpty()) {
			Edge e = pq.poll();
			if (visited[e.idx]) // 이미 포함된 경우.
				continue;
			visited[e.idx] = true;
			sum += e.weight;
			for (int i = 0; i < n; i++) { // 인접 정접과의 정보 추가.
				if (!visited[i] && cost[e.idx][i] < minEdge[i]) {
					minEdge[i] = cost[e.idx][i];
					pq.add(new Edge(i, minEdge[i]));
				}
			}
		}
		System.out.println(sum);
	}
}