import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class Driver implements ActionListener {
    JFrame DriverFrame = new JFrame();
    JTextField text1 = new JTextField("Bus ID");
    JButton search = new JButton("Search");
    JTable table = new JTable();
    DefaultTableModel model = (DefaultTableModel)table.getModel();
    JScrollPane sp = new JScrollPane(table);
    boolean status = false;
    
    Driver(){

        text1.setBounds(100,25,300,25);
        search.setBounds(450,25,100,25);
        search.addActionListener(this);

        JTableHeader Theader = table.getTableHeader();
        Theader.setBackground(Color.BLACK);
        Theader.setForeground(Color.white);

        Object[] ColName = {"Year","Month","Date","Hour","Minutes","Seat Row","Seat Column","Username"};
        model.setColumnIdentifiers(ColName);

        table.setEnabled(false);
        table.setBounds(0,0,600,150);
        sp.setBounds(0,200,600,150);

        DriverFrame = new JFrame("Red Bus Application");
        DriverFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        DriverFrame.setSize(610,700);
        DriverFrame.setLocation(400,50);
        DriverFrame.setLayout(null);
        DriverFrame.setVisible(true);
        DriverFrame.setBackground(Color.RED);

        DriverFrame.add(text1);
        DriverFrame.add(search);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==search){
            String fileName = text1.getText() + ".txt";
            if (status){
                while (model.getRowCount()!=0){
                    model.removeRow(0);
                }
            }
            try {
                status = true;
                File f = new File(fileName);
                if (f.exists()){
                    FileReader fr = new FileReader(f);
                    BufferedReader br = new BufferedReader(fr);
                    for (int i = 0; i < 5; i++){
                        br.readLine();
                    }
                    String temp1;
                    while ((temp1 = br.readLine())!=null){
                        System.out.println(temp1);
                        String s[] = temp1.split(",");
                        model.addRow(s);
                    }
                    br.close();
                    fr.close();
                    DriverFrame.add(sp);
                }else{
                    JOptionPane.showMessageDialog(null,"Invalid Bus ID");
                }
            }catch (Exception ex){

            }
        }
    }
}
