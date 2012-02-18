package pl.edu.pjwstk.mpr.notes.app;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AppTest 
    extends TestCase
{
    
    public AppTest( String testName )
    {
        super( testName );
    }

    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    public void testApp()
    {
        assertTrue(true);
    }
    public void testCategory()
    {
    	String testS = "test";
    	String testS1 = "abcdef";
    	String testS2 = "jfjhaqhohufaiuh39";
    	int testI = 1234;
        Category c = new Category(testS);
        assertEquals(c.getName(), testS);
        c.setName(testS1);
        assertEquals(c.getName(), testS1);
        c.setId(testI);
        assertEquals(c.getId(), testI);
        c.setDescription(testS2);
        assertEquals(c.getDescription(), testS2);
    }
    public void testPost(){
    	String testS = "test";
    	int testI = 1234;
    	int testx = 1234;
    	Note p = new Note(testS, testI, testx, null);
    	assertEquals(p.getContent(), testS);
    }
    
    public void testStorage(){
    	Storage s = new Storage();
    	try {
    		PreparedStatement xxx = s.c.prepareStatement("INSERT into category (id, name , description) VALUES (?,?,?)");
    		xxx.setInt(1, 888888);
    		xxx.setString(2, "asdfjahlkdfads");
    		xxx.setString(3, "af37473hsdfjahlkdfads");
    		xxx.executeUpdate();
    		xxx = s.c.prepareStatement("INSERT into thread (id,name, category_id) VALUES (?,?,?)");
    		xxx.setInt(1, 999999);
    		xxx.setString(2, "abcd2jk34h1l234efgh");
    		xxx.setInt(3, 888888);
    		xxx.executeUpdate();
    		PreparedStatement ps = s.c.prepareStatement("INSERT into note (content, thread_id) VALUES (?,?)");
			ps.setString(1, "abcdef");
			ps.setInt(2, 999999);
			ps.executeUpdate();
			PreparedStatement pr = s.c.prepareStatement("SELECT * FROM note WHERE content = ?");
			pr.setString(1, "abcdef");
			ResultSet rs = pr.executeQuery();
			assertTrue(rs.next());
			PreparedStatement pk = s.c.prepareStatement("DELETE FROM note WHERE content = ?");
			pk.setString(1, "abcdef");
			pk.executeUpdate();
			xxx = s.c.prepareStatement("DELETE FROM thread where id = ?");
			xxx.setInt(1, 999999);
			xxx.executeUpdate();
			xxx = s.c.prepareStatement("DELETE FROM category where id = ?");
			xxx.setInt(1, 888888);
			xxx.executeUpdate();
    	} catch (SQLException e) {
		
			e.printStackTrace();
		}
    }
}
