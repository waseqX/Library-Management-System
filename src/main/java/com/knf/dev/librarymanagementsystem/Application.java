package com.knf.dev.librarymanagementsystem;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.knf.dev.librarymanagementsystem.entity.Author;
import com.knf.dev.librarymanagementsystem.entity.Book;
import com.knf.dev.librarymanagementsystem.entity.Category;
import com.knf.dev.librarymanagementsystem.entity.Publisher;
import com.knf.dev.librarymanagementsystem.entity.Role;
import com.knf.dev.librarymanagementsystem.entity.User;
import com.knf.dev.librarymanagementsystem.repository.UserRepository;
import com.knf.dev.librarymanagementsystem.service.BookService;

@SpringBootApplication
public class Application {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private BookService bookService;

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner initialCreate() {
		return (args) -> {

			var book = new Book("A", "Spring Boot", "A1", "Book description");
			book.addAuthors(new Author("james gossolin", " description"));
			book.addCategories(new Category("Spring Boot categary"));
			book.addPublishers(new Publisher("james publisher"));
			bookService.createBook(book);

			var book1 = new Book("B", "Java", "B1", " Book Description1");
			book1.addAuthors(new Author("Hassan", "description"));
			book1.addCategories(new Category("Java category"));
			book1.addPublishers(new Publisher("publisher2"));
			bookService.createBook(book1);

			var book2 = new Book("C", "Flutter", "C1", "Book description");
			book2.addAuthors(new Author("said Navid", " description2"));
			book2.addCategories(new Category("Flutter category"));
			book2.addPublishers(new Publisher("publisher3"));
			bookService.createBook(book2);

			var user = new User("Hassan", "Waseq", "waseq@gmail.com", passwordEncoder.encode("Hassan333"),
					Arrays.asList(new Role("ROLE_ADMIN")));
			userRepository.save(user);

		};
	}
}
