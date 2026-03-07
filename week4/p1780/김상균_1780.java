package week4.p1780;

import java.io.*;
import java.util.*;

public class 김상균_1780 {

    static int N;
    static int[][] map;
    static boolean[][] visited;

    static int[] count = {0, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        visited = new boolean[N][N];

        for(int i=0;i<N;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            for(int j=0;j<N;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        check(map[0][0], 0, 0, N);

        StringBuilder sb = new StringBuilder(100);

        sb.append(count[0]).append("\n").append(count[1]).append("\n").append(count[2]);

        System.out.println(sb);

    }

    // num == 현재 종이에 적힌 숫자 -> 각 종이의 좌측상단 숫자를 넘겨주면 됨
    // x, y == 현재 종이의 시작 위치
    // size == 현재 종이의 사이즈
    static void check(int num, int x, int y, int size) {

        if(size == 1) {
            count[num+1]++;
            return;
        }

        if(allSame(num, x, y, size)) {
            count[num + 1]++;
            return;
        }

        int nextSize = size / 3;

        for(int i=x;i <= x + nextSize * 2;i += nextSize) {
            for(int j=y;j<=y + nextSize * 2;j += nextSize) {
                check(map[i][j], i, j, nextSize);
            }
        }

    }


    // 종이가 전부 동일하면
    static boolean allSame(int num, int x, int y, int size) {
        for(int i = x;i<x + size;i++) {
            for(int j=y;j<y + size;j++) {
                if(map[i][j] != num) {
                    return false;
                }
            }
        }

        return true;
    }

}

