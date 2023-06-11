import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Login implements ActionListener {
        JFrame loginFrame;
        JLabel lab1 = new JLabel("Red Bus Application ",SwingConstants.CENTER);
        JLabel lab2 = new JLabel("Username : ");
        JLabel lab3 = new JLabel("Password : ");
        JTextField text1 = new JTextField("username");
        JPasswordField passwordField = new JPasswordField("password");
        JButton loginButton = new JButton("Login ");
        JButton regButton = new JButton("Register ");
        Login(){
            lab1.setBounds(0,0,300,50);
            lab1.setOpaque(true);
            lab1.setBackground(Color.decode("#BF4F65"));

            lab2.setBounds(50,100,150,50);
            text1.setBounds(150,115,100,25);

            lab3.setBounds(50,150,150,50);
            passwordField.setBounds(150,165,100,25);

            loginButton.setBounds(25,225,100,25);
            loginButton.addActionListener(this);
            regButton.setBounds(150,225,100,25);
            regButton.addActionListener(this);

            loginFrame = new JFrame();
            loginFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            loginFrame.setSize(300,400);
            loginFrame.setLocation(600,250);
            loginFrame.setLayout(null);
            loginFrame.setVisible(true);
            loginFrame.setBackground(Color.LIGHT_GRAY);

            loginFrame.add(lab1);
            loginFrame.add(lab2);
            loginFrame.add(text1);
            loginFrame.add(lab3);
            loginFrame.add(passwordField);
            loginFrame.add(loginButton);
            loginFrame.add(regButton);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==loginButton){
                IDPassword info = new IDPassword();
                String username = text1.getText();
                String password = String.valueOf(passwordField.getPassword());
                String gender = null;
                if(info.map.containsKey(username)){
                    if(info.map.containsValue(password)){
                        try{
                            String temp = username + ".txt";
                            File f = new File(temp);
                            FileReader fr =new FileReader(f);
                            BufferedReader br = new BufferedReader(fr);
                            for (int i = 0; i < 3; i++){
                                gender = br.readLine();
                            }
                            br.close();
                            fr.close();
                            loginFrame.dispose();
                        }catch (Exception ex){

                        }
                        User u1 = new User(username,password,gender);
                        Application a = new Application(u1);
                        //Open application
                        JOptionPane.showMessageDialog(null,"Login Completed");
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"Password Incorrect");
                        //password error
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null,"Username Incrrect");
                    //Username error message
                }
            }

            if(e.getSource()==regButton){
                Register reg = new Register();
            }
        }
    }

