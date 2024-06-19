package com.spring.spring_security_learn.todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

import jakarta.validation.Valid;



public class TodoServer {
	static int top=0;
   static List<Todo> todos=new ArrayList<>();
   static {
	  todos.add(new Todo(top++,"Rajasomeshwar","Learn Aws",LocalDate.now().plusYears(1),false));
	  todos.add(new Todo(top++,"Rajasomeshwar","Learn Full Stack",LocalDate.now().plusYears(2),false));
   }
   //public Todo(int id, String username, String description, LocalDate targetdate, boolean done)
   
   public void AddTodo(int id,String user,String description,LocalDate date ,boolean done)
   {
	   todos.add(new Todo(top++,user,description,date,done));
   }
   public List<Todo> findElement()
   {
	   return todos;
   }
   public void findById(int id)
   {
	   Predicate<? super Todo> predicate=(todo)->id==todo.getId();
	   Todo temp=todos.stream().filter(predicate).findFirst().get();
	   todos.remove(temp);
	   
   }
public Todo getByid(int id) {
	// TODO Auto-generated method stub
	Predicate<? super Todo> predicate=(todo)->id==todo.getId();
	return todos.stream().filter(predicate).findFirst().get();
}
public void updateBy(@Valid Todo todo) {
	// TODO Auto-generated method stub
	deletedBy(todo.getId());
	todos.add(todo);
	
}
private void deletedBy(int id) {
	// TODO Auto-generated method stub
	todos.remove(getByid(id));
	
}
}
