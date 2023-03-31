package App;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
		
	public static final String ANSI_CYAN = "\u001B[36;4m"; 
	public static final String ANSI_WHITE = "\u001B[30;1m"; 
	public static final String ANSI_YELLOW = "\u001B[33m"; 
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String STAR = ANSI_YELLOW + "★" + ANSI_RESET;
	public static final String STAR_EMPTY = ANSI_WHITE + "☆" + ANSI_RESET;
	
		
	public static void main(String[] args) throws Exception {
		
		//connection
		String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
		URI endereco = URI.create(url);
		var client = HttpClient.newHttpClient();		
		var request = HttpRequest.newBuilder(endereco).GET().build();		
		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
		String body = response.body();		
		System.out.println(body);
		
		//extracting data
		var parser = new JsonParser();
		List<Map<String, String>> listaDeFilmes = parser.parse(body);
		
		//showing data
		for (Map<String, String> filme : listaDeFilmes) {
		
			String urlImage = filme.get("image");
			String title = filme.get("title");
			String fileName = title + ".png";
			
			InputStream inputStream = new URL(urlImage).openStream();
								
			var gen = new stickerGenerator();
			gen.generate(inputStream, fileName);
			
			System.out.println(ANSI_CYAN +(filme.get("title").toUpperCase()) + ANSI_RESET);
			// System.out.println("Poster: " + filme.get("image"));
			System.out.println("Classificação: " + ANSI_YELLOW + filme.get("imDbRating") + ANSI_RESET);
			System.out.println("Posição: " + ANSI_YELLOW + filme.get("rank") + ANSI_RESET);
			
			var rating = (filme.get("imDbRating"));
			
			if(Double.parseDouble(rating) >= 8 
					&& Double.parseDouble(rating) < 9) {
				System.out.println(STAR + STAR + STAR + STAR + STAR_EMPTY );
			}
			
			if(Double.parseDouble(rating) >= 9 
					&& Double.parseDouble(rating) <= 10) {
				System.out.println(STAR + STAR + STAR + STAR + STAR );
			}

			System.out.println();
		}
	}

}
