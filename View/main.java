import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.text.DefaultFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.util.ArrayList;

/**
 * Created by hongyeah on 2014/12/20.
 */
public class main extends JFrame implements ActionListener, MouseListener {
    JPanel Back,Open,New;
    JButton Open_JBu,New_JBu;
    CardLayout C;

    public static void main(String S[])
    {
        new main();
    }

    public main()
    {
        C = new CardLayout();
        this.setLayout(C);
        InitBack();
        InitNew();
        InitOpen();
        InitAnalisyBing();
        InitAnalystTiao();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
        this.pack();
        this.setSize(600,510);
    }

    public void InitBack()
    {
        Back = new JPanel();
        Back.setLayout(new FlowLayout(FlowLayout.CENTER,100,100));
        Back.setBorder(BorderFactory.createTitledBorder(null, "学生成绩分析程序", TitledBorder.CENTER, TitledBorder.ABOVE_TOP, new Font("微软雅黑", Font.BOLD, 18)));
        Open_JBu = new JButton("打开课程考试成绩单");
        Open_JBu.addActionListener(this);
        New_JBu = new JButton("新建课程考试成绩单");
        New_JBu.addActionListener(this);
        Back.add(Open_JBu);
        Back.add(New_JBu);
        this.add(Back,"Back");

    }
    JButton newcannel;
    public void InitNew()
    {
        New = new JPanel(new BorderLayout());
        JPanel List ;
        JLabel[] jLabels ;
        JScrollPane jsp;
        CourseList c = new CourseList();
        ArrayList<String> courselist = c.getCourse();
        jLabels = new JLabel[courselist.size()];
        List = new JPanel(new GridLayout(courselist.size(),1,0,4));
        for(int i=0;i<courselist.size();i++)
        {
            jLabels[i]=new JLabel(courselist.get(i),JLabel.CENTER);
            jLabels[i].addMouseListener(this);
            List.add(jLabels[i]);
        }
        jsp = new JScrollPane(List);
        New.add(jsp,"Center");
        newcannel = new JButton("cancel");
        newcannel.addActionListener(this);
        New.add(newcannel,"South");
        this.add(New, "New");
    }


    Button analyst,edit,watch,save,cancel;
    JPanel up,down;
    public void InitOpen()
    {
        up = new JPanel(new BorderLayout());
        down = new JPanel(new FlowLayout(FlowLayout.CENTER,30,10));
        Open = new JPanel(new BorderLayout());
        analyst = new Button("Analyst");
        edit = new Button("Edit");
        watch = new Button("Watch");
        save = new Button("Save");
        cancel = new Button("Cancel");
        analyst.addActionListener(this);
        edit.addActionListener(this);
        watch.addActionListener(this);
        save.addActionListener(this);
        cancel.addActionListener(this);
        up.setSize(600, 300);
        down.setSize(600,100);
        down.add(edit);
        down.add(watch);
        down.add(analyst);
        down.add(save);
        down.add(cancel);
        Open.add(up,"Center");
        Open.add(down,"South");
        this.add(Open,"Open");
    }

