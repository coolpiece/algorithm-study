import java.io.*;
import java.util.*;

public class Main {
	static int paper[][], cnt[] = new int[3];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		paper = new int[n][n];
		for (int i = 0; i < n; i++) { // 입력.
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++)
				paper[i][j] = Integer.parseInt(st.nextToken());
		}
		
		cut(0, 0, n);
		
		System.out.println(cnt[0]);
		System.out.println(cnt[1]);
		System.out.println(cnt[2]);
	}

	static void cut(int r, int c, int size) {
		if (size == 1) { // 더 이상 자를 수 없는 경우.
			cnt[paper[r][c] + 1]++;
			return;
		}
		int sameCnt = 0;
		int num = paper[r][c];
		x: for (int i = 0; i < size; i += size / 3) {
			for (int j = 0; j < size; j++) {
				if (paper[r + i][c + j] != num) // 잘라야 하는 경우.
					break x;
			}
			sameCnt += 3; // 자르지 않아도 되는 경우.
		}

		if (sameCnt == 9) { // 9개 부분이 모두 같은 숫자면 하나로 결합.
			cnt[num + 1]++;
			return;
		}

		size /= 3; // 9분할.
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				cut(r + i * size, c + j * size, size);
			}
		}
	}
}