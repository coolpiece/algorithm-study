import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	static int dr[] = { -1, 1, 0, 0 }, dc[] = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> pq = new PriorityQueue<>(); // 값 저장.
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				pq.add(Integer.parseInt(st.nextToken()));
				if (pq.size() > n)
					pq.poll();
			}
		} // end of input.
		System.out.println(pq.peek());
	}
}


// 개선 전 무식한 방식 ...
//import java.io.*;
//import java.util.*;
//
//public class Main {
//	static BufferedReader br;
//	static StringTokenizer st;
//	static int dr[] = { -1, 1, 0, 0 }, dc[] = { 0, 0, -1, 1 };
//
//	public static void main(String[] args) throws Exception {
//		br = new BufferedReader(new InputStreamReader(System.in));
//		int n = Integer.parseInt(br.readLine());
//		int map[][] = new int[n][n];
//		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
//			public int compare(int[] a, int[] b) {
//				return b[0] - a[0];
//			}
//		}); // 값, 속한 열 저장.
//		for (int i = 0; i < n; i++) {
//			st = new StringTokenizer(br.readLine());
//			for (int j = 0; j < n; j++) {
//				map[i][j] = Integer.parseInt(st.nextToken());
//				if (i == n - 1)
//					pq.add(new int[] { map[i][j], j });
//			}
//		} // end of input.
//
//		int order[] = new int[n]; // 각 열 별 현재 인덱스 저장.
//		Arrays.fill(order, n - 1);
//		int idx = 1; // idx번째 큰 수.
//		while (!pq.isEmpty()) {
//			int cur[] = pq.poll();
//			if (idx == n) {
//				System.out.println(cur[0]);
//				return;
//			}
//			int line = cur[1];
//			int curIdx = --order[line];
//			if (line >= 0)
//				pq.add(new int[] { map[curIdx][line], line }); // 인덱스 넘어가는 것 방지.
//			idx++;
//		}
//	}
//}