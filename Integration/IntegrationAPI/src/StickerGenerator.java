import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.rmi.server.ExportException;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Font;

import javax.imageio.ImageIO;

public class StickerGenerator {
    
    public void create(InputStream inputStream, String nameFile) throws Exception{

        // Leitura da imagem    
       // InputStream inputStream = new URL("url da api que tem a imagem").openStream();
        BufferedImage originImage = ImageIO.read(inputStream);

        // Criar imagem em memória com transparência e como novo tamanho
        int width = originImage.getWidth();
        int height = originImage.getHeight();
        int newHeight = height + 200;

        BufferedImage newImage =  new BufferedImage(width, newHeight, BufferedImage.TRANSLUCENT);

        // Copiar a imagem original para nova imagem (em memória)
        Graphics2D graphics = (Graphics2D)newImage.getGraphics();
        graphics.drawImage(originImage, 0, 0, null);

        // Vonfogurar a fonte
        var font = new Font(Font.SANS_SERIF, Font.BOLD, 64);
        graphics.setColor(Color.BLUE);
        graphics.setFont(font);

        // Escrever uma frase para nova Imagem
        graphics.drawString("TOPZERA", 100, 100);;

        // Escrever a nova imagem em um arquivo
        ImageIO.write(newImage, "png", new File(nameFile));    //fazer repositório
     }
    // public static void main(String[] args) throws Exception {
    //     var generater = new StickerGenerator();
    //     generater.create();

    // }
}
