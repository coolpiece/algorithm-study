import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 윤소윤_2589 {
    static int [] dx = {-1, 1, 0, 0};
    static int [] dy = {0, 0, -1, 1};
    static char [][] map;
    static boolean [][] visit;
    static int N, M;
    static int time;

    public static void main (String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char [N][M];

        for(int i = 0; i < N; i++) {
            String str = br.readLine();
            for(int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        int res = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(map[i][j] == 'L') {
                    visit = new boolean[N][M];
                    time = 0;
                    bfs(i,j);
                    res = Math.max(res, time);
                }
            }
        }
        System.out.println(res);
    }

    private static void bfs(int ni, int nj) {
        ArrayDeque<int []> q = new ArrayDeque<>();
        int [][] dist = new int [N][M];
        for(int i = 0; i < N; i++) Arrays.fill(dist[i], -1);

        visit[ni][nj] = true;
        dist[ni][nj] = 0;
        q.offer(new int[] {ni,nj});

        while(!q.isEmpty()) {
            int [] cur = q.poll();
            int x = cur[0], y = cur[1];

            for(int dir = 0; dir < 4; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];

                if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                if(visit[nx][ny]) continue;
                if(map[nx][ny] != 'L') continue;

                visit[nx][ny] = true;
                dist[nx][ny] = dist[x][y] + 1;
                time = Math.max(time, dist[nx][ny]);
                q.offer(new int [] {nx,ny});
            }
        }

    }
}
