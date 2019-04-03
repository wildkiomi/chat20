import org.apache.log4j.Logger;
import java.io.*;
import java.net.Socket;


public class ClientChat {

    private static final Logger log= Logger.getLogger(ClientChat.class);
    private final String HOST="127.0.0.1";
    private final int PORT=6666;
    private Socket socket;
    private BufferedReader reader,readerConsole;
    private PrintWriter writer;
    private String outputMessage;

    public class IncomingReader implements Runnable{
        public void run() {
            String inputMessage;
            try{
                while ((inputMessage=reader.readLine())!=null){
                    System.out.println(inputMessage);
                    log.info("read message from server");
                }}
            catch (IOException e){
                log.error("error with reading message from server");
            }

        }
    }


    public void go(){
        try{
            socket=new Socket(HOST,PORT);
            InputStreamReader streamReader=new InputStreamReader(socket.getInputStream());
            reader=new BufferedReader(streamReader);
            writer=new PrintWriter(socket.getOutputStream());
            readerConsole=new BufferedReader(new InputStreamReader(System.in));
        }
        catch (Exception e)
        {log.error("error with socket");
        }
        Thread thread=new Thread(new IncomingReader());
        thread.start();
        try{
            while ((outputMessage=readerConsole.readLine())!=null){
                writer.println(parsing(outputMessage));
                writer.flush();
                log.info("send message to server");
            }}
        catch (Exception e){log.error("error with sending message to server");
        }
    }

    public ClientChat(){
        go();
    }

    private String parsing(String s){
        if (!s.startsWith("/"))
            s="/message "+s;
        return s;
    }


    public static void main(String[] args){
        new ClientChat();
        log.info("new client chat");
    }

}