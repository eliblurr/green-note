package Application;

import client.Client;
import order.Order;
import portofolio.Portofolio;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		
		// Start the RESTFul services
		SpringApplication.run(Application.class, args);
		
		// Example client code to make use of the RESTFul services
		WebClient webClient = WebClient.builder().baseUrl("http://localhost:8080").defaultHeader("Content-Type", "application/json").build();

		Order order1 = new Order(1, "IBM",1.05,300);
		Order order2 = new Order(2,"TESL",1.00,500);
		Order order3 = new Order(3,"GOOGL",1.90,200);

		Portofolio portofolio1 = new Portofolio("portofolio1");
		portofolio1.addTrade(order1);
		ArrayList<Portofolio> portList = new ArrayList<>();
		portList.add(portofolio1);

		Portofolio portofolio2 = new Portofolio("portofolio2");
		portofolio2.addTrade(order2);
		portofolio2.addTrade(order3);

		Client client1 = new Client(22, "SELORM","Rwanda", portList.toString());
		Client client2 = new Client(23, "ELVIS","Ghana");
		Client client3 = new Client(24, "FELIX","France");

		client2.addPortofolio(portofolio2);

		// Call the POST methods
		webClient.post().uri("/LOGIN").body(Mono.just(client1), Client.class).retrieve().bodyToFlux(Client.class).subscribe(
			confirmation-> {System.out.println("Transfer Object Stored!");},
			error->System.out.println(error)
		);
		webClient.post().uri("/LOGIN").body(Mono.just(client2), Client.class).retrieve().bodyToFlux(Client.class).subscribe(
				confirmation-> {System.out.println("Transfer Object Stored!");},
				error->System.out.println(error)
			);
		webClient.post().uri("/LOGIN").body(Mono.just(client3), Client.class).retrieve().bodyToFlux(Client.class).subscribe(
				confirmation-> {System.out.println("Transfer Object Stored!");},
				error->System.out.println(error)
		);

		/* Sleep a little, as the above are async, so if we don't give enough time
		 * there will be nothing to retrieve. 
		 */
		try {
			Thread.sleep(3000);
		} catch (Exception e) {
			
		}
		
		// Now we should be able to request the data from the server...
		
		// Call the GET methods
		Flux<Client> returnedList =
				webClient.get().uri("/LOGIN").accept(MediaType.APPLICATION_JSON).retrieve().bodyToFlux(Client.class);
		
		returnedList.toStream().forEach((Client object)-> {
			System.out.println(object);
		});


	}


}
