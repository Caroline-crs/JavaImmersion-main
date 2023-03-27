import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpClient.Builder;
import java.net.http.HttpResponse.BodyHandler;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

import javax.xml.transform.Source;

public class App {
   
    public static void main(String[] args) throws Exception {
      
        // Fazer uma conexão HTTP e buscar os top 250 files
        String url = "https://imdb-api/en/API/Top250Movies/(key)";
        var client  = HttpClient.newHttpClient();
        var address = URI.create(url);
        var request =  HttpRequest.newBuilder(address).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        System.out.println(body);

        // Pegar somente os dados que nos interessam (titulo, poster, classificação)
        var parser = new JsonParser();
        List<Map<String, String>> movieList = parser.parser(body);
       
        // Exibir e manipular os dados
        foreach(Map<String, String> movie : movieList){
            System.out.println(movie.get(key: "title"));
            System.out.println(movie.get(key: "image"));
            System.out.println(movie.get(key: "imDbRating"));
          
            System.out.println();
        } 
    }
}
