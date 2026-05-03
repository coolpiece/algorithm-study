import java.io.*;
import java.util.*;

public class Main {
	static boolean visited[];
	static int u, v;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken()),
				x = Integer.parseInt(st.nextToken());
		ArrayList<Integer> better[] = new ArrayList[n + 1]; // better[i]: i보다 등수가 더 높은 학생들.
		ArrayList<Integer> worse[] = new ArrayList[n + 1]; // worse[i]: i보다 등수가 더 낮은 학생들.

		for (int i = 1; i <= n; i++) {
			better[i] = new ArrayList<>();
			worse[i] = new ArrayList<>();
		}
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
			worse[a].add(b); // a보다 b의 등수가 더 낮음.
			better[b].add(a); // b보다 a의 등수가 더 높음.
		}

		u = 1; // 가능한 가장 높은 등수.
		v = n; // 가능한 가장 낮은 등수.

		visited = new boolean[n + 1];
		visited[x] = true;
		u += dfs(better, x, 0); // 최대 등수 내리기.

		Arrays.fill(visited, false);
		visited[x] = true;
		v -= dfs(worse, x, 0); // 최소 등수 올리기.
		System.out.println(u + " " + v);
	}

	static int dfs(ArrayList<Integer>[] rank, int cur, int cnt) { // 등수를 아는 학생 수 return.
		for (int next : rank[cur]) {
			if (!visited[next]) {
				visited[next] = true;
				cnt = dfs(rank, next, cnt + 1);
			}
		}
		return cnt;
	}
}