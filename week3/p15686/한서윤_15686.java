import java.io.*;
import java.util.*;

public class Main {
	private static int m, pick[], minVal;
	private static ArrayList<int[]> home, chicken;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		home = new ArrayList<>();
		chicken = new ArrayList<>();
		for (int i = 0; i < n; i++) { // 입력.
			String str = br.readLine(); // 2차원 배열 삭제.
			for (int j = 0; j < n * 2; j += 2) {
				if (str.charAt(j) == '1')
					home.add(new int[] { i, j / 2 });
				else if (str.charAt(j) == '2')
					chicken.add(new int[] { i, j / 2 });
			}
		}
		pick = new int[m];
		minVal = Integer.MAX_VALUE;
		combi(0, 0);
		System.out.println(minVal);
	}

	static void combi(int start, int cnt) { // 치킨집 고르기.
		if (cnt == m) {
			int sum = 0;
			for (int[] i : home) { // for-each 구문으로 수정.
				int cal = Integer.MAX_VALUE, r = i[0], c = i[1];
				for (int j : pick) { // for-each 구문으로 수정.
					int dis = Math.abs(r - chicken.get(j)[0]) + Math.abs(c - chicken.get(j)[1]);
					cal = Math.min(cal, dis);
				}
				sum += cal;
			}
			minVal = Math.min(minVal, sum);
			return;
		}
		for (int i = start; i < chicken.size(); i++) {
			pick[cnt] = i;
			combi(i + 1, cnt + 1);
		}
	}
}