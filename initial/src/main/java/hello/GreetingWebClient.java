package hello;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class GreetingWebClient {
    private WebClient client = WebClient.create("http://localhost:8080");

    private Mono<ClientResponse> result = client.get()
            .uri("/hello")
            .accept(MediaType.TEXT_PLAIN)
            .exchange();

    /**
     * The WebClient uses reactive features, in the form of a Mono to hold the content of the URI we specify and a function
     * (in the getResult method) to turn that content into a string. If we had different requirements,
     * we might turn it into something other than a string. Since we’re going to put the result into System.out, a string will do here.
     * @return
     */
    public String getResult() {
        return ">> result = " + result.flatMap(res -> res.bodyToMono(String.class)).block();
    }
}
