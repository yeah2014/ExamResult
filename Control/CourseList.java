import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by hongyeah on 2014/12/23.
 */
public class CourseList {

    BufferedReader br;
    ArrayList<String> arrs =new ArrayList<String>();
    public CourseList(){
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream("Course.txt"),"GBK"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        String s;
        try {
            while(null != (s=br.readLine()))
            {
                arrs.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<String> getCourse()
    {
        return arrs;
    }



}
