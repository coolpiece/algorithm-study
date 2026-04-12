import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		char list[] = input.toCharArray(); // char 배열로 바꾸기.
		char output[] = new char[input.length()];
		for (int i = 0; i < input.length(); i++)
			output[i] = (char) ('A' + (list[i] - 'A' + 23) % 26); // -3으로 계산하면 범위를 넘어갈 위험이 있으므로 +23, %26으로 계산.
		for (int i = 0; i < input.length(); i++)
			System.out.print(output[i]);
	}
}