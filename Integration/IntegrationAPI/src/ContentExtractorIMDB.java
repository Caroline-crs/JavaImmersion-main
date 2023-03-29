import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ContentExtractorIMDB implements ContentExtractor {

    public List<Content> contentExtract (String json){
        
        // Pegar somente os dados que nos interessam (titulo, poster, classificação)
        var parser = new JsonParser();
        List<Map<String, String>> atributesList = parser.parse(json);
        
        List<Content> contents = new ArrayList<>();

        //popular a lista de conteudos

        for(Map<String, String> atributes : atributesList){
          
        String title = atributes.get(Key: "title");
        String urlImage = atributes.get(Key: "url");

         var content = new Content(title, urlImage);

         contents.add(content);

      }
      return contents;
    }
}
