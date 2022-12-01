package Application;

import java.util.ArrayList;
import java.util.List;

import client.Client;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class RESTEndpoint {
	
	// Build a repo and load some fake data into it.
	private List<Client> repository = new ArrayList<>();


	@CrossOrigin
	@RequestMapping(method = {RequestMethod.POST}, path="/LOGIN")
	public @ResponseBody List<Client> examplePOSTEndpoint(@RequestBody Client sentIn) {
		
		System.out.println("POST /LOGIN received with object: " + sentIn);
		this.repository.add(sentIn);
		
		return repository;
	}
	
	@CrossOrigin
	@RequestMapping(method = {RequestMethod.GET}, path="/LOGIN")
//	@GetMapping("/example")
	public @ResponseBody List<Client> exampleGETEndpoint() {
		System.out.println("here is my data: ");
		System.out.println();
		System.out.println("GET /LOGIN requested, sending list: " + this.repository);
		return this.repository;
	}


}
