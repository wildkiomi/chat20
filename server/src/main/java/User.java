import java.io.PrintWriter;

public class User {

    private String name;
    private PrintWriter oWriter;
    private Integer numberOfChat;

    public void setName(String name) {
        this.name = name;
    }

    public void setNumberOfChat(Integer numberOfChat) {
        this.numberOfChat = numberOfChat;
    }

    public void setoWriter(PrintWriter oWriter) {
        this.oWriter = oWriter;
    }

    public String getName() {
        return name;
    }

    public PrintWriter getoWriter() {
        return oWriter;
    }

    public Integer getNumberOfChat() {
        return numberOfChat;
    }

    private String delayedMessage=null;

    public void setDMessage(String s){
        if (delayedMessage==null)
            this.delayedMessage=s;
        else
            this.delayedMessage=this.delayedMessage+"\n"+s;
    }

    public String getDMessage(){
        return this.delayedMessage;
    }

    public void devalueDMessage(){
        this.delayedMessage=null;
    }
}