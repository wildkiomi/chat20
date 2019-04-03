import org.apache.log4j.Logger;

import java.io.IOException;

public class Exit implements ICommand{

    private static Logger log = Logger.getLogger(Exit.class);

    public User execute(User user, String s) {
            try {
                Server.clientSocket.close();
                log.info("client socket closed");
                new Message().execute(user,"exit");
                System.exit(0);
            } catch (IOException e) {
                log.error("can't close client socket");
            }
            return null;
    }
}
