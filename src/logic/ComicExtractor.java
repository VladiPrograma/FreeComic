package logic;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import objects.ComicWeb;
import objects.ReadComicsOnlineRU;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ComicExtractor {

    public static void ChapterImagesToPDF(ComicWeb comic) {
            Document document = new Document(PageSize.A1);
            try {
                PdfWriter writer = PdfWriter.getInstance(
                    document,
                    new FileOutputStream(System.getProperty("user.home") + "/Desktop"+"\\"
                    +comic.name+"_"+comic.chapter+".pdf"));

                document.open();
                int j = 0;
                String path = "chapter" + comic.chapter + "\\";
                while (true) {
                    j++;
                    try {
                        Image image = Image.getInstance(path + j + ".jpg");
                        image.setAlignment(Element.ALIGN_CENTER);

                        image.scaleToFit(1684,2320);
                        document.add(Image.getInstance(image));

                    } catch (IOException e) {
                        System.out.println("Exception controlled: No more Images to add");
                        break;
                    }
                }
                document.close();
                writer.close();
            } catch (FileNotFoundException e) {
                System.out.println("Exception controlled: No more images to add.");
            } catch ( DocumentException e){
                System.out.println("Exception controlled: PDF is ready to open.");
            }
        }
}


