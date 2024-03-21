package com.zolobooky.booky.books;

import com.zolobooky.booky.appeals.AppealEntity;
import com.zolobooky.booky.appeals.AppealRepository;
import com.zolobooky.booky.books.BookExceptions.BadRequestException;
import com.zolobooky.booky.books.BookExceptions.BookAlreadyExistsException;
import com.zolobooky.booky.books.BookExceptions.BookNotFoundException;
import com.zolobooky.booky.books.dto.CreateBookDTO;
import com.zolobooky.booky.books.dto.UpdateBookDTO;
import com.zolobooky.booky.commons.CustomStatus;
import com.zolobooky.booky.commons.CustomStatus.BookStatus;
import com.zolobooky.booky.notifications.FireService;
import com.zolobooky.booky.users.UserEntity;
import com.zolobooky.booky.users.UserRepository;
import com.zolobooky.booky.users.UserService;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BookService {

	private final BookRepository bookRepository;

	private final UserService userService;

	private final FireService fireService;

	private final UserRepository userRepository;

	private final AppealRepository appealRepository;

	public BookService(BookRepository bookRepository, UserService userService, FireService fireService,
			AppealRepository appealRepository, UserRepository userRepository) {
		this.bookRepository = bookRepository;
		this.userService = userService;
		this.fireService = fireService;
		this.userRepository = userRepository;
		this.appealRepository = appealRepository;
	}

	public Page<BookEntity> getBooks(Integer page, Integer size) {
		log.info(String.format(" %s books from page: %s fetched from the database.", size, page));
		return this.bookRepository.findAll(PageRequest.of(page, size));
	}

	public BookEntity getBookById(Integer id) {
		BookEntity book;
		if (this.bookRepository.findById(id).isEmpty()) {
			log.info(String.format("Book with Book ID: %s Not Found", id));
			throw new BookNotFoundException(String.format("Book with Book ID: %s Not Found", id));

		}
		else {
			log.info(String.format("book with book id: %d fetched from database.", id));
			book = this.bookRepository.findById(id).get();
		}

		return book;
	}

	public BookEntity createBook(CreateBookDTO createBookDTO) {

		List<BookEntity> books = this.bookRepository.findByStatusOrderByName(BookStatus.AVAILABLE);
		BookEntity newBookToSave = new BookEntity(createBookDTO);
		newBookToSave.setOwner(userService.getUser(createBookDTO.getOwner()));

		if (newBookToSave.getName() == null || newBookToSave.getAvailability() == null
				|| newBookToSave.getOwner() == null) {
			StringBuilder whichNull = new StringBuilder();
			if (newBookToSave.getName() == null) {
				whichNull.append("name : <book_name>, ");
			}

			if (newBookToSave.getAuthor() == null) {
				whichNull.append("author : <author_name>, ");
			}

			if (newBookToSave.getAvailability() == null) {
				whichNull.append("availability : <book_availability>, ");
			}
			if (newBookToSave.getOwner() == null) {
				whichNull.append("owner : <book_owner>");
			}

			log.info(String.format("Found unexpected null value(s) : %s", whichNull));
			throw new BadRequestException(String.format("Found unexpected null value(s) : %s", whichNull));
		}

		for (var book : books) {
			if (book.getName().equals(newBookToSave.getName()) && book.getOwner().equals(newBookToSave.getOwner())) {
				log.info(String.format("book with NAME: %s already exists with ID: %d and is available to borrow.",
						book.getName(), book.getId()));

				throw new BookAlreadyExistsException(
						String.format("book with NAME: %s already exists with ID: %d and is available to borrow.",
								book.getName(), book.getId()));
			}
		}

		log.info(String.format("Book with book name: %s Created.", newBookToSave.getName()));

		List<UserEntity> users = this.userRepository.findAll();

		for (UserEntity user : users) {
			if (user.getName() != newBookToSave.getOwner().getName()) {
				this.fireService.sendNotification(user.getFcmToken(), "New Book Alert!!",
						String.format("New Book %s has been added by %s ", newBookToSave.getName(),
								newBookToSave.getOwner().getName()));
			}

		}

		return this.bookRepository.save(newBookToSave);
	}

	public BookEntity deleteBook(Integer id) {
		BookEntity book;
		if (this.bookRepository.findById(id).isEmpty()) {
			throw new BookNotFoundException(String.format("book to delete with book id: %s not found", id));
		}
		else {
			book = this.bookRepository.findById(id).get();
			if (book.getStatus().equals(BookStatus.DELISTED)) {
				throw new BookAlreadyExistsException(String.format("book with book id: %d is already delisted ", id));
			}
			book.setStatus(BookStatus.DELISTED);
			log.info(String.format("book with id: %d Delisted", id));

			List<AppealEntity> appealsOfBook = this.appealRepository.findAll();

			for (AppealEntity appeal : appealsOfBook) {
				if (appeal.getBook_id().equals(book)) {
					this.fireService.sendNotification(appeal.getBorrower_id().getFcmToken(),
							String.format("Book %s delisted.", book.getName()), "Owner has delisted the book.");
				}
			}
		}

		return this.bookRepository.save(book);

	}

	public BookEntity updateBook(UpdateBookDTO updateBookDTO, Integer id) {
		Optional<BookEntity> book = this.bookRepository.findById(id);

		if (book.isEmpty()) {
			log.info(String.format("book to update with book id: %s not found", id));
			throw new BookNotFoundException(String.format("book to update with book id: %s not found", id));
		}

		BookEntity bookToUpdate = book.get();

		if (updateBookDTO.getName() != null) {
			bookToUpdate.setName(updateBookDTO.getName());
		}

		if (updateBookDTO.getAuthor() != null) {
			bookToUpdate.setAuthor(updateBookDTO.getAuthor());
		}

		if (updateBookDTO.getDescription() != null) {
			bookToUpdate.setDescription(updateBookDTO.getDescription());
		}

		if (updateBookDTO.getAvailability() != null) {
			bookToUpdate.setAvailability(updateBookDTO.getAvailability());
		}

		if (updateBookDTO.getThumbnail() != null) {
			bookToUpdate.setThumbnail(updateBookDTO.getThumbnail());
		}

		log.info(String.format("book with book id: %d updated successfully", id));

		return this.bookRepository.save(bookToUpdate);
	}

	public BookEntity updateStatus(Integer book_id, CustomStatus.BookStatus newStatus) {
		BookEntity book = getBookById(book_id);
		if (book == null) {
			throw new BookNotFoundException("Book with ID" + book_id + " does not exist");
		}
		book.setStatus(newStatus);
		return this.bookRepository.save(book);
	}

}
