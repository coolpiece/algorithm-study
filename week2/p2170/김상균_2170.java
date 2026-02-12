package week2.p2170;

import java.io.*;
import java.util.*;

public class 김상균_2170 {

    // 1. 시작점이 작은 순
    // 2. 시작점이 동일하다면 종료점이 작은 순
    // 즉 작고 짧은 것부터 정렬
    // 인덱스를 뺀 만큼이 길이임. 5 - 4 = 1
    // 이전 거의 마지막을 기억 -> 그 다음 거의 시작 지점이 이전 거의 마지막보다 작으면 겹치는 길이만큼 길이 빼주기

    // 새로운 데이터의 시작점 > 기존 데이터의 끝점 == 연결되지 않음. 즉 다시 줄의 길이 계산
    // 기존 줄의 길이를 결과에 더해야함

    // 연결이 끊기면 시작점도 바꾸기
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        List<Node> list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            list.add(new Node(start, end));
        }

        Collections.sort(list);

        Node firstNode = list.get(0);
        int lastStart = firstNode.start;
        int lastEnd = firstNode.end;
        int result = 0;

        for (int i = 1; i < N; i++) {
            Node currentNode = list.get(i);
            int start = currentNode.start;
            int end = currentNode.end;


            if (start > lastEnd) { // 새로운 줄이면 => ----- *****
                result += lastEnd - lastStart;

                lastStart = start;
                lastEnd = end;
            } else { // 겹치면 => ----*-(*)**** 이런식으로 겹치면
                lastEnd = Math.max(end, lastEnd);
            }

        }

        result += lastEnd - lastStart;

        System.out.println(result);
    }

    static class Node implements Comparable<Node> {
        int start;
        int end;

        Node(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Node o) {
            if (this.start == o.start) {
                return this.end - o.end;
            }

            return this.start - o.start;
        }
    }

}

