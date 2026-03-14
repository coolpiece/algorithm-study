package week5.p4179;

import java.io.*;
import java.util.*;

public class 김상균_4179 {

    static int[][] map;
    static int R, C;

    static int jx, jy;
    static List<Node> fires;

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    // 0 : 미방문
    // 1 : 사람이 방문
    // 2 : 불이 덮어버림
    static int[][] visited;

    static int result = 0;
    static boolean flag = false;

    // 0 : 그냥 공간
    // 1 : 벽
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        visited = new int[R][C];

        fires = new ArrayList<>();

        for(int i=0;i<R;i++) {
            String input = br.readLine();

            for(int j=0;j<C;j++) {
                if(input.charAt(j) == '#') {
                    map[i][j] = 1;
                }

                if(input.charAt(j) == 'J') {
                    jx = i;
                    jy = j;
                }

                if(input.charAt(j) == 'F') {
                    fires.add(new Node(i, j, 1));
                }
            }
        }

        bfs();

        if(!flag) {
            System.out.println("IMPOSSIBLE");
        } else {
            System.out.println(result);
        }


    }

    // 1. 불을 먼저 집어넣음
    // 2. 불을 꺼내고, 그 불이 덮어버리는 공간을 체크
    // 3. 그 다음 불을 만나기 전까지 계속 이동가능한 위치 꺼냄
    // 4. 만약 이동 가능한 위치가 하나라도 있다면 그걸 큐에 집어넣음
    // 4. 만약 이동 가능한 위치가 하나도 없다면, 그대로 IMPOSSIBLE
    static void bfs() {
        Queue<Node> queue = new ArrayDeque<>();

        for(Node fire : fires) {
            queue.add(new Node(fire.x, fire.y, 1));
            visited[fire.x][fire.y] = 2;
        }

        queue.add(new Node(jx, jy, 0));
        visited[jx][jy] = 1;

        while(!queue.isEmpty()) {
            result++;

            List<Node> nextNodes = new ArrayList<>();

            // 불을 꺼냈다면
            while(!queue.isEmpty() && queue.peek().type != 0) {
                Node fire = queue.poll();

                int fireX = fire.x;
                int fireY = fire.y;

                for(int i=0;i<4;i++) {
                    int nextX = fireX + dx[i];
                    int nextY = fireY + dy[i];

                    if(nextX < 0 || nextX >= R || nextY < 0 || nextY >= C) {
                        continue;
                    }

                    // 벽이 아니면서 불이 덮은 적 없어야함
                    if(visited[nextX][nextY] != 2 && map[nextX][nextY] != 1) {
                        visited[nextX][nextY] = 2;
                        nextNodes.add(new Node(nextX, nextY, 1));
                    }
                }

            }

            int canMoveCount = 0;
            // 이제 다음 불을 만날 때까지 계속 가능한 지훈이 위치를 꺼냄
            // 일단 다 찾고 그 다음에 넣어야함
            while(!queue.isEmpty() && queue.peek().type != 1) {
                Node jihoon = queue.poll();

                int jihoonX = jihoon.x;
                int jihoonY = jihoon.y;

                if(jihoonX== 0 || jihoonX == R-1 || jihoonY == 0 || jihoonY == C-1) {
                    flag = true;
                    return;
                }

                for(int i=0;i<4;i++) {
                    int nextX = jihoonX + dx[i];
                    int nextY = jihoonY + dy[i];

                    if(nextX < 0 || nextX >= R || nextY < 0 || nextY >= C) {
                        continue;
                    }

                    // 벽이 아니면서 불이 덮은 적 없어야하며 지훈이가 지나간 적 없어야함
                    if(visited[nextX][nextY] == 0 && map[nextX][nextY] != 1) {
                        visited[nextX][nextY] = 1;
                        canMoveCount++;
                        nextNodes.add(new Node(nextX, nextY, 0));
                    }
                }

            }

            if(canMoveCount == 0) {
                return;
            }

            for(Node node : nextNodes) {
                queue.add(node);
            }

        }
    }

    static class Node{
        int x;
        int y;
        int type;

        // type = 0 : 지훈이
        // type = 1 : 불
        Node(int x, int y, int type) {
            this.x = x;
            this.y = y;
            this.type = type;
        }
    }

}

