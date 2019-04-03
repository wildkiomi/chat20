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

}