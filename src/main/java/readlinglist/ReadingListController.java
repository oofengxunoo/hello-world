package readlinglist;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class ReadingListController {

	@Autowired
	private ReadlingListRepository readlingListRepository;
	
	@RequestMapping(value = "/{reader}", method = RequestMethod.GET)
	public String readersBooks(
			@PathVariable("reader") String reader, Model model){
		List<Book> readingList = readlingListRepository.findByReader(reader);
		if(readingList!=null){
			model.addAttribute("books", readingList);
		}
		return "readingList";
	}
	
	@RequestMapping(value = "/{reader}", method = RequestMethod.POST)
	public String addToReadingList(@PathVariable("reader") String reader, Book book) {
		book.setReader(reader);
		readlingListRepository.save(book);
		return "redirect:/{reader}";
	}
	
}
