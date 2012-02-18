package pl.edu.pjwstk.mpr.notes.app;

import java.util.ArrayList;

public class Category {
	private String name, description;
	private int id;
	
	public Category(String n, String d, int i){
		name = n; 
		description = d;
		id = i;
	}
	
	
	
	public Category(String n, String d){
		name = n; 
		description = d;
	}
	public Category(String n){
		name = n; 
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String toString(){
		return name + " - " + description;
	}
}
