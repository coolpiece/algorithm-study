import java.io.*;
import java.util.*;

public class Main {

    // 각각의 위치
    static int[] positions = new int[7];
    static int result = 0;
    static char[][] map = new char[5][5];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 5; i++) {
            String line = br.readLine();

            for (int j = 0; j < 5; j++) {
                map[i][j] = line.charAt(j);
            }
        }

        combination(0, 0);

        System.out.println(result);

    }

    static void combination(int currentIndex, int size) {

        if (size == 7) {
            if (isPossible()) {
                result++;
            }

            return;
        }

        for (int i = currentIndex; i < 25; i++) {
            positions[size] = i;

            combination(i + 1, size + 1);
        }
    }

    static boolean isPossible() {
        Queue<Integer> queue = new ArrayDeque<>();

        boolean[] visited = new boolean[7];

        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        queue.add(positions[0]);
        visited[0] = true;

        // 현재 연결된 수
        int connectCount = 1;

        int sCount = 0;

        if (map[positions[0] / 5][positions[0] % 5] == 'S') {
            sCount++;
        }

        while (!queue.isEmpty()) {
            int currentPos = queue.poll();
            int x = currentPos / 5;
            int y = currentPos % 5;

            for (int i = 0; i < 4; i++) {
                int nextX = x + dx[i];
                int nextY = y + dy[i];

                if (nextX >= 0 && nextX < 5 && nextY >= 0 && nextY < 5) {
                    int nextPos = nextX * 5 + nextY;

                    for (int j = 0; j < 7; j++) {
                        if (nextPos == positions[j] && !visited[j]) {
                            connectCount++;
                            visited[j] = true;

                            if (map[nextX][nextY] == 'S') {
                                sCount++;
                            }

                            if (connectCount == 7) {
                                if (sCount >= 4) {
                                    return true;
                                }
                            }

                            queue.offer(nextPos);
                        }
                    }
                }

            }
        }

        return false;
    }
}
