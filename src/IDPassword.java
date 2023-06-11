import java.io.*;
import java.util.HashMap;

public class IDPassword {
    HashMap<String,String> map = new HashMap<String,String>();

    IDPassword(){
        File file = new File("IDAndPassword.txt");
        if(file.exists()){
            try{
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);
                String line1 = "Lesgoo";
                String line2 = null;
                br.readLine();
                while ((line1 = br.readLine())!=null){
                    line2 = br.readLine();
                    map.put(line1,line2);
                }
                br.close();
                fr.close();
            }
            catch (Exception e){
                System.out.println("Jaani");
            };

        }
        else {
            try {
                file.createNewFile();
            }catch (Exception e){

            }
        }

    }

    public void Insert(String username, String password){
        File file = new File("IDAndPassword.txt");
        try {
            FileWriter fw = new FileWriter(file,true);
            BufferedWriter bw = new BufferedWriter(fw);
            if(file.exists()){
                if (map.containsKey(username)){
                    System.out.println("Username already exists ");
                }else {
                    bw.newLine();
                    bw.write(username);
                    bw.newLine();
                    bw.write(password);
                    bw.close();
                    fw.close();
                }
            }
            else {
                file.createNewFile();
                bw.write(username);
                bw.newLine();
                bw.write(password);
                bw.close();
                fw.close();
            }
        }
        catch (Exception e){
            System.out.println("Jaani");
        }
    }
}
