import org.apache.log4j.Logger;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class UsersStreams implements Runnable{

        public BufferedReader reader;
        public User user;
        public static PrintWriter writer;
        public static String inputMessage;
        private static final Logger log= Logger.getLogger(UsersStreams.class);

        public UsersStreams(Socket clientSocket){
            try {
                InputStreamReader streamReader = new InputStreamReader(clientSocket.getInputStream());
                reader = new BufferedReader(streamReader);
                writer = new PrintWriter(clientSocket.getOutputStream());
            }
            catch (IOException e){
                log.error("error with streams");
            }

        }

        public void run(){
            try {
                while ((inputMessage=reader.readLine()) != null) {
                    parsing(inputMessage);
                }
            }
            catch (Exception e){log.error("can't read message from client");}

        }

        public void parsing(String s) {
            log.info("parsing");
                if (s.contains(" ")) s=s.substring(0,s.indexOf(" "));
                user=Server.map.get(s).execute(user,inputMessage);
                if (!s.equals("/message")) connecting();
        }

        public synchronized void connecting() {
            boolean connection = false;
            if (user instanceof Client){
                for (int i = 0; i < Server.chats.size(); i++) {
                    if ((Server.chats.get(i)[1] != null) && (Server.chats.get(i)[0]==null)&&(user.getNumberOfChat() != Server.chats.get(i)[1].getNumberOfChat())) {
                        user.setNumberOfChat(Server.chats.get(i)[1].getNumberOfChat());
                        User[] users = {user,Server.chats.get(i)[1]};
                        Server.chats.set(i, users);
                        log.info("connect " + Server.chats.get(i)[0].getName() + " " + Server.chats.get(i)[1].getName());
                        connection = true;
                        break;
                    }
                }
                if (!connection){
                    User[] chat = new User[2];
                    chat[0] = user;
                    Server.chats.add(chat);
                    user.setNumberOfChat(Server.chats.size()-1);
                }
            }
            int k = 0;
            for (User agent : Server.freeAgents) {
                for (int i = 0; i < Server.chats.size(); i++) {
                    if ((Server.chats.get(i)[1] == null) && (agent.getNumberOfChat() != Server.chats.get(i)[0].getNumberOfChat())) {
                        agent.setNumberOfChat(Server.chats.get(i)[0].getNumberOfChat());
                        User[] users = {Server.chats.get(i)[0], agent};
                        Server.chats.set(i, users);
                        Server.freeAgents.remove(k);
                        log.info("connect " + Server.chats.get(i)[0].getName() + " " + Server.chats.get(i)[1].getName());
                        connection = true;
                        break;
                    }
                }
                if (connection) {
                    new Message().execute(user,"connect");
                    break;}
                k++;
            }


        }

    }

