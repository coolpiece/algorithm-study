import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder rst;
	static int parent[], rank[];

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		rst = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			rst.append("Scenario ").append(t).append(":\n");
			int n = Integer.parseInt(br.readLine()), k = Integer.parseInt(br.readLine());
			parent = new int[n];
			rank = new int[n];
			for (int i = 0; i < n; i++) // 초기 집합.
				parent[i] = i;
			for (int i = 0; i < k; i++) { // 친구 관계.
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
				union(a, b);
			}
			int m = Integer.parseInt(br.readLine());
			for (int i = 0; i < m; i++) { // 구해야하는 쌍.
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken()), v = Integer.parseInt(st.nextToken());
				if (findSet(u) == findSet(v)) // 대표자가 같으면 같은 집합.(친구 관계.)
					rst.append("1\n");
				else
					rst.append("0\n");
			}
			rst.append("\n");
		}
		System.out.println(rst);
	}

	static int findSet(int a) { // 대표자 찾기.
		if (a == parent[a])
			return a;
		return parent[a] = findSet(parent[a]);
	}

	static void union(int a, int b) {
		int pa = findSet(a), pb = findSet(b);
		if (pa == pb)
			return;
		if (rank[pa] > rank[pb]) {
			parent[pb] = pa;
			rank[pa]++;
		} else {
			parent[pa] = pb;
			rank[pb]++;
		}
	}
}