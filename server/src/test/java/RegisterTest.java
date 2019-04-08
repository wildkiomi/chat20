import org.junit.Test;

import static org.junit.Assert.*;

public class RegisterTest {

    @Test
    public void execute() {
        Register registerTest=new Register();
        User user=null;
        String s="/register client hanna";

        assertEquals(registerTest.execute(user,s).getName(),"hanna");
        assertEquals(registerTest.execute(user,s).getType(),"client");
    }
}