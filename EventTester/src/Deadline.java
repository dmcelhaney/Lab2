public class Deadline implements Comparable<Deadline> {
    boolean complete;
    void complete(){

    }   // sets the complete boolean to true;
    boolean isComplete(){
        return complete;
    }

    @Override
    public int compareTo(Deadline o) {
        return 0;
    }
}
