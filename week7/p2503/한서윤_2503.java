import java.io.*;
import java.util.*;

public class Main {
	static boolean list[], visited[];
	static int count, strike, ball;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		count = 0; // 가능한 후보 수.
		list = new boolean[1000]; // list[n]: 숫자 n의 가능 여부.
		Arrays.fill(list, false);
		visited = new boolean[10]; // 순열을 위한 초기화.
		Arrays.fill(visited, false);
		makeNum(0, 0); // 순열 만들기.

		int n = Integer.parseInt(br.readLine()), s, b;
		String input;
		char num[];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			input = st.nextToken();
			num = input.toCharArray();
			s = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());

			for (int j = 123; j < 988; j++) {
				if (!list[j]) // 이미 불가능하다고 판단한 숫자 패스.
					continue;

				String tmp = Integer.toString(j);
				char target[] = tmp.toCharArray();
				strike = 0;
				ball = 0;
				check(num, target);
				if (strike != s || ball != b) { // 스트라이크와 볼 중 하나라도 다르면 불가능. 후보에서 제거.
					list[j] = false;
					count--;
				}
			}
		}
		System.out.println(count);
	}

	static void makeNum(int cnt, int number) { // 서로 다른 3개의 숫자로 만든 세 자리수.
		if (cnt == 3) {
			list[number] = true;
			count++; // 가능한 후보 수 증가.
			return;
		}

		for (int i = 1; i < 10; i++) {
			if (visited[i]) // 이미 선택한 숫자.
				continue;

			visited[i] = true;
			makeNum(cnt + 1, number * 10 + i); // 선택하는 경우.
			visited[i] = false;
		}
	}

	static void check(char[] origin, char[] compare) { // 스트라이크, 볼 계산.
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (i == j && origin[i] == compare[j])
					strike++;
				else if (i != j && origin[i] == compare[j])
					ball++;
			}
		}
	}
}