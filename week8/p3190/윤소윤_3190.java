import java.io.*;
import java.util.*;

public class 윤소윤_3190 {

    static int N, d = 0;
    static int [][] map;
    static Map<Integer,String> moveInfo;
    static int [] dx = {1, 0, -1, 0};
    static int [] dy = {0, 1, 0, -1};

    public static void main (String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine()); // 보드 크기
        int K = Integer.parseInt(br.readLine()); // 사과 개수
        map = new int [N][N];
        moveInfo = new HashMap<>();

        for(int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            map[x][y] = 1; // 사과
        }

        int L = Integer.parseInt(br.readLine());
        for(int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken());
            String dir = st.nextToken();
            moveInfo.put(X,dir);
        }
        solve();
    }

    public static void solve() {
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        int time = 0;
        int px = 0;
        int py = 0;

        while (true) {
            int nx = px + dx[d];
            int ny = py + dy[d];
            time++;

            // 벽 부딪힘
            if(nx < 0 || ny < 0 || nx >= N || ny >= N) break;

            // 몸 부딪힘
            if(q.contains(ny*N+nx)) break;

            //뱀 이동
            if(map[ny][nx] == 1) {
                map[ny][nx] = 0;
                q.add(ny*N + nx);
            } else {
                q.add(ny*N + nx);
                q.poll();
            }

            // 방향 전환
            if(moveInfo.containsKey(time)) {
                String data = moveInfo.get(time);
                if(data.equals("D")) {
                    d += 1;
                    if(d==4) d=0;
                } else {
                    d -= 1;
                    if (d == -1) d = 3;
                }
            }
            px = nx;
            py = ny;
        }
        System.out.println(time);
    }
}

