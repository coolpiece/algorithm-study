import java.util.*;

// S'(이다‘솜’파의 학생을 나타냄) 또는 'Y'(임도‘연’파의 학생
// S가 적어도 4명 이상이어야 함

public class java_1941_윤소윤 {
	static char [][] stu = new char[5][5]; //학생행
	static int ans = 0; // 가능 횟수 
	
	static boolean[] selected = new boolean[25]; // 선택 여부 
	
	// 4방향
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);	
		
		String [] mid = new String[5];
		
		// 행렬 입력받
		for (int i = 0; i < 5; i++) {
			mid[i] = sc.next();
		}
		
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				stu[i][j] = mid[i].charAt(j);
			}
		}
		
		// 조합 구성
		comb(0,0,0);
		System.out.println(ans);
		sc.close();
		
	}
	// idx : 어디 위치를 볼지 결정 
	// cnt : 지금까지 뽑은 인원 수 
	// Scnt : 지금까지의 S의 수  
	static void comb (int idx, int cnt, int Scnt) {
		if (cnt - Scnt >= 4) return; // S의 수가 4 미만임
		
		// 7명 뽑아서 연결되었는지 검사 
		if (cnt == 7) {
			if (isConnected()) ans++;
			return;
		}
		
		// 7명을 못 뽑은 경우
		if (idx == 25) return;
		
		// 선택하는 경우 
		selected[idx] = true;
		int r = idx / 5;
		int c = idx % 5;
		
		if (stu[r][c] == 'S') comb(idx+1, cnt+1, Scnt+1); // S인 경우
		else comb(idx + 1, cnt + 1, Scnt); // S가 아닌 경우
		
		selected[idx] = false;
		comb(idx+1, cnt, Scnt);		
	}
	
	// 7칸이 연결되었는지 확인 
	static boolean isConnected() {
		Queue<Integer> q = new ArrayDeque<>();
		boolean[] vis = new boolean[25];
		
		// 시작위치 찾기 
		int start = -1;
		for (int i = 0; i < 25; i++) {
			if (selected[i]) {
				start = i;
				break;
			}
		}
		
		q.offer(start);
		vis[start] = true;
		int connectedCnt = 1;
		
		while (!q.isEmpty()) {
			int cur = q.poll();
			int r = cur/5;
			int c= cur%5;
			
			for (int k = 0; k < 4; k++) {
				int nr = r + dr[k];
				int nc = c + dc[k];
				if (nr < 0 || nr >= 5 || nc < 0 || nc >= 5) continue;
				
				int nxt = nr*5 + nc;
				if (!selected[nxt]) continue;   // 7명 중에 포함된 칸만 이동
                if (vis[nxt]) continue;

                vis[nxt] = true;
                q.offer(nxt);
                connectedCnt++;
			}
		}
		return connectedCnt == 7;
		
		
	}

}
