import org.apache.log4j.Logger;

public class Leave implements ICommand{

    private static Logger log = Logger.getLogger(Leave.class);

    public User execute(User user, String s) {

        log.info("disconnect");
        new Message().execute(user,"disconnect");
        if (user instanceof Agent) {
            Server.freeAgents.add(user);
            User[] newChat = {Server.chats.get(user.getNumberOfChat())[0], null};
            Server.chats.set(user.getNumberOfChat(), newChat);
        }
        if (user instanceof Client) {
            User[] newChat = {null,Server.chats.get(user.getNumberOfChat())[1]};
            Server.chats.set(user.getNumberOfChat(), newChat);
        }

        return user;
    }
}
