import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Application implements ActionListener {
    Travelling t = new Travelling();
    JFrame appFrame;
    ImageIcon img = new ImageIcon("Red bus pic 2.jfif");
    JLabel picLab = new JLabel(img);
    JLabel resLab = new JLabel();
    JPanel picPan = new JPanel();
    JPanel menuPan = new JPanel();
    JPanel ridePan = new JPanel();
    JButton startRide = new JButton("Start Ride ");
    JButton search = new JButton("Search ");
    JButton viewRoutes = new JButton("View Routes ");
    JButton bookRides = new JButton("Confirm Payment");
    JButton back = new JButton("Back");
    JButton showHistory = new JButton("Show History");
    JTextField text1 = new JTextField("Enter Starting Spot");
    JTextField text2 = new JTextField("Enter Destination");
    JTextField text3 = new JTextField("Search");
    JTextField text6 = new JTextField("Seat Number");
    JTable table;
    JTable histTab = new JTable();
    DefaultTableModel model = (DefaultTableModel)histTab.getModel();
    JScrollPane sp;
    JScrollPane hsp = new JScrollPane(histTab);
    Travelling temp = new Travelling();
    BusSeats[][] buses = new BusSeats[6][10];
    String[][] tripInfo = new String[2][10];
    User u;
    BookingDetails b1;
    boolean flag1 = false;
    boolean flag2 = false;
    boolean rideStatus = false;
    boolean hist = false;
    int seatRow, seatCol;

    Application(User u1){
        u = u1;
        picLab.setBounds(0,0,600,200);
        picLab.setBackground(Color.red);

        picPan.setBounds(0,0,600,200);
        picPan.setForeground(Color.red);
        picPan.setBackground(Color.red);
        picPan.setLayout(null);
        picPan.add(picLab);

        search.setBounds(200,0,100,25);
        search.addActionListener(this);
        text3.setBounds(200,0,100,25);

        viewRoutes.setBounds(200,200,100,100);
        viewRoutes.addActionListener(this);

        showHistory.setBounds(500,200,150,100);
        showHistory.addActionListener(this);

        resLab.setBounds(0,200,600,75);

        text1.setBounds(125,500,400,25);
        text2.setBounds(125,550,400,25);

        startRide.setBounds(250,600,100,50);
        startRide.addActionListener(this);


        bookRides.setBounds(300,500,150,50);
        bookRides.addActionListener(this);
        back.setBounds(125,500,100,50);
        back.addActionListener(this);
        text6.setBounds(125,450,400,25);

        menuPan.setBounds(0,200,600,100);
        menuPan.add(viewRoutes);
        menuPan.add(text3);
        menuPan.add(search);
        menuPan.add(showHistory);

        ridePan.setBounds(0,400,600,200);
        String[][] data = new String[6][11];

        for (int i = 0; i < 6; i++){
            int k = 7, l = 0;
            for (int j = 1; j < 11;j++){
                data[i][j] = t.routes[i][j-1];
                buses[i][j-1] = new BusSeats();
                buses[i][j-1].StartHour = k;
                buses[i][j-1].StartMin = l;
                l += 6;
            }
        }
        for (int k = 0; k < 6;k++){
            data[k][0] = ("Route " + (k+1));
        }

        String[] column = {" ","Stop 1", "Stop 2", "Stop 3", "Stop 4", "Stop 5", "Stop 6", "Stop 7","Stop 8", "Stop 9", "Stop 10"};

        table = new JTable(data,column){
            private static final long serialVersionUID = 1L;

            public boolean isCellEditable(int row, int column) {
                return false;
            };
        };
        table.setBounds(0,0,600,150);

        sp = new JScrollPane(table);
        sp.setBounds(0,325,600,150);

        JTableHeader Theader = histTab.getTableHeader();
        Theader.setBackground(Color.red);
        Theader.setForeground(Color.white);

        Object[] ColName = {"Time","Route","Bus","Stop Name","Destination Name"};
        model.setColumnIdentifiers(ColName);

        try {
            String temp = u1.username + ".txt";
            File f = new File(temp);
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            for (int i = 0; i < 3; i++){
                br.readLine();
            }
            String line;
            while ((line = br.readLine())!=null){
                String dataRow[] = line.split(",");
                model.addRow(dataRow);
            }
            br.close();
            fr.close();
        }catch (Exception e){

        }
        table.setEnabled(false);
        histTab.setBounds(0,0,600,150);
        histTab.setEnabled(false);
        hsp.setBounds(0,325,600,150);

        appFrame = new JFrame("Red Bus Application");
        appFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        appFrame.setSize(610,700);
        appFrame.setLocation(400,50);
        appFrame.setLayout(null);
        appFrame.setVisible(true);
        appFrame.setBackground(Color.LIGHT_GRAY);

        appFrame.add(resLab);
        appFrame.add(picPan);
        appFrame.add(menuPan);
        appFrame.add(text1);
        appFrame.add(text2);
        appFrame.add(startRide);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==search){
            String stop = text3.getText();
            if (temp.search_stop(t.stops,stop)){
                resLab.setText("Stop is Available");
            }else{
                resLab.setText("Stop is not Available");
            }
        }

        if (e.getSource()==viewRoutes){
            System.out.println("A");
            if (hist==true){
                appFrame.remove(hsp);
                hist = false;
            }
            if (flag1==false){
                System.out.println("B");
                appFrame.add(sp);
                flag1 = true;
                return;
            }else{
                System.out.println("C");
                appFrame.remove(sp);
                flag1 = false;
                return;
            }
        }

        if (e.getSource()==showHistory){
            if (flag1 == true){
                appFrame.remove(sp);
                flag1 =  false;
            }
            if (hist == false){
                appFrame.add(hsp);
                hist = true;
                return;
            }
            if (hist == true){
                appFrame.remove(hsp);
                hist = false;
                return;
            }
        }

        if(e.getSource()==startRide){
            String starting = text1.getText();
            String dest = text2.getText();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            MyTime time = new MyTime();
            time.year = now.getYear();
            time.month = now.getMonthValue();
            time.date = now.getDayOfMonth();
            time.hour = now.getHour();
            time.minute = now.getMinute();
            if (now.getHour()>7&&now.getHour()<22){
                int h = now.getHour(), m = now.getMinute();
                t.start_trip(t.routes,t.stops,t.route1,t.route2,t.route3,t.route4,t.route5,t.route6,tripInfo,starting,dest);
                System.out.println(tripInfo[1][1]);
                if (tripInfo[0][0]==null && tripInfo[1][0]==null){
                    JOptionPane.showMessageDialog(null,"Can't find Route");
                    return;
                }
                if (tripInfo[1][0]==null){
                    int routeNum = Integer.parseInt(tripInfo[0][0])-1;
                    int startStop = Integer.parseInt(tripInfo[0][1]);
                    int destStop = Integer.parseInt(tripInfo[0][3]);
                    int busNum = 0;
                        int i = 0;
                        boolean flag1 = true;
                        while (flag1 && i < 10){
                            int temp1 = (h-buses[routeNum][i].StartHour)%2;
                            if (temp1 < 0){
                                temp1*=-1;
                            }
                            int temp2 = 0;
                            temp2 = (m-buses[routeNum][i].StartMin)/6;
                            if (temp2<0){
                                temp2 += 10;
                            }
                            System.out.println(i + " " + startStop + " " + temp2 + " " + buses[routeNum][i].StartMin);
                            if (startStop==temp2+1){
                                busNum = i;
                                flag1 = false;
                                break;
                            }
                            i++;
                        }
                    buses[routeNum][busNum].InitSeats(routeNum+1,busNum+1, u.gender);
                    AddSeatLabels(routeNum+1,busNum+1);
                    int min = (6-(time.minute%6));
                    resLab.setText("Route Num " + (routeNum+1) + " Bus Num " + (busNum+1) + " is " + min + " minutes away " + "Fare = " + 50);
                    int temp1 = Math.abs((destStop - startStop)) * 6, temp2 = 0;
                    temp1 += time.minute + min;
                    if (temp1>=60){
                        time.hour += 1;
                        temp1 -= 60;
                    }
                    resLab.setText("Route Num " + (routeNum+1) + " Bus Num " + (busNum+1) + " is " + min + " minutes away " + "Fare = " + 50 + " reach by " + time.hour + " : " + temp1);
                    time.minute = temp1;
                    System.out.println(temp1);
                    b1 = new BookingDetails(routeNum,busNum,0,0,time,u);
                    appFrame.remove(sp);
                    appFrame.remove(hsp);
                    appFrame.remove(text1);
                    appFrame.remove(text2);
                    appFrame.remove(startRide);
                    appFrame.remove(menuPan);
                    appFrame.add(text6);
                    appFrame.add(bookRides);
                    appFrame.add(back);
                    return;
                }
                if (tripInfo[1][0]!=null){
                    resLab.setText("Take Route " + tripInfo[1][0] + " from " + tripInfo[1][2] + " to " + tripInfo[1][4] + " "
                    + " Take Route " + tripInfo[1][5] + " from " + tripInfo[1][7] + " to " + tripInfo[1][9]);
                }
            }else{
                JOptionPane.showMessageDialog(null,"Currently no buses are running");
            }
        }

        if (e.getSource()==bookRides){
            String seatNum = text6.getText();
            int r, c;
            if (Character.isDigit(seatNum.charAt(0))){
                r = Integer.parseInt(String.valueOf(seatNum.charAt(0)));
                seatRow = r;
                System.out.println(r);
            }else{
                JOptionPane.showMessageDialog(null,"Invalid Row Entry");
                return;
            }
            if (seatNum.length()==2){
                if (Character.isDigit(seatNum.charAt(1))){
                    c = Integer.parseInt(String.valueOf(seatNum.charAt(1)));
                    seatCol = c;
                    rideStatus = true;
                }else{
                    JOptionPane.showMessageDialog(null,"Invalid Column Entry");
                    return;
                }
            }else if (seatNum.length()==3){
                if (Character.isDigit(seatNum.charAt(1)) && Character.isDigit(seatNum.charAt(2))){
                    String temp = String.valueOf(seatNum.charAt(1)) + String.valueOf(seatNum.charAt(2));
                    c = Integer.parseInt(temp);
                    seatCol = c;
                    rideStatus = true;
                }else{
                    JOptionPane.showMessageDialog(null,"Invalid Column Entry");
                    return;
                }
            }else{
                JOptionPane.showMessageDialog(null,"Invalid Seat Entered");
                return;
            }
            if (rideStatus){
                boolean temp = buses[b1.RouteNum][b1.BusNum].Booking(b1.RouteNum+1,b1.BusNum+1,b1.t,seatRow,seatCol,u,tripInfo[0][2],tripInfo[0][4]);
                if (temp){
                    resLab.setText("Booking Successfull");
                }else{
                    resLab.setText("Booking Failed");
                }
                RemoveSeatLabels(b1.RouteNum, b1.BusNum);
                appFrame.add(text1);
                appFrame.add(text2);
                appFrame.add(startRide);
                appFrame.add(menuPan);
                appFrame.remove(text6);
                appFrame.remove(back);
                appFrame.remove(bookRides);
            }
        }

        if (e.getSource()==back){
            RemoveSeatLabels(b1.RouteNum,b1.BusNum);
            appFrame.add(text1);
            appFrame.add(text2);
            appFrame.add(startRide);
            appFrame.add(menuPan);
            appFrame.remove(text6);
            appFrame.remove(bookRides);
            appFrame.remove(back);
        }

    }

    public void AddSeatLabels(int r, int c){
        if (flag2){
            return;
        }
        for (int i = 0; i < 4; i++){
            for (int j = 0; j <20; j++){
                appFrame.add(buses[r-1][c-1].seats[i][j]);
            }
        }
        flag2 = true;
    }

    public void RemoveSeatLabels(int r, int c){
        if (!flag2){
            return;
        }
        for (int i = 0; i < 4; i++){
            for (int j = 0; j <20; j++){
                appFrame.remove(buses[r][c].seats[i][j]);
            }
        }
        flag2 = false;
    }

}
