package com.knf.dev.librarymanagementsystem.controller;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.knf.dev.librarymanagementsystem.entity.Author;
import com.knf.dev.librarymanagementsystem.service.AuthorService;

@Controller
public class AuthorController {

	private final AuthorService authorService;

	public AuthorController(AuthorService authorService) {
		this.authorService = authorService;
	}

	@RequestMapping("/authors")
	public String findAllAuthors(Model model, @RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) {

		var currentPage = page.orElse(1);
		var pageSize = size.orElse(5);
		var bookPage = authorService.findPaginated(PageRequest.of(currentPage - 1, pageSize));

		model.addAttribute("authors", bookPage);

		int totalPages = bookPage.getTotalPages();
		if (totalPages > 0) {
			var pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}
		return "list-authors";
	}

	@RequestMapping("/author/{id}")
	public String findAuthorById(@PathVariable("id") Long id, Model model) {

		model.addAttribute("author", authorService.findAuthorById(id));
		return "list-author";
	}

	@GetMapping("/addAuthor")
	public String showCreateForm(Author author) {
		return "add-author";
	}

	@RequestMapping("/add-author")
	public String createAuthor(Author author, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "add-author";
		}

		authorService.createAuthor(author);
		model.addAttribute("author", authorService.findAllAuthors());
		return "redirect:/authors";
	}

	@GetMapping("/updateAuthor/{id}")
	public String showUpdateForm(@PathVariable("id") Long id, Model model) {

		model.addAttribute("author", authorService.findAuthorById(id));
		return "update-author";
	}

	@RequestMapping("/update-author/{id}")
	public String updateAuthor(@PathVariable("id") Long id, Author author, BindingResult result, Model model) {
		if (result.hasErrors()) {
			author.setId(id);
			return "update-author";
		}

		authorService.updateAuthor(author);
		model.addAttribute("author", authorService.findAllAuthors());
		return "redirect:/authors";
	}

	@RequestMapping("/remove-author/{id}")
	public String deleteAuthor(@PathVariable("id") Long id, Model model) {
		authorService.deleteAuthor(id);

		model.addAttribute("author", authorService.findAllAuthors());
		return "redirect:/authors";
	}

}

// ________________________________________________

// package com.knf.dev.librarymanagementsystem.controller;

// import java.util.Optional;
// import java.util.stream.Collectors;
// import java.util.stream.IntStream;

// import org.springframework.data.domain.PageRequest;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.validation.BindingResult;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;

// import com.knf.dev.librarymanagementsystem.entity.Author;
// import com.knf.dev.librarymanagementsystem.service.AuthorService;

// @Controller // این کلاس یک کنترلر در معماری MVC است و پاسخ به درخواست‌های HTTP را مدیریت می‌کند
// public class AuthorController {

// 	private final AuthorService authorService; // تعریف یک فیلد از سرویس نویسنده برای انجام عملیات منطقی (business logic)

// 	// سازنده کلاس که با استفاده از تزریق وابستگی (Dependency Injection) مقداردهی می‌شود
// 	public AuthorController(AuthorService authorService) {
// 		this.authorService = authorService;
// 	}

// 	// متد نمایش لیست نویسنده‌ها با صفحه‌بندی
// 	@RequestMapping("/authors") // وقتی کاربر به مسیر /authors برود این متد اجرا می‌شود
// 	public String findAllAuthors(Model model, @RequestParam("page") Optional<Integer> page,
// 			@RequestParam("size") Optional<Integer> size) {

// 		var currentPage = page.orElse(1); // اگر پارامتر page در URL نبود مقدار پیش‌فرض 1 استفاده شود
// 		var pageSize = size.orElse(5); // اگر پارامتر size در URL نبود مقدار پیش‌فرض 5 استفاده شود
// 		var bookPage = authorService.findPaginated(PageRequest.of(currentPage - 1, pageSize)); // دریافت لیست نویسنده‌ها با استفاده از صفحه‌بندی

