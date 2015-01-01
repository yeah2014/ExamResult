import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

/**
 * Created by hongyeah on 2014/12/23.
 */
public class StudentList {
    public File file;
    public BufferedReader br;
    public ArrayList<String> arrs;

    public StudentList(File file)
    {
        this.file = file;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));
            arrs = new ArrayList<String>();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String s;
        try {
            while((s=br.readLine())!=null)
            {
                arrs.add(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getArrs()
    {
        return arrs;
    }


/**/
    public String filename;

    public void setfilename(String filename)
    {
        this.filename = file.getName().replace(".txt","-"+filename + ".dat");
    }

    public void savescorse(JFormattedTextField[] scores)
    {
        ObjectOutputStream output = null;
        try {
             output = new ObjectOutputStream(new FileOutputStream(filename));
            for(int i=0;i<scores.length ; i++)
            {
                Course clist= new Course(arrs.get(i),scores[i].getValue());
                output.writeObject(clist);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
