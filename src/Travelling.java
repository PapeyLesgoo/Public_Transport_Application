import java.util.Scanner;

public class Travelling {
    String[][] routes;
    String[] stops;
    int[][] route1;
    int[][] route2;
    int[][] route3;
    int[][] route4;
    int[][] route5;
    int[][] route6;

    Travelling(){
        route1=set_routes_values();
        route2=set_routes_values();
        route3=set_routes_values();
        route4=set_routes_values();
        route5=set_routes_values();
        route6=set_routes_values();
        stops=set_stops();
        routes=set_routes();
    }
    public String[] set_stops(){
        String[] stops={"Malir Halt", "Colony Gate", "Nata Khan Bridge", "Drigh Road Station", "PAF Base Faisal", "Laal Kothi", "Karsaz", "Nursery", "FTC", "Regent Plaza","Nagan Chowrangi",
                "Shafiq Morr", "Sohrab Goth", "Gulshan Chowranei", "NIPA","Johar Morr", "COD", "Drigh Road Station"
                , "Colony Gate","Shah Faisal Colony ","5 Star Chowrangi", "KDA Chowrangi", "Nazimabad Eid Gah Ground", "Liaquatabad 10 Number", "Essa Nagri", "Civic Centre", "National stadium", "Karsaz", "Nursery","FTC",
                "Fast University", "Bhains Colony More", "Manzil Pump", "Quaidabad", "Murghi Khana", "Nadra Center Malir", "Malir 15", "Malir Halt", "Colony Gate", "Drigh Road Station",
                "Numaish", "Tower", "I.I. Chunrigar Road", "Shaheen Complex", "Arts Council", "Metropole", "Frere Hall", "3 Talwar", "2 Talwar", "Dolmen Mall Cilfton",
                "Shireen Jinnah", "Bilawal Chowrangi", "Boat Basin", "US Consulate General", "crown cinema", "Mauripur", "Keamari", "Miran Nakka", "piyala hotel", "Lyari"};
        return stops;
    }
    public static String[][] set_routes() {
        String[][] route={{"Malir Halt", "Colony Gate", "Nata Khan Bridge", "Drigh Road Station", "PAF Base Faisal", "Laal Kothi", "Karsaz", "Nursery", "FTC", "Regent Plaza"},
                {"Nagan Chowrangi", "Shafiq Morr", "Sohrab Goth", "Gulshan Chowranei", "NIPA", "Johar Morr", "COD", "Drigh Road Station", "Colony Gate","Shah Faisal Colony "},
                {"5 Star Chowrangi", "KDA Chowrangi", "Nazimabad Eid Gah Ground", "Liaquatabad 10 Number", "Essa Nagri", "Civic Centre", "National stadium", "Karsaz", "Nursery","FTC"},
                {"Fast University", "Bhains Colony More", "Manzil Pump", "Quaidabad", "Murghi Khana", "Nadra Center Malir", "Malir 15", "Malir Halt", "Colony Gate", "Drigh Road Station"},
                {"Numaish", "Tower", "I.I. Chunrigar Road", "Shaheen Complex", "Arts Council", "Metropole", "Frere Hall", "3 Talwar", "2 Talwar", "Dolmen Mall Cilfton"},
                {"Shireen Jinnah", "Bilawal Chowrangi", "Boat Basin", "US Consulate General", "crown cinema", "Mauripur", "Keamari", "Miran Nakka", "piyala hotel", "Lyari"}};
        return route;
    }
    public void display_routes(String[][] routes){
        for (int i=0;i<6;i++){
            System.out.print("Route #"+(i+1)+" : \n\t");
            for (int j=0;j<10;j++){
                if (j==9){
                    System.out.print(routes[i][j]);
                }else {
                    System.out.print(routes[i][j]+" ==> ");
                }
            }
            System.out.println("\n");
        }
    }
    public static class queue{
        Node head;
        class Node{
            String data;
            Node next;
        }
        queue(){
            this.head=null;
        }

