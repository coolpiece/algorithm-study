package week2.p21939;

import java.io.*;
import java.util.*;

public class 김상균_21939 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // <난이도, 해당 난이도에 해당하는 문제들> => 기본적으로 오름차순
        TreeMap<Integer, TreeSet<Integer>> difficulties = new TreeMap<>();

        // 각 문제 번호가 어떤 난이도에 있는지를 체크해야함
        // <문제 번호, 해당 문제의 난이도들> => 삭제용
        Map<Integer, List<Integer>> problems = new HashMap<>();

        int N = Integer.parseInt(br.readLine());

        // 문제 번호와 난이도들
        for(int i=0;i<N;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int P = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());

            // 난이도에 해당하는 문제들 관리
            TreeSet<Integer> set = difficulties.getOrDefault(L, new TreeSet<>());
            set.add(P);
            difficulties.put(L, set);


            // 문제가 속한 난이도들
            List<Integer> list = problems.getOrDefault(P, new ArrayList<>());
            list.add(L);
            problems.put(P, list);
        }

        int M = Integer.parseInt(br.readLine());

        for(int i=0;i<M;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            String inst = st.nextToken();

            if(inst.equals("recommend")) {
                int x = Integer.parseInt(st.nextToken());

                if(x == 1) {
                    // 가장 높은 난이도
                    TreeSet<Integer> hardestProblems = difficulties.get(difficulties.lastKey());
                    sb.append(hardestProblems.last()).append("\n");
                } else {
                    TreeSet<Integer> easiestProblems = difficulties.get(difficulties.firstKey());
                    sb.append(easiestProblems.first()).append("\n");
                }

            } else if(inst.equals("add")) {
                int P = Integer.parseInt(st.nextToken());
                int L = Integer.parseInt(st.nextToken());

                TreeSet<Integer> set = difficulties.getOrDefault(L, new TreeSet<>());
                set.add(P);
                difficulties.put(L, set);

                List<Integer> list = problems.getOrDefault(P, new ArrayList<>());
                list.add(L);
                problems.put(P, list);
            } else { // solved
                int P = Integer.parseInt(st.nextToken());

                List<Integer> list = problems.get(P);

                for(int difficulty : list) {
                    difficulties.get(difficulty).remove(P);

                    if(difficulties.get(difficulty).isEmpty()) {
                        difficulties.remove(difficulty);
                    }
                }

                problems.remove(P);
            }
        }
        System.out.println(sb);


    }

}

