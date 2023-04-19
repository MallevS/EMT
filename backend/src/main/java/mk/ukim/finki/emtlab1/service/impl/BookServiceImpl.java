package mk.ukim.finki.emtlab1.service.impl;

import jakarta.transaction.Transactional;
import mk.ukim.finki.emtlab1.model.Author;
import mk.ukim.finki.emtlab1.model.Book;
import mk.ukim.finki.emtlab1.model.BookCategory;
import mk.ukim.finki.emtlab1.model.dto.BookDto;
import mk.ukim.finki.emtlab1.model.exceptions.AuthorNotFound;
import mk.ukim.finki.emtlab1.model.exceptions.BookNotFound;
import mk.ukim.finki.emtlab1.repository.AuthorRepository;
import mk.ukim.finki.emtlab1.repository.BookRepository;
import mk.ukim.finki.emtlab1.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Book> findAll() {
        return this.bookRepository.findAll();
    }

    @Override
    public Book create(Long id, String name, BookCategory category, Author author, Integer availableCopies) {
        Book book= new Book(id,name,category,author,availableCopies);
        return this.bookRepository.save(book);
    }

    @Override
    public Optional<Book> findById(Long id) {
        return this.bookRepository.findById(id);
    }
    @Override
    public Optional<Book> findByName(String name) {
        return this.bookRepository.findByName(name);
    }


    @Override
    @Transactional
    public Optional<Book> save(String name,BookCategory category,Long authorId,Integer availableCopies) {
        Author author = this.authorRepository.findById(authorId).orElseThrow(()-> new AuthorNotFound(authorId));
        Book book = new Book(name,category,author,availableCopies);
        this.bookRepository.save(book);
        return Optional.of(book);
    }

    @Override
    public Optional<Book> save(BookDto bookDto) {
        Author author = bookDto.getAuthor();
        Book book = new Book(bookDto.getName(), bookDto.getCategory(), author,bookDto.getAvailableCopies());
        this.bookRepository.save(book);
        return Optional.of(book);
    }


    @Override
    @Transactional
    public Optional<Book> edit(Long id,String name,BookCategory category,Author author,Integer availableCopies) {
        Book book = this.bookRepository.findById(id).orElseThrow(() -> new BookNotFound(id));
        book.setName(name);
        book.setCategory(category);
        book.setAuthor(author);
        book.setAvailableCopies(availableCopies);
        this.bookRepository.save(book);
        return Optional.of(book);
    }

    @Override
    public Optional<Book> edit(Long id, BookDto bookDto) {
        Book book = this.bookRepository.findById(id).orElseThrow(() -> new BookNotFound(id));

        book.setName(bookDto.getName());
        book.setCategory(bookDto.getCategory());
        book.setAuthor(bookDto.getAuthor());
        book.setAvailableCopies(bookDto.getAvailableCopies());

        this.bookRepository.save(book);
        return Optional.of(book);
    }

    @Override
    public void deleteById(Long id) {
        this.bookRepository.deleteById(id);
    }

    @Override
    public void markBookAsTaken(Long id) {
        Book b=bookRepository.findById(id).orElseThrow(()->new BookNotFound(id));
        if(b.getAvailableCopies() != 0){
            b.setAvailableCopies(b.getAvailableCopies() - 1);
        }
        bookRepository.save(b);
    }
}
