package guru.springframework.spring6webapp.bootstrap;

import guru.springframework.spring6webapp.domain.Author;
import guru.springframework.spring6webapp.domain.Book;
import guru.springframework.spring6webapp.domain.Publisher;
import guru.springframework.spring6webapp.repositories.AuthorRepository;
import guru.springframework.spring6webapp.repositories.BookRepository;
import guru.springframework.spring6webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author eric = new Author();
        eric.setFirstName("Eric");
        eric.setLastName("Evans");

        Book ddd = new Book();
        ddd.setTitle("Domain driven design");
        ddd.setIsbn("123456");

        Author ericSaved = authorRepository.save(eric);
        Book dddSaved = bookRepository.save(ddd);

        Author John = new Author();
        eric.setFirstName("John");
        eric.setLastName("Jokes");

        Book effectiveJava = new Book();
        ddd.setTitle("Effective Java 2nd edition");
        ddd.setIsbn("123457");

        Author johnSaved = authorRepository.save(John);
        Book effectiveJavaSaved = bookRepository.save(effectiveJava);

        ericSaved.getBooks().add(dddSaved);
        johnSaved.getBooks().add(effectiveJavaSaved);
        dddSaved.getAuthors().add(ericSaved);
        effectiveJavaSaved.getAuthors().add(johnSaved);

        Publisher publisher = new Publisher();
        publisher.setPublisherName("Goya moya");
        publisher.setAddress("Grove street");

        Publisher savedPublisher = publisherRepository.save(publisher);
        dddSaved.setPublisher(savedPublisher);
        effectiveJavaSaved.setPublisher(savedPublisher);
        authorRepository.save(ericSaved);
        authorRepository.save(johnSaved);
        bookRepository.save(dddSaved);
        bookRepository.save(effectiveJavaSaved);

        System.out.println("In Bootstrap");
        System.out.println("Author count: " + authorRepository.count());
        System.out.println("Book count: " + bookRepository.count());
        System.out.println("Publisher: " + publisherRepository.count());
    }
}
