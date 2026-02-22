package br.com.swetonyancelmo.repository;

import br.com.swetonyancelmo.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
