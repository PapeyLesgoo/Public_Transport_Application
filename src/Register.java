import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Register implements ActionListener {
    JFrame regFrame;
    JLabel lab1 = new JLabel("Red Bus Application ",SwingConstants.CENTER);
    JLabel lab2 = new JLabel("Username : ");
    JLabel lab3 = new JLabel("Password : ");
    JLabel lab4 = new JLabel("Confirm : ");
    JLabel lab5 = new JLabel("Gender : ");
    JTextField text1 = new JTextField("username ");
    JTextField text2 = new JTextField("male");
    JPasswordField jPasswordField1 = new JPasswordField("password");
    JPasswordField jPasswordField2 = new JPasswordField("password");
    JButton regButton = new JButton("Register ");

    Register(){

        lab1.setBounds(0,0,400,50);
        lab1.setOpaque(true);
        lab1.setBackground(Color.decode("#BF4F65"));

        lab2.setBounds(50,100,150,50);
        text1.setBounds(150,115,150,25);

        lab3.setBounds(50,150,150,50);
        jPasswordField1.setBounds(150,165,150,25);

        lab4.setBounds(50,200,150,50);
        jPasswordField2.setBounds(150,215,150,25);

        lab5.setBounds(50,250,150,50);
        text2.setBounds(150,265,150,25);

        regButton.setBounds(150,300,100,25);
        regButton.addActionListener(this);

        regFrame = new JFrame();
        regFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        regFrame.setSize(400,500);
        regFrame.setLocation(500,250);
        regFrame.setLayout(null);
        regFrame.setVisible(true);
        regFrame.setBackground(Color.LIGHT_GRAY);

        regFrame.add(lab1);
        regFrame.add(lab2);
        regFrame.add(text1);
        regFrame.add(lab3);
        regFrame.add(jPasswordField1);
        regFrame.add(lab4);
        regFrame.add(jPasswordField2);
        regFrame.add(lab5);
        regFrame.add(text2);
        regFrame.add(regButton);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==regButton){
            IDPassword info = new IDPassword();
            String username = text1.getText();
            String gender = text2.getText();
            String pw1 = String.valueOf(jPasswordField1.getPassword());
            String pw2 = String.valueOf(jPasswordField2.getPassword());
            if(info.map.containsKey(username)){
                JOptionPane.showMessageDialog(null,"Username exists");
                //Username error message
            }
            else {
                if (pw1.equals(pw2)){
                    if (gender.equalsIgnoreCase("male")||gender.equalsIgnoreCase("female")){
                        info.Insert(username,pw1);
                        String temp = username.concat(".txt");
                        System.out.println(temp);
                        File user = new File(temp);
                        if (!user.exists()){
                            try {
                                user.createNewFile();
                                FileWriter fw = new FileWriter(user);
                                BufferedWriter bw = new BufferedWriter(fw);
                                bw.write(username);
                                bw.newLine();
                                bw.write(pw1);
                                bw.newLine();
                                bw.write(gender);
                                bw.close();
                                fw.close();
                            } catch (Exception ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                        System.out.println("Registration successful");
                        regFrame.dispose();
                    }else{
                        JOptionPane.showMessageDialog(null,"Unknown Gender");
                    }
                }else {
                    JOptionPane.showMessageDialog(null,"Password not same");
                    //password error message
                }
            }
        }
    }
}
