package week5.p2589;

import java.io.*;
import java.util.*;

public class 김상균_2589 {

    static int H, W;
    static int[][] map;
    static boolean[][] visited;
    static int result;

    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, 1, -1};

    // 어차피 각 지점에서 bfs 때리면, 현재 좌표엣 이동 가능한 다른 모든 좌표들에 도달하는 최단 거리를 계산할 수 있음
    public static void main(String[] args) throws IOException{
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        map = new int[H][W];
        visited = new boolean[H][W];
        result = 0;

        for(int i=0;i<H;i++) {
            String input = br.readLine();
            for(int j=0;j<W;j++) {
                char c = input.charAt(j);

                if(c == 'L') {
                    map[i][j] = 1;
                }
            }
        }

        for(int i=0;i<H;i++) {
            for(int j=0;j<W;j++) {
                if(map[i][j] == 1) {
                    bfs(i, j);
                }
            }
        }

        System.out.println(result);



    }

    static void bfs(int x, int y) {
        Queue<Node> queue = new ArrayDeque<>();
        for(int i=0;i<H;i++) {
            Arrays.fill(visited[i], false);
        }

        queue.add(new Node(x, y, 0));
        visited[x][y] = true;

        while(!queue.isEmpty()) {
            Node node = queue.poll();
            int currentX = node.x;
            int currentY = node.y;
            int currentLength = node.length;

            result = Math.max(currentLength, result);

            for(int i=0;i<4;i++) {
                int nextX = currentX + dr[i];
                int nextY = currentY + dc[i];

                if(nextX < 0 || nextX >= H || nextY < 0 || nextY >= W) {
                    continue;
                }

                if(map[nextX][nextY] == 1 && !visited[nextX][nextY]) {
                    visited[nextX][nextY] = true;
                    queue.add(new Node(nextX, nextY, currentLength + 1));
                }
            }
        }
    }

    static class Node {
        int x;
        int y;
        int length;

        Node(int x, int y, int length) {
            this.x = x;
            this.y = y;
            this.length = length;
        }
    }
}

