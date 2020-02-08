import org.sct.lock.util.BasicUtil;

public class test {
    public static void main(String[] args) {
        String line = ">+";
        int detect = 0;
        if (line.contains("<")) {
            System.out.println(1);
            detect++;
        } else if (line.contains(">")) {
            System.out.println(2);
            detect++;
        } else if (line.contains("=")) {
            System.out.println(3);
            detect++;
        }
        System.out.println(detect);
    }
}
