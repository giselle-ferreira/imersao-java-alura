package App;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class stickerGenerator {

	void generate(InputStream inputStream, String newFile ) throws Exception {
		
		// image reading
		// InputStream inputStream = new FileInputStream(new File("entrada/filme.jpg"));
		// InputStream inputStream = new URL("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies_1.jpg").openStream();
		BufferedImage originalImage = ImageIO.read(inputStream);
		
		// creates new image with transparency and new size
		int originWidth = originalImage.getWidth();
		int originHeight = originalImage.getHeight();
		int newHeight = originHeight + 200;	
		BufferedImage newImage = new BufferedImage(originWidth, newHeight, BufferedImage.TRANSLUCENT);
		
		// copies original image to new image
		Graphics2D graphics = (Graphics2D) newImage.getGraphics();
		graphics.drawImage(originalImage, 0, 0, null);
						
		// configuring font
		Font fonte = new Font(Font.SANS_SERIF, Font.BOLD, 90);
		graphics.setColor(Color.YELLOW);
		graphics.setFont(fonte);
		
		// writes a sentence on the new image
		graphics.drawString("TOPPERSSON", 30, newHeight - 70);
		
		// writes new image in a file
		ImageIO.write(newImage, "png", new File(newFile));
	}
	
//	public static void main(String[] args) throws Exception {
//		var gen = new stickerGenerator();
//		gen.generate();
//		System.out.println("Sticker criado com sucesso.");
//	}
}
