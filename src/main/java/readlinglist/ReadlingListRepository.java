package readlinglist;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReadlingListRepository extends JpaRepository<Book, Long> {

	List<Book> findByReader(String reader);
	
}
