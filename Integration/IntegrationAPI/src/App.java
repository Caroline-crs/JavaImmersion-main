import java.io.InputStream;
import java.net.URL;
import java.util.List;


public class App {

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
      
        // Fazer uma conexão HTTP e buscar os top 250 files
       String url = "uri da api";
       
       var http = new ClientHttp();
       String json = http.getData(url);
    
       
        // Exibir e manipular os dados
        var extractor = new ContentExtractorNasa();
        List<Content> contents = extractor.contentExtract(json);
        
        var generator = new StickerGenerator();

        for(int i = 0; i < 0; i++ ) {
                       
            Content content = contents.get(i);
            
            InputStream inputStream = new URL(content.getUrlImage()).openStream();
            String fileName = "saida/" + content.getTitle() + ".png";
       
            generator.create(inputStream, fileName);

            System.out.println(content.getTitle());
            System.out.println();
        } 
    }
}
