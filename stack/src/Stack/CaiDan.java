package Stack;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Stack;

public class CaiDan {
    public static void readTxtFile(String filePath) {
        try {
            String encoding = "UTF-8";
            File file = new File(filePath);
            Stack<Integer> stack = new Stack<>();
            Long a = 0L;
            Integer i = 1;
            if (file.isFile() && file.exists()) { //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file), encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    String[] s = lineTxt.split(" ");
                    if (s.length == 1) {
                        a += stack.pop() * i;
                        i++;
                    } else {
                        stack.push(Integer.valueOf(s[1]));
                    }
                }
                System.out.println(a);
                read.close();
            } else {
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }

    }

    public static void main(String argv[]) {
        String filePath = "E:\\11.txt";
        readTxtFile(filePath);
    }
}