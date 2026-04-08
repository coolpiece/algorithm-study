import java.io.*;
import java.util.*;

public class Main {
	static int n, total, order[], score[][];
	static boolean picked[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		score = new int[n][9];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++) {
				score[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		order = new int[9]; // 타격 순서 저장.
		picked = new boolean[9]; // picked[i]: i+1번 선수 선택 여부.

//		4번 타자는 1번 선수.
//		order[3] = 0; // 어차피 0으로 초기화 되므로 삭제.
		picked[0] = true;

		total = 0;
		permutation(0);
		System.out.println(total);
	}

	static void permutation(int cnt) { // cnt번 타자 선택 / 비선택.
		if (cnt == 9) { // 순열 완성.
			total = Math.max(total, calScore());
			return;
		}

		// 4번 타자 이미 결정됨. 패스.
		if (cnt == 3) {
			permutation(cnt + 1);
			return;
		}

		// 순열 만들기.
		for (int i = 1; i < 9; i++) {
			if (!picked[i]) {
				picked[i] = true;
				order[cnt] = i;
				permutation(cnt + 1);
				picked[i] = false;
			}
		}
	}

// 기존 방식.
//	static int calScore() {
//		int finalScore = 0;
//		int cur = 0; // 현재 타순.
//
//		for (int i = 0; i < n; i++) {
//			int outCnt = 0;
//			boolean b1 = false, b2 = false, b3 = false; // 1루, 2루, 3루의 주자 존재 여부.
//
//			while (outCnt < 3) { // 삼진 아웃이면 이닝 종료.
//				int hit = score[i][order[cur]];
//
//				if (hit == 0) // 아웃.
//					outCnt++;
//				else if (hit == 1) { // 1루타.
//					if (b3) // 3루 주자 홈인.
//						finalScore++;
//					b3 = b2; // 1, 2루 주자 한 칸씩 이동.
//					b2 = b1;
//					b1 = true; // 타자 1루로.
//				} else if (hit == 2) { // 2루타.
//					if (b3) // 2, 3루 주자 홈인.
//						finalScore++;
//					if (b2)
//						finalScore++;
//					b3 = b1; // 1루 주자 3루로.
//					b2 = true; // 타자 2루로.
//					b1 = false;
//				} else if (hit == 3) { // 3루타.
//					if (b3) // 1, 2, 3루 주자 홈인.
//						finalScore++;
//					if (b2)
//						finalScore++;
//					if (b1)
//						finalScore++;
//					b3 = true; // 타자 3루로.
//					b2 = b1 = false;
//				} else { // 홈런.
//					if (b3) // 1, 2, 3루 주자 홈인.
//						finalScore++;
//					if (b2)
//						finalScore++;
//					if (b1)
//						finalScore++;
//					finalScore++; // 타자 홈인.
//					b3 = b2 = b1 = false;
//
//				}
//				cur = (cur + 1) % 9;
//			}
//		}
//		return finalScore;
//	}

	// 개선된 버전. (비트 마스킹)
	static int calScore() {
		int finalScore = 0;
		int cur = 0; // 현재 타순.

		for (int i = 0; i < n; i++) {
			int outCnt = 0;
			int base = 0; // 비트 마스크. 000. (3루|2루|1루)

			while (outCnt < 3) {
				int hit = score[i][order[cur]];

				if (hit == 0) // 아웃.
					outCnt++;
				else {
					// 타자를 베이스에 올림. (_ _ _ 1 형태)
					base = (base << 1) | 1;
					// 타격 결과만큼 다같이 진루. 이미 한 칸씩 움직였으므로 (hit-1)만큼만 추가로 이동.
					base <<= (hit - 1);

					// 홈으로 들어온 주자.
					finalScore += Integer.bitCount(base >> 3);
					// 1, 2, 3루만 남기고 상위 비트 삭제. (0000 0111)
					base &= 7;
				}
				cur = (cur + 1) % 9;
			}
		}
		return finalScore;
	}
}