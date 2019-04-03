import org.apache.log4j.Logger;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Server{

    private static final Logger log= Logger.getLogger(Server.class);

    public final int PORT=6666;
    public ServerSocket serverSocket;
    public static Socket clientSocket;
    public static ArrayList<User[]> chats=new ArrayList();
    public static ArrayList<User> freeAgents=new ArrayList();
    public static Map<String,ICommand> map=new HashMap<String, ICommand>();
    private static Server instance;

    private Server(){
        go();
    }

    {
        log.info("инициализация arraylist");
        map.put("/register",new Register());
        map.put("/leave",new Leave());
        map.put("/exit",new Exit());
        map.put("/message", new Message());
    }

    public static Server getInstance(){
        if (instance==null){
            synchronized (Server.class){
                if (instance==null)
                    instance=new Server();
            }
        }
        return instance;
    }

    public void go(){
        try {
            serverSocket = new ServerSocket(PORT);
            while (true){
                clientSocket = serverSocket.accept();
                Thread thread=new Thread(new UsersStreams(clientSocket));
                thread.start();

            }
        }
        catch (IOException e){e.printStackTrace();}
    }

    public static void main(String[] args){
        getInstance();
    }

}