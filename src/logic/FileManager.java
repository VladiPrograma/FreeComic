package logic;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import objects.ComicWeb;

import javax.swing.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import static screen.HomeScreen.waitAdvise;

public class FileManager {


   

    public static void downloadChapterImages(ComicWeb comic,  JLabel label, JFrame frame) {

      String input = comic.getDownloadPageURL();
      System.out.println(input);

      while (WebExists(input)) {
          File directorio = new File("chapter" + comic.chapter) ;
          if(!directorio.exists()){ directorio.mkdirs(); }
          System.out.println(input);

          try {
              // Url con la foto
              /** SCREEN INTERACTION */
              waitAdvise(label, frame, "Downloading img Nº "+comic.page);

              URL site = new URL(input);

              // establecemos conexion
              URLConnection urlCon = site.openConnection();
              urlCon.addRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");

              // Se obtiene el inputStream de la foto web y se abre el fichero
              InputStream is = urlCon.getInputStream();
              FileOutputStream fos = new FileOutputStream("chapter" + comic.chapter + "\\" + comic.page + ".jpg");

              // Lectura de la foto de la web y escritura en fichero local
              byte[] array = new byte[1000]; // buffer temporal de lectura.
              int leido = is.read(array);
              while (leido > 0) {
                  fos.write(array, 0, leido);
                  leido = is.read(array);
              }

              // cierre de conexion y fichero.
              is.close();
              fos.close();


              comic.nextPage();
              input = comic.getDownloadPageURL();

          } catch (IOException e) {
              System.out.println("Exception controlled: No more images have been found ");
              break;
          }
      }
    }

    public static boolean WebExists(String url){
        try {
            URL testURL = new URL(url);
            HttpURLConnection huc = (HttpURLConnection) testURL.openConnection();
            int responseCode = huc.getResponseCode();
            return HttpURLConnection.HTTP_OK == responseCode;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void ChapterImagesToPDF(ComicWeb comic) {
        if(! new File("chapter"+comic.chapter).exists()){
            System.out.println("No more chapters to transform in PDF");
            return;
        }
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

    public static void removeImages(int firstChapter, int lastChapter){
        for (int i=firstChapter; i<=lastChapter; i++){
            int j=0;
            String path = "chapter"+i+"\\";
            while(true){
                j++;
                File f = new File(path+j+".jpg");
                if (!f.exists()){ break; }
                f.delete();
            }
            File file = new File("chapter"+i);
            file.delete();

        }
    }

}
