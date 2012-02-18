package pl.edu.pjwstk.mpr.notes.app;

public class Thread {
	private String name;
	private int id, category_id;
	
	public Thread(String n, int i, int ci){
		name = n;
		id = i;
		category_id = ci;
	}
	public Thread(String n, int i){
		name = n;
		id = i;
	
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCategory_id() {
		return category_id;
	}
	public void setCategory_id(int categoryId) {
		category_id = categoryId;
	}
	public Thread(String n){
		name = n;
	
	}
	
	public String toString(){
		return name;
	}
}
