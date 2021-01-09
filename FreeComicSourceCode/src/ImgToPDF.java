import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

public class ImgToPDF {

    public static void chapToPDF( String download) {
        for (int i =index.firstChap; i <index.chap-1; i++) {
            Document document = new Document(PageSize.A1);
            try {
                PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(download+"\\"+index.comicName+i+".pdf"));
                document.open();
                int j = 0;
                String path = "chap" + i + "\\";
                while (true) {
                    j++;
                    try {
                        Image image = Image.getInstance(path + j + ".jpg");
                        image.setAlignment(Element.ALIGN_CENTER);

                        image.scaleToFit(1684,2320);
                        document.add(Image.getInstance(image));

                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                        break;
                    } catch (IOException e) {
                        e.printStackTrace();
                        break;
                    }
                }
                document.close();
                writer.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (DocumentException e) {
                e.printStackTrace();
            }
        }
    }


    public static void allToPDF( String download) {
            Document document = new Document(PageSize.A1);
            try {
                PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(download + "\\" + index.comicName + ".pdf"));
                document.open();
                int j = 0;
                int i= index.firstChap;
                while (i<index.chap-1) {
                    String path = "chap" + i + "\\";
                    j++;
                    try {
                        Image image = Image.getInstance(path + j + ".jpg");
                        image.setAlignment(Element.ALIGN_CENTER);

                        image.scaleToFit(1684,2320);
                        document.add(Image.getInstance(image));

                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                        i++;
                        j=0;
                    } catch (IOException e) {
                        e.printStackTrace();
                        i++;
                        j=0;
                    }
                }

                document.close();
                writer.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (DocumentException e) {
                e.printStackTrace();
            }

    }
}
