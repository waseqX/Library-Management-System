package com.knf.dev.librarymanagementsystem.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "authors")
public class Author {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "name", length = 100, nullable = false, unique = true)
	private String name;

	@Column(name = "description", length = 250, nullable = false)
	private String description;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REMOVE }, mappedBy = "authors")
	private Set<Book> books = new HashSet<Book>();

	public Author(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Set<Book> getBooks() {
		return books;
	}

	public void setBooks(Set<Book> books) {
		this.books = books;
	}

	public Author() {
		super();
	}

}


// ___________________________________________

// package com.knf.dev.librarymanagementsystem.entity; // پکیجی که این کلاس داخل آن قرار دارد

// import java.util.HashSet; // کلاس HashSet برای نگهداری لیست کتاب‌ها
// import java.util.Set;     // Set یک نوع Collection برای لیست یکتا

// import javax.persistence.*; // همه انوتیشن‌های مربوط به JPA

// @Entity // مشخص می‌کند که این کلاس یک موجودیت (Entity) در دیتابیس است
// @Table(name = "authors") // مشخص می‌کند که جدول دیتابیس با نام authors ساخته شود
// public class Author {

// 	@Id // مشخص می‌کند که فیلد id کلید اصلی (Primary Key) است
// 	@GeneratedValue(strategy = GenerationType.AUTO) // مقدار id به‌صورت خودکار توسط دیتابیس تولید می‌شود
// 	private Long id; // شناسه یکتا برای هر نویسنده

// 	@Column(name = "name", length = 100, nullable = false, unique = true) 
// 	// این ستون با نام name در دیتابیس ذخیره می‌شود، نباید خالی باشد و باید یکتا باشد، حداکثر 100 حرف
// 	private String name; // نام نویسنده

// 	@Column(name = "description", length = 250, nullable = false)
// 	// این ستون توضیحی برای نویسنده است، حداکثر 250 حرف، نباید خالی باشد
// 	private String description; // توضیح درباره نویسنده

// 	@ManyToMany(
// 		fetch = FetchType.LAZY, // فقط وقتی نیاز باشد کتاب‌ها از دیتابیس بارگذاری می‌شوند
// 		cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE }, // عملیات‌هایی که روی کتاب‌ها اعمال می‌شوند
// 		mappedBy = "authors" // مشخص می‌کند که مالک رابطه در کلاس Book است
// 	)
// 	private Set<Book> books = new HashSet<Book>(); // مجموعه‌ای از کتاب‌هایی که نویسنده نوشته است

// 	// سازنده با پارامتر برای ساختن نویسنده با نام و توضیح
// 	public Author(String name, String description) {
// 		this.name = name;
// 		this.description = description;
// 	}

// 	// getter برای id
// 	public Long getId() {
// 		return id;
// 	}

// 	// setter برای id
// 	public void setId(Long id) {
// 		this.id = id;
// 	}

// 	// getter برای name
// 	public String getName() {
// 		return name;
// 	}

// 	// setter برای name
// 	public void setName(String name) {
// 		this.name = name;
// 	}

// 	// getter برای description
// 	public String getDescription() {
// 		return description;
// 	}

// 	// setter برای description
// 	public void setDescription(String description) {
// 		this.description = description;
// 	}

// 	// getter برای لیست کتاب‌ها
// 	public Set<Book> getBooks() {
// 		return books;
// 	}

// 	// setter برای تنظیم لیست کتاب‌ها
// 	public void setBooks(Set<Book> books) {
// 		this.books = books;
// 	}

// 	// سازنده پیش‌فرض (برای JPA و ابزارهای دیگر لازم است)
// 	public Author() {
// 		super(); // فراخوانی سازنده کلاس پدر (اختیاری است)
// 	}
// }
