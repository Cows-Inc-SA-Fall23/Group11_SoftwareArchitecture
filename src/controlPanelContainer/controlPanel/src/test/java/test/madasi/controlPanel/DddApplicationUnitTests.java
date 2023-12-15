package test.madasi.controlPanel;
import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import madasi.controlPanel.model.User;
import madasi.controlPanel.util.CustomUtil;

public class DddApplicationUnitTests {

    @Test
    public void testJson() throws Exception {
    	String test = CustomUtil.convertObjectToJson("Some string");
    	
    	assertEquals("\"Some string\"", test);
    	
    	List<String> someStrings = new ArrayList<>();
    	someStrings.add("Some");
    	someStrings.add("String");
    	
    	test = CustomUtil.convertObjectToJson(someStrings);
    	
    	assertEquals("[\"Some\",\"String\"]", test);
    	
    	User u = new User();
    	u.setId(1);
    	u.setEmail("mail");
    	u.setName("name");
    	
    	test = CustomUtil.convertObjectToJson(u);
    	assertEquals("{\"id\":1,\"name\":\"name\",\"email\":\"mail\",\"phoneNo\":null,\"password\":null,\"user_level\":null}", test);
    	
    	User deserializedUser = (User) CustomUtil.convertJsonToObject(test, User.class);
    	
    	assertEquals(u,deserializedUser);
    	
    }
}
