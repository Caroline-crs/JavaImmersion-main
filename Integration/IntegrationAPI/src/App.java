import java.io.InputStream;
import java.net.URI;
import java.net.URL;
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
   
    /**
     * @param args
     * @throws Exception
     */
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
        List<Map<String, String>> movieList = parser.parse(body);
       
        // Exibir e manipular os dados
        var generator = new StickerGenerator();

        for(Map<String, String> movie : movieList) {
           
            String urlImage = movie.get(key: "image");
            String title = movie.get(key: "title");
            
            InputStream inputStream = new URL(urlImage).openStream();
            String nameFile = title + ".png";
       
            generator.create(inputStream, nameFile);

            System.out.println(title);
            System.out.println(movie.get(key: "image"));
            System.out.println(movie.get(key: "imDbRating"));
          
            System.out.println();
        } 
    }
}
