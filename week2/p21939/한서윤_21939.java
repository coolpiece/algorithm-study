import java.io.*;
import java.util.*;

class Problem implements Comparable<Problem> {
	int level, num;

	Problem(int level, int num) {
		this.level = level;
		this.num = num;
	}

	@Override
	public int compareTo(Problem o) { // 난이도 순으로 내림차순 정렬.
		if (o.level == this.level) // 난이도가 같으면 숫자 내림차순 정렬.
			return Integer.compare(o.num, this.num);
		return Integer.compare(o.level, this.level);
	}
}

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder rst = new StringBuilder();
		int n = Integer.parseInt(br.readLine());

		// 난이도 내림차순으로 정렬.
		TreeSet<Problem> set = new TreeSet<>();
		// key: 문제 번호, value: 문제 난이도. 검색용.
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken()), l = Integer.parseInt(st.nextToken());
			set.add(new Problem(l, p));
			map.put(p, l);
		}
		int m = Integer.parseInt(br.readLine());
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			String command = st.nextToken();
			int input = Integer.parseInt(st.nextToken());
			switch (command) {
			case "recommend":
				Problem p = (input == 1) ? set.first() : set.last();
				rst.append(p.num).append("\n");
				break;
			case "add":
				int l = Integer.parseInt(st.nextToken());
				set.add(new Problem(l, input));
				map.put(input, l);
				break;
			case "solved":
				set.remove(new Problem(map.get(input), input)); // map에서 해당 번호 문제의 난이도 검색 -> 객체로 만들어 검색, 삭제.
				map.remove(input);
				break;
			}
		}
		System.out.println(rst);
	}
}