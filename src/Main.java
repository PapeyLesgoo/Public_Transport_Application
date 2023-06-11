import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class Main{
    public static void main(String[] args) {
        BusSeats bus[][] = new BusSeats[6][10];
        for (int i = 0; i < 6;i++){
            for (int j = 0; j < 10;j++){
                bus[i][j] = new BusSeats();
                bus[i][j].InitServer(i+1,j+1);
            }
        }
        //Run to open Driver side
        //Driver d =new Driver();
        InitFiles();
        //Run to open login
        Login l = new Login();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now));
    }

    public static void InitFiles(){
        for (int i = 1; i < 7;i++){
            for (int j = 1; j < 11;j++){
                String s = "Bus" + i + "" + j + ".txt";
                try {
                    File f = new File(s);
                    if (!f.exists()){
                        f.createNewFile();
                        FileWriter fw = new FileWriter(f);
                        BufferedWriter bw = new BufferedWriter(fw);
                        for (int r = 0; r < 4; r++){
                            System.out.println("New File");
                            bw.write("00000000000000000000");
                            bw.newLine();
                        }
                        bw.write("-");
                        bw.close();
                        fw.close();
                    }
                }catch (Exception e){

                }
            }
        }
    }
}