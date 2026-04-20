import java.io.*;

class Main {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        String s = "";

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) > 67) {
                s += String.valueOf((char) (str.charAt(i) - 3));
            } else {
                s += String.valueOf((char) (str.charAt(i) + 23));
            }
        }
        System.out.println(s);
    }
}