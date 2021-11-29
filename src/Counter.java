public class Counter {

    private int c;

    public Counter() {
        c = 0;
    }

    public /*synchronized*/ void  inc(){
        c++;
    }

    public /*synchronized*/ void dec(){
        c--;
    }

    public int getC(){
        return c;
    }
}
