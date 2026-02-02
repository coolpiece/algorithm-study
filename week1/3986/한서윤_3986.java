import java.io.*;
import java.util.*;

public class Main {
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine()), cnt = 0;
		for (int i = 0; i < n; i++) {
			String sb = new String(br.readLine());
			if (sb.length() % 2 != 0)
				continue;
			Stack<Character> s = new Stack<>();
			for (int j = 0; j < sb.length(); j++) {
				char ch = sb.charAt(j);
				if (!s.isEmpty() && s.peek() == ch)
					s.pop();
				else s.add(ch);
			}
			if (s.isEmpty()) cnt++;
		}
		System.out.println(cnt);
	}
}