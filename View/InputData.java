import javax.swing.*;
import javax.swing.text.DefaultFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Created by hongyeah on 2014/12/23.
 */
public class InputData extends JFrame implements ActionListener {

    public JPanel back,list,buttun;
    public Button save,cancel;
    public JScrollPane jsp;
    public JFormattedTextField[] scores;
    public JLabel[] name;
    public ArrayList<String> arrs;
    public StudentList sl;
    public InputData(StudentList SL)
    {
        this.sl=SL;
        this.arrs = SL.getArrs();
        list = new JPanel(new GridLayout(arrs.size(),2,100,5));
        back = new JPanel(new BorderLayout(100,10));
        scores = new JFormattedTextField[arrs.size()];
        name = new JLabel[arrs.size()];
        for(int i = 0;i<arrs.size();i++)
        {
            name[i]=new JLabel(arrs.get(i),JLabel.CENTER);
            scores[i]=newJFTF();
            list.add(name[i]);
            list.add(scores[i]);
        }
        jsp = new JScrollPane(list);
        back.add(jsp,"Center");
        save = new Button("save");
        save.addActionListener(this);
        cancel = new Button("cancel");
        cancel.addActionListener(this);
        buttun = new JPanel(new FlowLayout(FlowLayout.RIGHT,10,5));
        buttun.add(save);
        buttun.add(cancel);
        back.add(buttun,"South");
        this.add(back);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
        this.pack();
        this.setSize(600,400);
    }

    public JFormattedTextField newJFTF()
    {
        DefaultFormatter formatter = new DefaultFormatter();
        formatter.setOverwriteMode(true);
        JFormattedTextField Field = new JFormattedTextField(formatter);
        Field.setValue(new Double(0));
        return Field;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==save)
        {
            sl.savescorse(scores);
            JOptionPane.showMessageDialog(null,"录入成功，修改请到修改页面");
            this.dispose();
        }
        else if(e.getSource()==cancel)
        {
            this.dispose();
        }
    }



}
