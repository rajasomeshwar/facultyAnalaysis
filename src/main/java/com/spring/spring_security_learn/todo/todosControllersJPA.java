package com.spring.spring_security_learn.todo;

 

import java.util.List;
import java.util.function.Predicate;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
public class todosControllersJPA {
	//private TodoServer todos;
	private TodoRepository repsoitory;
	public todosControllersJPA(TodoRepository repsoitory) {
		super();
		
		this.repsoitory=repsoitory;
	}

   @GetMapping("/users/{username}/todos")
   public List<Todo> GettodosByuserName(@PathVariable String username)
   {
	   System.out.println(" x   ");
	   return repsoitory.findByUsername(username);
   }
	@GetMapping("/users/{username}/todos/{id}")
	@PreAuthorize("hasAuthority('ROLE_USER') and #username == principal.claims['sub']")
	public Todo GetTodosByuserNameWithid(@PathVariable String username,@PathVariable int id) {
		var list= repsoitory.findByUsername(username);
		Predicate< ? super Todo> findByID= (v)->v.getId()==id;
	 return	list.stream().filter(findByID).findAny().orElse(null);
		}
	//@PostAuthorize("returnObject.username='nmae'")
	@DeleteMapping("/users/{username}/todos/{id}")
	public  ResponseEntity<Void> DeleteByuserNameWithid(@PathVariable String username,@PathVariable int id) {
		var list= repsoitory.findByUsername(username);
		Predicate< ? super Todo> findByID= (v)->v.getId()==id;
	  var todo=list.stream().filter(findByID).findAny().orElse(null);
	  if(todo!=null)
		  repsoitory.delete(todo);
	  return ResponseEntity.noContent().build();
	  
		}
	@PostMapping("/users/{username}/todos")
	   public Todo CreatetodosByuserName(@PathVariable String username,@Valid @RequestBody Todo todo)
	   {
		   repsoitory.save(todo);
		   return todo;
	   }
	@PutMapping("/users/{username}/todos/{id}")
	   public Todo GettodosByuserName(@PathVariable String username,@PathVariable int id,@Valid @RequestBody Todo todo)
	   {
		
	 return	repsoitory.save(todo);
	   }
}
