package com.crudcontacts.crudcontacts;

import java.util.ArrayList;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ContactsController {
	
	private static final ArrayList<Contact> LIST_CONTACTS = new ArrayList<>();
	
	static {
		LIST_CONTACTS.add(new Contact("1", "Lea", "15 1234-5678"));
		LIST_CONTACTS.add(new Contact("2", "Luke", "14 5678-1234"));
		LIST_CONTACTS.add(new Contact("3", "Anakin", "11 1034-5890"));
		LIST_CONTACTS.add(new Contact("4", "Chewie", "41 2038-9604"));
		LIST_CONTACTS.add(new Contact("5", "Yoda", "51 3640-8950"));
		LIST_CONTACTS.add(new Contact("6", "Han Solo", "50 9874-0097"));
	}
	
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/contacts")
	public ModelAndView list() {
		ModelAndView modelAndView = new ModelAndView("list");
		
		modelAndView.addObject("contacts", LIST_CONTACTS);
		
		return modelAndView;
	}
	
	@GetMapping("/contacts/novo")
	public ModelAndView novo() {
		ModelAndView modelAndView = new ModelAndView("form");
		
		modelAndView.addObject("contact", new Contact());
		
		return modelAndView;
	}
	
	@PostMapping("/contacts")
	public String create(Contact contact) {
		String id = UUID.randomUUID().toString();
		
		contact.setId(id);
		
		LIST_CONTACTS.add(contact);
		
		return "redirect:/contacts";
	}
	
	@GetMapping("/contacts/{id}/edit")
	public ModelAndView edit(@PathVariable String id) {
		ModelAndView modelAndView = new ModelAndView("form");
		
		Contact contact = searchContact(id);
		
		modelAndView.addObject("contact", contact);
		
		return modelAndView;
	}
	
	@PutMapping("/contacts/{id}")
	public String update(Contact contact) {
		Integer indice = searchIdContact(contact.getId());
		
		Contact contactOld = LIST_CONTACTS.get(indice);
		
		LIST_CONTACTS.remove(contactOld);
		
		LIST_CONTACTS.add(indice, contact);
		
		return "redirect:/contacts";
	}
	
	@DeleteMapping("/contacts/{id}")
	public String delete(@PathVariable String id) {
		Contact contact = searchContact(id);
		
		LIST_CONTACTS.remove(contact);
		
		return "redirect:/contacts";
	}
	
	//Métodos auxiliares
	
	private Contact searchContact(String id) {
		Integer indice = searchIdContact(id);
		
		if(indice != null) {
			Contact contact = LIST_CONTACTS.get(indice);
			return contact;
		}
		
		return null;
	}
	
	private Integer searchIdContact(String id) {
		for(int i=0; i<LIST_CONTACTS.size(); i++) {
			Contact contact = LIST_CONTACTS.get(i);
			
			if(contact.getId().equals(id)) {
				return i;
			}
		}
		return null;
	}

}