        void enqueue(String data){
            Node n=new Node();
            n.data=data;
            n.next=null;
            if (head==null){
                head=n;
            }else {
                Node temp=head;
                while (temp.next!=null){
                    temp=temp.next;
                }
                temp.next=n;
            }
        }
        String dequeque(){
            if (head==null){
                System.out.println("queue is empty");
            }
            String temp=head.data;
            head=head.next;
            return temp;
        }
        String peek(){
            if (head==null){
                System.out.println("queue is empty");
            }
            return head.data;
        }
        boolean isempty(){
            if(head==null){
                return true;
            }else{
                return false;
            }
        }
        void print_queue(){
            Node temp=head;
            while (temp!=null){
                System.out.print(temp.data+" \n");
                temp=temp.next;
            }
        }
    }
    public void display_stops(String[] stops){
//        for (int i=0;i<stops.length;i++){
//            System.out.println((i+1)+" : "+stops[i]);
//        }
        queue stops_list=new queue();
        for (int i=0;i<stops.length;i++){
            stops_list.enqueue(stops[i]);
        }
        stops_list.print_queue();
    }
    public void display_a_route(String[][] routes){
        Scanner sc=new Scanner(System.in);
        System.out.println("enter the route number you wish to see");
        int temp=sc.nextInt();
        System.out.println("Route #"+temp+" : ");
        for (int j=0;j<10;j++){
            if (j==9){
                System.out.print(routes[temp-1][j]);
            }
            else if (j==0){
                System.out.print("\t"+routes[temp-1][j]+" ==> ");
            }else {
                System.out.print(routes[temp-1][j]+" ==> ");
            }
        }
    }
    public static int[][] set_routes_values(){
        int[][] route1 = new int[10][10];
        int i=0,j=1;
        while (i<9 && j<10){
            route1[i][j]=1;
            i++;
            route1[i][j-1]=1;
            j++;
        }
        return route1;
    }
    public boolean search_stop(String[] stops, String name){
        Scanner sc=new Scanner(System.in);
        //System.out.println("enter the name of the stop");
        //String stop=sc.nextLine();
        int flag=0;
        for (int i=0;i<stops.length;i++){
            if (stops[i].equalsIgnoreCase(name)){
                flag=1;
            }
        }
        if (flag==1){
            System.out.println("stop is available");
            return true;
        }else {
            System.out.println("we are sorry,the entered stop is not available");
            return false;
        }
    }
    public void start_trip(String[][] routes,String[] stops,int[][] route1,int[][] route2,int[][] route3,int[][] route4,int[][] route5,int[][] route6,String[][] trip_information, String start_stop, String destination_stop

    ){

        if (start_stop.equalsIgnoreCase(destination_stop)){
            return;
        }
        int flag1=0,flag2=0,fare=0;
        for (int i=0;i<routes.length;i++){
            for (int j=0;j< routes.length;j++){
                if (start_stop.equalsIgnoreCase(routes[i][j])){
                    flag1=1;
                }
                if (destination_stop.equalsIgnoreCase(routes[i][j])){
                    flag2=1;
                }
            }
        }
        if (flag1!=1 || flag2!=1){
            if (flag1==0){
                System.out.println("the starting point of trip is unfortunately not included in the predefined stops of bus service");
            }
            if (flag2==0){
                System.out.println("the destination of trip is unfortunately not included in the predefined stops of bus service");
            }
            if (flag1==0 && flag2==0){
                System.out.println("the entered stops are not included int the predefined stops list of the service");
            }
            return;
        }
        int present_in_one=0,index=-1,start_point_number=-1,destination_point_number=-1;

        for (int i=0;i<6;i++){
            int temp1=0,temp2=0;
            for (int j=0;j< 10;j++){
                if (start_stop.equalsIgnoreCase(routes[i][j])){
                    temp1=1;
                    start_point_number=j;
                }
                if (destination_stop.equalsIgnoreCase(routes[i][j])){
                    temp2=1;
                    destination_point_number=j;
                }
                if (temp2==1 && temp1==1){
                    present_in_one=1;
                    index=i;
                    break;
                }
            }
            if (present_in_one==1){
                break;
            }
        }
        if (present_in_one==1){
            if (index==0){
                int stopek=-1,stopdo=-1;
                for (int i=0;i< routes.length;i++){
                    if (routes[index][i].equalsIgnoreCase(start_stop)){
                        stopek=i;
                    }
                    if (routes[index][i].equalsIgnoreCase(destination_stop)){
                        stopdo=i;
                    }
                }
                if (stopdo>stopek){
                    int i,j=stopek;
                    if (stopek!=0){
                        i=stopek-1;
                    }else {
                        i=stopek;
                        j=stopek+1;
                        fare=fare+30;
                    }
                    do {
                        i++;
                        j++;
                        if (route1[i][j]==1){
                            fare=fare+30;
                        }
                    }while (j!=stopdo);
                }else {
                    int i=stopek+1,j=stopek;
                    do {
                        i--;
                        j--;
                        if (route1[i][j]==1){
                            fare=fare+30;
                        }
                    }while (j!=stopdo);
                }
            }
            else if (index==1){
                int stopek=-1,stopdo=-1;
                for (int i=0;i< routes.length;i++){
                    if (routes[index][i].equalsIgnoreCase(start_stop)){
                        stopek=i;
                    }
                    if (routes[index][i].equalsIgnoreCase(destination_stop)){
                        stopdo=i;
                    }
                }
                if (stopdo>stopek){
                    int i,j=stopek;
                    if (stopek!=0){
                        i=stopek-1;
                    }else {
                        i=stopek;
                        j=stopek+1;
                        fare=fare+30;
                    }
                    do {
                        i++;
                        j++;
                        if (route2[i][j]==1){
                            fare=fare+30;
                        }
                    }while (j!=stopdo);
                }else{
                    int i=stopek+1,j=stopek;
                    do {
                        i--;
                        j--;
                        if (route2[i][j]==1){
                            fare=fare+30;
                        }
                    }while (j!=stopdo);
                }
            }
            else if (index==2){
                int stopek=-1,stopdo=-1;
                for (int i=0;i< routes.length;i++){
                    if (routes[index][i].equalsIgnoreCase(start_stop)){
                        stopek=i;
                    }
                    if (routes[index][i].equalsIgnoreCase(destination_stop)){
                        stopdo=i;
                    }
                }
                if (stopdo>stopek){
                    int i,j=stopek;
                    if (stopek!=0){
                        i=stopek-1;
                    }else {
                        i=stopek;
                        j=stopek+1;
                        fare=fare+30;
                    }
                    do {
                        i++;
                        j++;
                        if (route3[i][j]==1){
                            fare=fare+30;
                        }
                    }while (j!=stopdo);
                }else{
                    int i=stopek+1,j=stopek;
                    do {
                        i--;
                        j--;
                        if (route3[i][j]==1){
                            fare=fare+30;
                        }
                    }while (j!=stopdo);
                }
            }
            else if (index==3){
                int stopek=-1,stopdo=-1;
                for (int i=0;i<10;i++){
                    if (routes[index][i].equalsIgnoreCase(start_stop)){
                        stopek=i;
                    }
                    if (routes[index][i].equalsIgnoreCase(destination_stop)){
                        stopdo=i;
                    }
                }
                if (stopdo>stopek){
                    int i,j=stopek;
                    if (stopek!=0){
                        i=stopek-1;
                    }else {
                        i=stopek;
                        j=stopek+1;
                        fare=fare+30;
                    }
                    do {
                        i++;
                        j++;
                        if (route4[i][j]==1){
                            fare=fare+30;
                        }
                    }while (j!=stopdo);
                }else{
                    int i=stopek+1,j=stopek;
                    do {
                        i--;
                        j--;
                        if (route4[i][j]==1){
                            fare=fare+30;
                        }
                    }while (j!=stopdo);
                }
            }
            else if (index==4){
                int stopek=-1,stopdo=-1;
                for (int i=0;i<10;i++){
                    if (routes[index][i].equalsIgnoreCase(start_stop)){
                        stopek=i;
                    }
                    if (routes[index][i].equalsIgnoreCase(destination_stop)){
                        stopdo=i;
                    }
                }
                if (stopdo>stopek){
                    int i,j=stopek;
                    if (stopek!=0){
                        i=stopek-1;
                    }else {
                        i=stopek;
                        j=stopek+1;
                        fare=fare+30;
                    }
                    do {
                        i++;
                        j++;
                        if (route5[i][j]==1){
                            fare=fare+30;
                        }
                    }while (j!=stopdo);
                }else{
                    int i=stopek+1,j=stopek;
                    do {
                        i--;
                        j--;
                        if (route5[i][j]==1){
                            fare=fare+30;
                        }
                    }while (j!=stopdo);
                }
            }
            else if (index==5){
                int stopek=-1,stopdo=-1;
                for (int i=0;i< routes.length;i++){
                    if (routes[index][i].equalsIgnoreCase(start_stop)){
                        stopek=i;
                    }
                    if (routes[index][i].equalsIgnoreCase(destination_stop)){
                        stopdo=i;
                    }
                }
                if (stopdo>stopek){
                    int i,j=stopek;
                    if (stopek!=0){
                        i=stopek-1;
                    }else {
                        i=stopek;
                        j=stopek+1;
                        fare=fare+30;
                    }
                    do {
                        i++;
                        j++;
                        if (route6[i][j]==1){
                            fare=fare+30;
                        }
                    }while (j!=stopdo);
                }else{
                    int i=stopek+1,j=stopek;
                    do {
                        i--;
                        j--;
                        if (route6[i][j]==1){
                            fare=fare+30;
                        }
                    }while (j!=stopdo);
                }
            }
            trip_information[0][0]=String.valueOf(index+1);  //route number
            trip_information[0][1]=String.valueOf(start_point_number+1);  // starting stop number
            trip_information[0][2]=start_stop;                            // starting stop name
            trip_information[0][3]=String.valueOf(destination_point_number+1);  // destination stop number
            trip_information[0][4]=destination_stop;                      // destination stop name

            System.out.println("the trip will cost you : "+fare+"Rs");
        }else{
            int start_route=-1,destination_route=-1,start_route_point=-1,destination_route_point=-1,fare1=0,fare2=0;
            for (int i=0;i<routes.length;i++){
                for (int j=0;j<routes.length;j++){
                    if (routes[i][j].equalsIgnoreCase(start_stop)){
                        start_route=i;
                        start_route_point=j;
                    }
                    if (routes[i][j].equalsIgnoreCase(destination_stop)){
                        destination_route=i;
                        destination_route_point=j;
                    }
                    if (start_route!=-1 && destination_route!=-1){
                        break;
                    }
                }
                if (start_route!=-1 && destination_route!=-1){
                    break;
                }
            }
            int destination_interval_stop=-1,start_interval_stop=-1,flag_possible=0;
            String start_interval_name = null,destination_interval_name = null;
            for (int i=0;i<10;i++){
                for (int j=0;j<10;j++){
                    if (routes[destination_route][i].equalsIgnoreCase(routes[start_route][j])){
                        start_interval_stop=j;
                        start_interval_name=routes[start_route][j];
                        destination_interval_stop=i;
                        destination_interval_name=routes[destination_route][i];
                        flag_possible=1;
                        break;
                    }
                }
            }
            if (flag_possible==0){
                System.out.println(start_interval_stop);
                System.out.println("cannot reach destination by the routes of our services");
            }else {
                if(start_interval_stop>start_route_point){
                    fare=fare+(start_interval_stop-start_route_point)*30;
                    fare1=fare;
                    if (destination_route_point>destination_interval_stop){
                        fare=fare+(destination_route_point-destination_interval_stop)*30;
                        fare2=fare-fare1;
                    }else {
                        fare=fare+(destination_interval_stop-destination_route_point)*30;
                        fare2=fare-fare1;
                    }
                }else {
                    fare=fare+(start_route_point-start_interval_stop)*30;
                    fare1=fare;
                    if (destination_route_point>destination_interval_stop){
                        fare=fare+(destination_route_point-destination_interval_stop)*30;
                        fare2=fare-fare1;
                    }else {
                        fare=fare+(destination_interval_stop-destination_route_point)*30;
                        fare2=fare-fare1;
                    }
                }
                fare=fare+30;
                fare2=fare2*2;
                System.out.println("take route#"+(start_route+1)+" till "+routes[start_route][start_interval_stop-2]+" and then take route#"+(destination_route+1)+" to "+routes[destination_route][destination_route_point]);
                System.out.println("route#"+(start_route+1)+" will cost "+50+"Rs and route#"+(destination_route+1)+" will cost "+50+"Rs");
                System.out.println("Total = "+fare+"Rs");
                trip_information[1][0]=String.valueOf(start_route+1);  //start route number
                trip_information[1][1]=String.valueOf(start_route_point+1);   // starting stop#
                trip_information[1][2]=start_stop;                            // starting stop name
                trip_information[1][3]=String.valueOf(start_interval_stop+1); // start interval stop#
                trip_information[1][4]=start_interval_name;     // starting stop interval stop name
                trip_information[1][5]=String.valueOf(destination_route+1);  // route number 2
                trip_information[1][6]=String.valueOf(destination_interval_stop+1); // starting stop# for route#2
                trip_information[1][7]=destination_interval_name;  // starting stop name for route#2
                trip_information[1][8]=destination_stop+1;    // destination stop#
                trip_information[1][9]=destination_stop;      // destination stop name
            }
        }
    }
}
