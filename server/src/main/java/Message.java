import org.apache.log4j.Logger;

public class Message implements ICommand {

    private static Logger log = Logger.getLogger(Message.class);

    public synchronized User execute(User user, String outputMessage) {
        outputMessage = outputMessage.substring(outputMessage.indexOf(" ") + 1);
        User[] chat = Server.chats.get(user.getNumberOfChat());
        if (chat[1] == null) {
            chat[0].setdMessage(outputMessage);
        } else {
            if (chat[0].getdMessage()!=null){
                outputMessage=outputMessage+"\n"+chat[0].getName()+": "+chat[0].getdMessage();
chat[0].devalueDMessage();}
            for (User member : chat) {
                try {
                    member.getoWriter().println(user.getName() + ": " + outputMessage);
                    member.getoWriter().flush();
                    log.info("сообщение от " + user.getName() + " к " + member.getName());

                } catch (Exception e) {
                    log.error("не отправляются сообщения собеседнику");
                }
            }
        }
            return user;
        }
}
