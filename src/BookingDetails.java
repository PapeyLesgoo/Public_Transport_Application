public class BookingDetails {
    int RouteNum, BusNum, i, j;
    MyTime t;
    User u;

    public BookingDetails(int routeNum, int busNum, int i, int j, MyTime t, User u) {
        RouteNum = routeNum;
        BusNum = busNum;
        this.i = i;
        this.j = j;
        this.t = t;
        this.u = u;
    }
}
