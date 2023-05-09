import mediator.Friend;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class FriendTest {
    private Friend f;

    @BeforeEach public void setup(){
        f = new Friend("name","ur status");
    }

    @Test public void getUsernameOne(){
        assertEquals("name",f.getUsername());
    }
    @Test public void getStatusOne(){
        assertEquals("ur status",f.getStatus());
    }
    @Test public void setStatusZero(){
        f.setStatus(null);
        assertEquals(null,f.getStatus());
    }
    @Test public void setStatusOne(){
        f.setStatus("null");
        assertEquals("null",f.getStatus());
    }
    @Test public void setStatusMany(){
        f.setStatus("null");
        f.setStatus("aaa");
        f.setStatus("bbb");
        assertEquals("bbb",f.getStatus());
    }

    @Test public void setUsernameZero(){
        f.setUsername(null);
        assertEquals(null,f.getUsername());
    }
    @Test public void setUsernameOne(){
        f.setUsername("null");
        assertEquals("null",f.getStatus());
    }
    @Test public void setUsernameMany(){
        f.setUsername("null");
        f.setUsername("aaa");
        f.setUsername("bbb");
        assertEquals("bbb",f.getUsername());
    }
}
