import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class BusSeats {
    JLabel[][] seats = new JLabel[4][20];
    String[][] seatKeep = new String[4][20];
    String tempFile = "Bus";
    int StartHour, StartMin;
    PriQueue q1 = new PriQueue();
    BusSeats(){
        int k = 300,l = 0;
        for (int i = 0; i < 4; i++){
            l = 0;
            for (int j = 0; j < 20; j++){
                seatKeep[i][j] = "0";
                seats[i][j] = new JLabel();
                seats[i][j].setText((i) + "" + (j));
                seats[i][j].setBounds(l,k,25,25);
                seats[i][j].setOpaque(true);
                l += 30;
            }
            k += 30;
        }
    }

    public void InitSeats(int RouteNum, int BusNum,String Gender){
        try{
            tempFile = "Bus";
            tempFile += RouteNum + "" + BusNum + ".txt";
            System.out.println(tempFile);
            File f = new File(tempFile);
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            for (int i = 0; i < 4; i++){
                String s = br.readLine();
                for (int j = 0; j < s.length();j++){
                    if (s.charAt(j)=='0'){
                        seatKeep[i][j] = "0";
                    }else{
                        seatKeep[i][j] = "1";
                    }
                    if (Gender.equalsIgnoreCase("male")){
                        if (j>=10){
                            if (s.charAt(j)=='0'){
                                seats[i][j].setBackground(Color.red);
                            }else{
                                System.out.println("Black" + i + " " + j);
                                seats[i][j].setBackground(Color.BLACK);
                            }
                        }else{
                            seats[i][j].setBackground(Color.black);
                        }
                    }else{
                        if (j<10){
                            if (s.charAt(j)=='0'){
                                seats[i][j].setBackground(Color.red);
                            }else{
                                seats[i][j].setBackground(Color.BLACK);
                            }
                        }else{
                            seats[i][j].setBackground(Color.black);
                        }
                    }
                }
            }
            br.readLine();
            br.close();
            fr.close();
        }catch (Exception e){

        }
    }

    public void InitServer(int route, int num){
        tempFile = "Bus";
        tempFile += route + "" + num + ".txt";
        try {
            File f = new File(tempFile);
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            for (int i = 0; i < 4; i++){
                String temp = br.readLine();
                for (int j = 0; j < 20; j++){
                    if (temp.charAt(j)=='0'){
                        seatKeep[i][j] = "0";
                    }else {
                        seatKeep[i][j] = "1";
                    }
                }
            }
            br.readLine();
            String line;
            while ((line = br.readLine())!=null){
                //System.out.println(line);
                String[] s = line.split(",");
                MyTime t = new MyTime();
                t.year = Integer.parseInt(s[0]);
                t.month = Integer.parseInt(s[1]);
                t.date = Integer.parseInt(s[2]);
                t.hour = Integer.parseInt(s[3]);
                t.minute = Integer.parseInt(s[4]);
                int r = Integer.parseInt(s[5]);
                int c = Integer.parseInt(s[6]);
                q1.Insert(t,r,c,0,0,s[7]);
            }
            br.close();
            fr.close();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            MyTime t = new MyTime();
            t.year = now.getYear();
            t.month = now.getMonthValue();
            t.date = now.getDayOfMonth();
            t.hour = now.getHour();
            t.minute = now.getMinute();
            q1.Remove(t,seatKeep);
            FileWriter fw = new FileWriter(tempFile);
            BufferedWriter bw = new BufferedWriter(fw);
            for (int i = 0; i < 4; i++){
                String temp = "";
                for (int j = 0; j < 20;j++){
                    temp+= seatKeep[i][j];
                }
                bw.write(temp);
                bw.newLine();
            }
            bw.write("-");
            bw.newLine();
            for (int i = 0; i <q1.size;i++){
                String s = q1.toString(i);
                System.out.println(s);
                bw.write(s);
                bw.newLine();
            }
            bw.close();
            fw.close();
        }catch (Exception ex){
            //System.out.println("Error" + route + " " + num);
        }
    }

    public boolean Booking(int RouteNum,int BusNum, MyTime t, int i, int j, User u, String stopName, String destName){
        tempFile = "Bus";
        tempFile += RouteNum + "" + BusNum + ".txt";
        if (!ValidSeat(i,j,u.gender)){
            JOptionPane.showMessageDialog(null,"Invalid Seat");
            return false;
        }
        seatKeep[i][j] = "1";
        try {
            ArrayList<String> s = new ArrayList<>();
            File f = new File(tempFile);
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line=br.readLine())!=null){
                s.add(line);
            }
            br.close();
            fr.close();
            FileWriter fw = new FileWriter(tempFile);
            BufferedWriter bw = new BufferedWriter(fw);
            for (int k = 0; k < s.size();k++){
                if (k==i){
                    System.out.println("B");
                    String temp = "";
                    for (int l = 0 ; l < 20;l++){
                        temp += seatKeep[i][l];
                    }
                    System.out.println(temp);
                    bw.write(temp);
                }else {
                    System.out.println("C");
                    bw.write(s.get(k));
                }
                bw.newLine();
            }
            String s3 = t.year + "," + t.month + "," + t.date + "," + t.hour + "," + t.minute + "," + i + "," + j + "," + u.username;
            bw.write(s3);
            bw.close();
            fw.close();
            String userTemp = u.username + ".txt";
            System.out.println(userTemp);
            File userFile = new File(userTemp);
            FileWriter Userfw = new FileWriter(userFile,true);
            BufferedWriter Userbw = new BufferedWriter(Userfw);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            String temp = dtf.format(now);
            temp += "," + RouteNum + "," + BusNum + "," + stopName + "," + destName;
            Userbw.newLine();
            Userbw.write(temp);
            Userbw.close();
            Userfw.close();
            return true;
        }catch (Exception ex){
        }
        return false;
    }

    public boolean ValidSeat(int i, int j, String gender){
        if (i >= 4 || i < 0){
            return false;
        }
        if (j >= 20 || j<0){
            return false;
        }
        if ((gender.equalsIgnoreCase("male")&&j<10)||(gender.equalsIgnoreCase("female")&&j>10)){
            return false;
        }
        if (seatKeep[i][j]=="1"){
            return false;
        }
        return true;
    }
}