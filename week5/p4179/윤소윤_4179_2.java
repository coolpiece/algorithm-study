import java.io.*;
import java.util.*;

public class 윤소윤_4179 {
    static int [] dx = {-1, 1, 0, 0};
    static int [] dy = {0, 0, -1, 1};

    static char [][] map;
    static int R, C;
    static int [][] fireDist; // 불이 도착하는 시간
    static int [][] JihunDist; // 지훈이가 도착하는 시간

    public static void main (String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        fireDist = new int[R][C];
        JihunDist = new int[R][C];

        for (int i = 0; i < R; i++) {
            Arrays.fill(fireDist[i], -1);
            Arrays.fill(JihunDist[i], -1);
        }

        Deque<int[]> fireQ = new ArrayDeque<>();
        Deque<int[]> jihunQ = new ArrayDeque<>();

        for(int i = 0; i < R; i++) {
            String str = br.readLine();
            for(int j = 0; j < C; j++) {
                map[i][j] = str.charAt(j);

                if(map[i][j] == 'F') {
                    fireQ.add(new int[] {i,j});
                    fireDist[i][j] = 0;
                }
                else if(map[i][j] == 'J') {
                    jihunQ.add(new int[] {i,j});
                    JihunDist[i][j] = 0;
                }
            }
        }

        // 1) 불 BFS: 각 칸에 불이 몇 초에 도착하는지 계산
        while (!fireQ.isEmpty()) {
            int[] cur = fireQ.poll();
            int x = cur[0], y = cur[1];

            for (int dir = 0; dir < 4; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];

                if (nx < 0 || ny < 0 || nx >= R || ny >= C) continue;
                if (map[nx][ny] == '#') continue;       // 벽
                if (fireDist[nx][ny] != -1) continue;   // 이미 방문

                fireDist[nx][ny] = fireDist[x][y] + 1;
                fireQ.add(new int[]{nx, ny});
            }
        }

        while(!jihunQ.isEmpty()) {
            int [] cur = jihunQ.poll();
            int x = cur[0], y = cur[1];
            int t = JihunDist[x][y];

            for(int dir = 0; dir < 4; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];

                if (nx < 0 || ny < 0 || nx >= R || ny >= C) {
                    System.out.println(t + 1);
                    return;
                }
                if (map[nx][ny] == '#') continue;       // 벽
                if(JihunDist[nx][ny] != -1) continue;
                if (map[nx][ny] != '.') continue;

                if (fireDist[nx][ny] != -1 && fireDist[nx][ny] <= t + 1) continue;

                JihunDist[nx][ny] = JihunDist[x][y]+ 1;
                jihunQ.add(new int[] {nx, ny});
            }
        }

        System.out.println("IMPOSSIBLE");
    }


}
