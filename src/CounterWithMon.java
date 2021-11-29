public class CounterWithMon {
    private int c;
    Object mon = new Object();
    Object mon2 = new Object();

    public CounterWithMon() {
        c = 0;
    }

    public void  inc(){
        synchronized (mon){
            c++;
        }
    }

    public void dec(){
        synchronized (mon){
            c--;
        }
    }

    public void  inc2(){
        synchronized (mon2){
            c++;
        }
    }

    public void dec2(){
        synchronized (mon2){
            c--;
        }
    }

    public int getC(){
        return c;
    }

}
