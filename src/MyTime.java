public class MyTime {
    int year, month, date, hour, minute, seconds;

    public int compare(MyTime t){
        if (t.year>year){
            System.out.println("year");
            return 1;
        }
        if (t.month>month){
            System.out.println("month");
            return 1;
        }
        if (t.date>date){
            System.out.println("date");
            return 1;
        }
        if (t.hour>hour){
            System.out.println("hour");
            return 1;
        }
        if (t.minute>=minute && t.hour==hour){
            return 1;
        }
        return -1;
    }
}
