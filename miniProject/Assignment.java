public class Assignment {
//fields :
    private int untilTheDeadline ; 
    private boolean isActive ; 
//finish


//constructors :
    public Assignment(){
        isActive = true ;
    }
    public Assignment(int untilTheDeadline){
        this() ;
        this.untilTheDeadline = untilTheDeadline ;
    }
//finish
    
//setters :
    public void setUntilTheDeadline(int untilTheDeadline){
        this.untilTheDeadline = untilTheDeadline ;
    }
    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }
//finish


//getters :
    public int getUntilTheDeadline() {
        return untilTheDeadline;
    }
    public boolean isActive() {
        return isActive;
    }
//finish
}
