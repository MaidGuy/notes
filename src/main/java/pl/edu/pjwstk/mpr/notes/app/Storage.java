package pl.edu.pjwstk.mpr.notes.app;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
public class Storage extends Database{
	public Storage(){
		super("localhost", "notes", "root", "root");
		connect();
	}
	
	
	public ArrayList<Category> getCategories(){
		ArrayList<Category> cs = new ArrayList<Category>();
		try {
			PreparedStatement ps = c.prepareStatement("SELECT name, description, id FROM category");
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Category ct = new Category(rs.getString("name"), rs.getString("description"), rs.getInt("id"));
				cs.add(ct);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cs;
	}
	
	public ArrayList<Thread> getThreadsFor(int ix){
		ArrayList<Thread> ie = new ArrayList<Thread>();
		try {
			PreparedStatement ps = c.prepareStatement("SELECT name, id FROM thread WHERE category_id = ?");
			ps.setInt(1, ix);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Thread j = new Thread(rs.getString("name"), rs.getInt("id"), ix);
				ie.add(j);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return ie;
	}
	
	public void addCategory(String name, String description){
		try {
			PreparedStatement ps = c.prepareStatement("INSERT INTO category (name, description) VALUES (?,?)");
			ps.setString(1, name);
			ps.setString(2, description);
			ps.executeUpdate();
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
	}
	public void addThread(String name, int cat) throws MySQLIntegrityConstraintViolationException{
		try {
			PreparedStatement ps = c.prepareStatement("INSERT INTO thread (name, category_id) VALUES (?,?)");
			ps.setString(1, name);
			ps.setInt(2, cat);
			ps.executeUpdate();
		}catch(MySQLIntegrityConstraintViolationException e){
			throw new MySQLIntegrityConstraintViolationException();
		}catch (SQLException e) {
		
			e.printStackTrace();
		}
	}
	public void deleteCategory(int i){
		try {
			PreparedStatement ps = c.prepareStatement("DELETE FROM category WHERE id = ?");
			ps.setInt(1, i);
			ps.executeUpdate();
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
	}
	public void deleteThread(int i){
		try {
			PreparedStatement ps = c.prepareStatement("DELETE FROM thread WHERE id = ?");
			ps.setInt(1, i);
			ps.executeUpdate();
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
	}
	public void addNoteFor(String content, int thread_id){
		try {
			PreparedStatement ps = c.prepareStatement("INSERT INTO note (content, thread_id) VALUES (?,?)");
			ps.setString(1, content);
			ps.setInt(2, thread_id);
			ps.executeUpdate();
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
	}
	
	
	public ArrayList<Note> getNotesFor(int thread_id){
		ArrayList<Note> ie = new ArrayList<Note>();
		try {
			PreparedStatement ps = c.prepareStatement("SELECT content, id, created_at FROM note WHERE thread_id = ?");
			ps.setInt(1, thread_id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Note p = new Note(rs.getString("content"), rs.getInt("id"), thread_id, rs.getDate("created_at"));
				ie.add(p);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return ie;
	}
	
	
	
}
