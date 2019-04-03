import org.apache.log4j.Logger;

public class Message implements ICommand {

    private static Logger log = Logger.getLogger(Message.class);

    public User execute(User user, String outputMessage) {
        log.info("message");

        outputMessage=outputMessage.substring(outputMessage.indexOf(" ")+1);
        User[] chat = Server.chats.get(user.getNumberOfChat());

       if (chat[1] == null) {
            chat[0].setDMessage(outputMessage);
        }
        else {
            if (chat[0].getDMessage()!=null){
                outputMessage=chat[0].getDMessage();
                chat[0].devalueDMessage();
            }
                for (int i=0;i<2;i++) {
                    try {
                        chat[i].getoWriter().println(user.getName() + ": " + outputMessage);
                        chat[i].getoWriter().flush();
                        log.info("message from " + user.getName());
                    } catch (Exception e) {
                        log.error("can't send message");
                    }

            }
       }

        return user;
    }
}
