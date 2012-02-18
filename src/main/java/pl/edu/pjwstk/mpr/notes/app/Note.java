package pl.edu.pjwstk.mpr.notes.app;

import java.sql.Date;

public class Note {
	private String content;
	private int id, thread_id;
	private Date created_at;
	
	public Note(String c, int i, int ii, Date cra){
		content = c;
		id = i;
		thread_id = ii;
		created_at = cra;
	}
	public Date getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Date createdAt) {
		created_at = createdAt;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getThread_id() {
		return thread_id;
	}
	public void setThread_id(int threadId) {
		thread_id = threadId;
	}
	public Note(String c, int i){
		content = c;
		id = i;
	}
	public Note(String c){
		content = c;
	}
}
