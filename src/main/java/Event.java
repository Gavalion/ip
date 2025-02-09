public class Event extends Task{
    protected String from ;

    public Event(String description, String from) {
        super(description);
        this.from = from;
    }
    public String getFrom() {
        return from;
    }
    public void setFrom(String by) {
        this.from = from;
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(" + getFrom() + ")";
//        return "description: "+super.getDescription() +"\n" +" " + ((isDone) ? "yes": "no") +"\n";
    }
}

