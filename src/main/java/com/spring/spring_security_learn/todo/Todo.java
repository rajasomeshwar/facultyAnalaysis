package com.spring.spring_security_learn.todo;

import java.time.LocalDate;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;


@Entity(name="Todo")//table name
public class Todo {
	@Id
	@GeneratedValue
  private int id;
	@Column(name="username")//table columna nmae
   private String username;
   @Size(min=10, message="Aleast 10 characters")
   private String description;
   private LocalDate targetdate;
   private boolean done;
public Todo(int id, String username, String description, LocalDate targetdate, boolean done) {
	super();
//	this.id = id;
	this.username = username;
	this.description = description;
	this.targetdate = targetdate;
	this.done = done;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public LocalDate getTargetdate() {
	return targetdate;
}
public void setTargetdate(LocalDate targetdate) {
	this.targetdate = targetdate;
}
public boolean isDone() {
	return done;
}
public void setDone(boolean done) {
	this.done = done;
}
 public Todo()
 {
	 
 }
   public String toString()
   {
	    return id+" "+username+" "+description+" "+targetdate+" "+done;
   }
}