// 		model.addAttribute("authors", bookPage); // ارسال داده‌ها به ویو (list-authors.html)

// 		int totalPages = bookPage.getTotalPages(); // تعداد کل صفحات را حساب می‌کند
// 		if (totalPages > 0) {
// 			var pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList()); // تولید لیست شماره صفحات
// 			model.addAttribute("pageNumbers", pageNumbers); // ارسال لیست شماره صفحات به ویو
// 		}
// 		return "list-authors"; // بازگشت به فایل HTML برای نمایش لیست نویسنده‌ها
// 	}

// 	// نمایش اطلاعات یک نویسنده خاص بر اساس آیدی
// 	@RequestMapping("/author/{id}") // مسیر با پارامتر id مثلاً /author/3
// 	public String findAuthorById(@PathVariable("id") Long id, Model model) {
// 		model.addAttribute("author", authorService.findAuthorById(id)); // یافتن نویسنده و ارسال به ویو
// 		return "list-author"; // نمایش صفحه HTML با اطلاعات نویسنده
// 	}

// 	// نمایش فرم اضافه کردن نویسنده جدید
// 	@GetMapping("/addAuthor") // مسیر نمایش فرم خالی برای اضافه کردن نویسنده
// 	public String showCreateForm(Author author) {
// 		return "add-author"; // فایل add-author.html را نمایش بده
// 	}

// 	// ذخیره نویسنده جدید
// 	@RequestMapping("/add-author") // مسیر ارسال اطلاعات از فرم نویسنده
// 	public String createAuthor(Author author, BindingResult result, Model model) {
// 		if (result.hasErrors()) { // بررسی خطاهای فرم
// 			return "add-author"; // اگر خطا داشت، دوباره فرم را نمایش بده
// 		}

// 		authorService.createAuthor(author); // ذخیره نویسنده جدید در دیتابیس
// 		model.addAttribute("author", authorService.findAllAuthors()); // ارسال داده‌های جدید به ویو
// 		return "redirect:/authors"; // پس از ذخیره، ریدایرکت به لیست نویسنده‌ها
// 	}

// 	// نمایش فرم بروزرسانی نویسنده با اطلاعات قبلی
// 	@GetMapping("/updateAuthor/{id}") // مسیر با آیدی نویسنده برای آپدیت
// 	public String showUpdateForm(@PathVariable("id") Long id, Model model) {
// 		model.addAttribute("author", authorService.findAuthorById(id)); // دریافت نویسنده و ارسال به فرم
// 		return "update-author"; // نمایش فرم آپدیت نویسنده
// 	}

// 	// ثبت تغییرات و بروزرسانی اطلاعات نویسنده
// 	@RequestMapping("/update-author/{id}") // مسیر ثبت تغییرات
// 	public String updateAuthor(@PathVariable("id") Long id, Author author, BindingResult result, Model model) {
// 		if (result.hasErrors()) { // اگر خطا داشت
// 			author.setId(id); // آیدی را دوباره ست کن
// 			return "update-author"; // نمایش دوباره فرم
// 		}

// 		authorService.updateAuthor(author); // بروزرسانی نویسنده در دیتابیس
// 		model.addAttribute("author", authorService.findAllAuthors()); // ارسال داده‌های جدید به ویو
// 		return "redirect:/authors"; // ریدایرکت به صفحه لیست نویسنده‌ها
// 	}

// 	// حذف نویسنده
// 	@RequestMapping("/remove-author/{id}") // مسیر حذف نویسنده با استفاده از آیدی
// 	public String deleteAuthor(@PathVariable("id") Long id, Model model) {
// 		authorService.deleteAuthor(id); // حذف نویسنده از دیتابیس
// 		model.addAttribute("author", authorService.findAllAuthors()); // بروزرسانی لیست نویسنده‌ها
// 		return "redirect:/authors"; // برگشت به صفحه لیست نویسنده‌ها
// 	}
// }
