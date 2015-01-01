import java.io.File;


/**
 * Created by hongyeah on 2014/12/25.
 */
public class FileOperator {

    public FileOperator()
    {

    }

    public static boolean isFile(String filename)
    {
        File file = new File(filename);
        if(file.isFile()) return true;
        else return false;
    }


}
