public class Test {

    public static int last2(String str) {
        int answer = 0;

        String match = str.substring(str.length() - 2);

        for (int i = 0; i < str.length() - 2; i++) {
            if (str.substring(i, i + 2).equals(match)) {
                answer++;
            }
        }

        return answer;
    }

    public static void main(String[] args) {

        String copy = "xabcxdcx";
  
  for (int i = 1; i < copy.length() - 1; i++) {
    if (copy.substring(i, i + 1).equals("x")) {
        copy = copy.substring(0, i) + copy.substring(i + 1);
    }
  }
        System.out.println(copy);
    }
}
