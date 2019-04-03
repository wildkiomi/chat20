import org.apache.log4j.Logger;

public class Register implements ICommand{

    private static Logger log = Logger.getLogger(Register.class);

    public User execute(User user, String message) {
        String type=message.substring(message.indexOf(" ")+1,message.lastIndexOf(" "));
        String name=message.substring(message.lastIndexOf(" ")+1);
        if (type.contains("client")) {
            user = new Client ();
        }
        if (type.contains("agent")) {
            user = new Agent();
            Server.freeAgents.add(user);
        }
        user.setName(name);
        user.setoWriter(UsersStreams.writer);
        log.info("registration of  "+user.getName());

        return user;
        }


}
