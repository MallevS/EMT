package mk.ukim.finki.emtlab1.config;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.emtlab1.model.Author;
import mk.ukim.finki.emtlab1.model.Country;
import mk.ukim.finki.emtlab1.service.AuthorService;
import mk.ukim.finki.emtlab1.service.BookService;
import mk.ukim.finki.emtlab1.service.CountryService;
import org.springframework.stereotype.Component;

import static mk.ukim.finki.emtlab1.model.BookCategory.DRAMA;
import static mk.ukim.finki.emtlab1.model.BookCategory.NOVEL;

@Component
public class DataInitializer {

    private final BookService bookService;
    private final CountryService countryService;
    private final AuthorService authorService;


    public DataInitializer(BookService bookService, CountryService countryService, AuthorService authorService) {
        this.bookService = bookService;
        this.countryService = countryService;
        this.authorService = authorService;
    }
    @PostConstruct
    public void initData(){
        Country country1 = new Country(1L,"Switzerland","Europe");
        this.countryService.create(1L,"Macedonia","Europe");
        Country country2 = new Country(2L,"Sweden","Europe");
        this.countryService.create(2L,"Norway","Europe");

        Author author1 = new Author(1L,"J.K.","Rawling", country1);
        this.authorService.create(1L,"Stojan","Tarapuza", country1);
        Author author2 = new Author(2L,"Dante","Alighieri", country2);
        this.authorService.create(2L,"Doktor","Ofboli", country2);

        this.bookService.create(1L,"Book1",NOVEL,author1,3);
        this.bookService.create(2L,"Book2",DRAMA,author1,1);
        this.bookService.create(3L,"Book3",NOVEL,author1,7);
        this.bookService.create(4L,"Book4",NOVEL,author2,2);

    }
}