    JScrollPane jspOpen;
    JPanel list ;
    JFormattedTextField[] scores;
    JLabel[] name;
    File file;
    ArrayList<Course> ScoreList = null;
    public void setOpen(File file)
    {
        ObjectInputStream intput;
        try {
             intput = new ObjectInputStream(new FileInputStream(file));
             ScoreList = new ArrayList<Course>();
            while(true)
            ScoreList.add((Course)intput.readObject());
        }catch (EOFException e){
            System.out.println("ALL data read.");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        if(ScoreList==null) return ;
        list = new JPanel(new GridLayout(ScoreList.size(),2,100,5));
        scores = new JFormattedTextField[ScoreList.size()];
        name = new JLabel[ScoreList.size()];
        for(int i = 0;i < ScoreList.size() ; i++)
        {
            name[i] = new JLabel(ScoreList.get(i).IDandname);
            scores[i] = newJFTF(ScoreList.get(i));
            scores[i].setEditable(false);
            list.add(name[i]);list.add(scores[i]);
        }
        if(jspOpen!=null)
        {
            up.remove(jspOpen);
        }
            jspOpen = new JScrollPane(list);
        up.add(jspOpen,"Center");
    }
    public JFormattedTextField newJFTF(Course course)
    {
        DefaultFormatter formatter = new DefaultFormatter();
        formatter.setOverwriteMode(true);
        JFormattedTextField Field = new JFormattedTextField(formatter);
        Field.setValue(course.getScore());
        Field.addMouseListener(this);
        return Field;
    }

    JPanel AnLBack,Anldown,anldowndown;
    DefaultPieDataset Bing;
    Integer lessthan60=0,between6069=0,between7079=0,between8089=0,morethan90=0;
    Integer lt60,bt6069,bt7079,bt8089,mt90;
    JLabel num1,num2,num3,num4,num5;
    JButton bingcancel,Tiaobutton;
    public void InitAnalisyBing()
    {
       AnLBack = new JPanel(new BorderLayout());
        Bing=new DefaultPieDataset(); //建立一个默认的饼图
        JFreeChart chart= ChartFactory.createPieChart("Student Score Statistics", Bing, true, true, false);
        ChartPanel chat = new ChartPanel(chart);
        num1 = new JLabel("OK  ");
        num2 = new JLabel("    OK   ");
        num3 = new JLabel("    OK     ");
        num4 = new JLabel("    OK     ");
        num5 = new JLabel("    OK     ");
        AnLBack.add(chat,"North");
        Anldown = new JPanel(new FlowLayout(FlowLayout.CENTER,10,0));
        Anldown.add(num1);
        Anldown.add(num2);
        Anldown.add(num3);
        Anldown.add(num4);
        Anldown.add(num5);
        AnLBack.add(Anldown,"Center");
        bingcancel = new JButton("Cancel");
        bingcancel.addActionListener(this);
        Tiaobutton = new JButton("Tiaoxing View");
        Tiaobutton.addActionListener(this);
        anldowndown = new JPanel(new FlowLayout(FlowLayout.CENTER));
        anldowndown.add(bingcancel);
        anldowndown.add(Tiaobutton);
        AnLBack.add(anldowndown,"South");
        this.add(AnLBack,"Analyst");
    }

    public void maths()
    {
        lessthan60=0;between6069=0;between7079=0;between8089=0;morethan90=0;
        if(ScoreList==null) return;
        for(int i=0;i<ScoreList.size();i++)
        {
            if(ScoreList.get(i).Score<60)
                lessthan60++;
            else if(ScoreList.get(i).Score>=60&&ScoreList.get(i).Score<70) between6069++;
            else if(ScoreList.get(i).Score>=70&&ScoreList.get(i).Score<80) between7079++;
            else if(ScoreList.get(i).Score>=80&&ScoreList.get(i).Score<90) between8089++;
            else morethan90++;
        }
        lt60=(int)Math.rint(lessthan60*100.0/ScoreList.size());
        bt6069=(int)Math.rint(between6069*100.0/ScoreList.size());
        bt7079=(int)Math.rint(between7079*100.0/ScoreList.size());
        bt8089=(int)Math.rint(between8089*100.0/ScoreList.size());
        mt90=(int)Math.rint(morethan90*100.0/ScoreList.size());
    }

    public void setdata()
    {
        Bing.setValue("<60", lt60);  //输入数据
        Bing.setValue("60-69", bt6069);
        Bing.setValue("70-79", bt7079);
        Bing.setValue("80-89", bt8089);
        Bing.setValue(">=90", mt90);
        num1.setText(lt60 + "%   ");
        num2.setText(bt6069 + "%     ");
        num3.setText(bt7079 + "%     ");
        num4.setText(bt8089 + "%     ");
        num5.setText(mt90 + "%  ");
    }

    JPanel ABack,Amin,Adown;
    JLabel student1,student2,student3,student4,student5;
    JButton Acancel,Abing;
    DefaultCategoryDataset Tiaodata;
    public void InitAnalystTiao()
    {
        Tiaodata = new DefaultCategoryDataset();
        JFreeChart chart=ChartFactory.createBarChart("Student Score Statistics", "分数分布",
                "Students population", Tiaodata, PlotOrientation.VERTICAL, true, true, false); //创建一个JFreeChart
        CategoryPlot plot=(CategoryPlot)chart.getPlot();//获得图标中间部分，即plot
        CategoryAxis categoryAxis=plot.getDomainAxis();//获得横坐标
        categoryAxis.setLabelFont(new Font("微软雅黑",Font.BOLD,12));//设置横坐标字体
        NumberAxis numberAxis = (NumberAxis)plot.getRangeAxis();
        numberAxis.setAutoTickUnitSelection(true);
        double len = 3;
        NumberTickUnit ntu = new NumberTickUnit(len);
        numberAxis.setTickUnit(ntu);
        ChartPanel jfreechart =new ChartPanel(chart);
        ABack = new JPanel(new BorderLayout());
        ABack.add(jfreechart,"North");
        student1 = new JLabel(" <60: 10");
        student2 = new JLabel("   60-69: 10");
        student3 = new JLabel("   70-79: 10");
        student4 = new JLabel("   80-89: 10");
        student5 = new JLabel("   >=90: 10");
        Amin = new JPanel(new FlowLayout(FlowLayout.CENTER));
        Amin.add(student1);
        Amin.add(student2);
        Amin.add(student3);
        Amin.add(student4);
        Amin.add(student5);
        Adown = new JPanel(new FlowLayout(FlowLayout.CENTER));
        Abing = new JButton("Bing View");
        Acancel = new JButton("cancel");
        Abing.addActionListener(this);
        Acancel.addActionListener(this);
        Adown.add(Abing);
        Adown.add(Acancel);
        ABack.add(Amin,"Center");
        ABack.add(Adown,"South");
        this.add(ABack,"Tiao");
    }

    public void setTiaodata()
    {
        Tiaodata.setValue(lessthan60,"<60","<60");
        Tiaodata.setValue(between6069,"60-69","60-69");
        Tiaodata.setValue(between7079,"70-79","70-79");
        Tiaodata.setValue(between8089,"80-89","80-89");
        Tiaodata.setValue(morethan90,">=90",">=90");
        student1.setText(" <60: "+lessthan60);
        student2.setText("   60-69: "+between6069);
        student3.setText("   70-79: "+between7079);
        student4.setText("   80-89: "+between8089);
        student5.setText("   >=90: "+morethan90);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == New_JBu)
        {
            C.show(this.getContentPane(),"New");
        }
        else if(e.getSource() == Open_JBu)
        {
            JFileChooser choose = new JFileChooser();
            choose.setFileSelectionMode(JFileChooser.FILES_ONLY);
            choose.setCurrentDirectory(new File("."));
            choose.showDialog(new JLabel(), "Choose");
            file = choose.getSelectedFile();
            if(file.isFile()) {
                setOpen(file);
                C.show(this.getContentPane(), "Open");
            }
            else C.show(this.getContentPane(),"Back");
        }
        else if(e.getSource() == edit)
            for(int i=0;i<scores.length;i++)
                scores[i].setEditable(true);
        else if(e.getSource() == watch)
            for(int i=0;i<scores.length;i++)
                scores[i].setEditable(false);
        else if(e.getSource() == save)
        {
            ObjectOutputStream output = null;
            try {
                output = new ObjectOutputStream(new FileOutputStream(file.getName()));
                for(int i=0;i<scores.length ; i++)
                {
                   ScoreList.get(i).Score=(Double)scores[i].getValue();
                    output.writeObject(ScoreList.get(i));
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }finally {
                if (output != null) {
                    try {
                        output.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        }
        else if(e.getSource() == cancel||e.getSource() == newcannel)
            C.show(this.getContentPane(),"Back");
        else if(e.getSource() == analyst)
        {
            maths();
            setdata();
            C.show(this.getContentPane(), "Analyst");
            System.out.println("OK");
        }
        else if(e.getSource() == bingcancel) C.show(this.getContentPane(),"Open");
        else if(e.getSource() == Tiaobutton)
        {
            setTiaodata();
            C.show(this.getContentPane(),"Tiao");
        }
        else if(e.getSource() == Abing) C.show(this.getContentPane(),"Analyst");
        else if(Acancel == e.getSource()) C.show(this.getContentPane(),"Open");
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getClickCount()==1&&(e.getSource() instanceof JLabel))
        {
            JFileChooser choose = new JFileChooser();
            choose.setFileSelectionMode(JFileChooser.FILES_ONLY);
            choose.setCurrentDirectory(new File("."));
            choose.showDialog(new JLabel(), "Choose");
            File file = choose.getSelectedFile();
            StudentList sl = new StudentList(file);
            JLabel classname =(JLabel)e.getSource();
            sl.setfilename(classname.getText());
            if(FileOperator.isFile(sl.filename))
                {
                    JOptionPane.showMessageDialog(null,"己存在");
                }
            else new InputData(sl);
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if(e.getSource() instanceof JLabel)
        {
            JLabel enter = (JLabel)e.getSource();
            enter.setForeground(Color.blue);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(e.getSource() instanceof JLabel) {
            JLabel exit = (JLabel) e.getSource();
            exit.setForeground(Color.black);
        }
    }
}
