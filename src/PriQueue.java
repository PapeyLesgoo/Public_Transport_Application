public class PriQueue {
    TimeNode head;
    int size;

    public void Insert(MyTime t, int r, int c, int stop, int dest,String username){
        size++;
        TimeNode newNode = new TimeNode(t,r,c,stop,dest,username);
        if (head == null){
            head = newNode;
        }else{
            int i = 0;
            TimeNode n = head;
            TimeNode temp = null;
            while (n.next!=null&&n.t.compare(t)==1){
                temp = n;
                n = n.next;
                i++;
            }
            if(i==0){
                newNode.next = head;
                head = newNode;
            }
            else if (n.next==null){
                n.next = newNode;
            }else{
                newNode.next = temp.next;
                temp.next = newNode;
            }
        }
    }

    public void Remove(MyTime t, String s[][]){
        if (head == null){
            return;
        }
        while (head!=null &&head.t.compare(t)==1){
            System.out.println("Deleting");
            size--;
            s[head.i][head.j] = "0";
            head = head.next;
        }
    }

    public boolean isEmpty(){
        if (head==null){
            return true;
        }
        return false;
    }

    public String toString(int i){
        int j = 0;
        String s = "";
        TimeNode n = head;
        while (i!=j){
            j++;
            n = n.next;
        }
        s = n.t.year + "," + n.t.month + "," + n.t.date + "," + n.t.hour + "," + n.t.minute + "," + n.i
                + "," + n.j + "," + n.username;
        return s;
    }

}
class TimeNode{
    TimeNode next;
    MyTime t;
    int i, j, stop, dest;
    String username;

    public TimeNode(MyTime t, int i, int j, int stop, int dest, String username) {
        this.t = t;
        this.i = i;
        this.j = j;
        this.stop = stop;
        this.dest = dest;
        this.username = username;
    }
}
