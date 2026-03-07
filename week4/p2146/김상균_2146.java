package week4.p2146;

import java.io.*;
import java.util.*;

public class 김상균_2146 {

    static int[][] map, grounds;
    static boolean[][] visited;
    static int N;

    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, 1, -1};

    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        grounds = new int[N][N];
        visited = new boolean[N][N];

        result = Integer.MAX_VALUE;

        for(int i=0;i<N;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for(int j=0;j<N;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 각각의 육지마다 번호 매기기
        int groundNum = 1;
        for(int i=0;i<N;i++) {
            for(int j=0;j<N;j++) {
                if(map[i][j] == 1 && !visited[i][j]) {
                    grounds[i][j] = groundNum;
                    visited[i][j] = true;
                    fillGround(groundNum, i, j);
                    ++groundNum;
                }
            }
        }

        // 다리 건설 찾기 => bfs
        for(int i=0;i<N;i++) {
            for(int j=0;j<N;j++) {
                if(map[i][j] == 1 && isCoast(i, j)) { // 육지이면서 바다와 접해있다면
                    findMinBridge(i, j, grounds[i][j]);
                }
            }
        }

        System.out.println(result);
    }

    static void fillGround(int groundNum, int x, int y) {

        for(int i=0;i<4;i++) {
            int nextX = x + dr[i];
            int nextY = y + dc[i];

            if(nextX < 0 || nextX >= N || nextY < 0 || nextY >= N) {
                continue;
            }

            if(map[nextX][nextY] == 1 && !visited[nextX][nextY]) {
                grounds[nextX][nextY] = groundNum;
                visited[nextX][nextY] = true;
                fillGround(groundNum, nextX, nextY);
            }
        }
    }

    // 바다와 한 면이라도 맞닿아있는지
    static boolean isCoast(int x, int y) {
        for(int i=0;i<4;i++) {
            int nextX = x + dr[i];
            int nextY = y + dc[i];

            if(nextX < 0 || nextX >= N || nextY < 0 || nextY >= N) {
                continue;
            }

            // 한 면이라도 바다라면
            if(map[nextX][nextY] == 0) {
                return true;
            }
        }

        return false;
    }

    static void findMinBridge(int x, int y, int groundNum) {
        Queue<Node> queue = new ArrayDeque<>();

        boolean[][] tempVisited = new boolean[N][N];
        queue.add(new Node(x, y, groundNum, 0));

        while(!queue.isEmpty()) {
            Node node = queue.poll();
            int currentX = node.x;
            int currentY = node.y;
            int currentGroundNum = node.groundNum;
            int currentLength = node.length;

            for(int i=0;i<4;i++) {
                int nextX = currentX + dr[i];
                int nextY = currentY + dc[i];

                if(nextX < 0 || nextX >= N || nextY < 0 || nextY >= N) {
                    continue;
                }

                int nextGroundNum = grounds[nextX][nextY];

                // 방문한 적이 없다면
                if(!tempVisited[nextX][nextY]) {

                    // 바다라면
                    if(map[nextX][nextY] == 0) {
                        tempVisited[nextX][nextY] = true;
                        queue.add(new Node(nextX, nextY, groundNum, currentLength + 1));
                        continue;
                    }

                    // 나랑 같은 육지라면
                    if(nextGroundNum == currentGroundNum) {
                        continue;
                    }

                    // 나랑 다른 육지라면
                    if(nextGroundNum != currentGroundNum) {
                        result = Math.min(currentLength, result);
                        return;
                    }

                }
            }
        }
    }

    static class Node {
        int x;
        int y;
        int groundNum; // 육지 번호
        int length;

        Node(int x, int y, int groundNum, int length) {
            this.x = x;
            this.y = y;
            this.groundNum = groundNum;
            this.length = length;
        }
    }
}

