import java.io.PrintWriter;

public class User {

    private String name;
    private PrintWriter oWriter;
    private Integer numberOfChat;
    private String dMessage=null;

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


    public String getdMessage() {
        return dMessage;
    }

    public void setdMessage(String dMessage) {
        if (this.dMessage==null)
        this.dMessage = dMessage;
        else {this.dMessage=this.dMessage+"\n"+dMessage;}
    }
    public void devalueDMessage(){
        this.dMessage=null;
    }
}