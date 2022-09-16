package logic;

import objects.ComicWeb;

import javax.swing.*;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;

import static screen.HomeScreen.waitAdvise;

public class FileManager {

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

   

    public static void downloadChapterImages(ComicWeb comic,  JLabel label, JFrame frame) {
      File directorio = new File("chapter" + comic.chapter);
      directorio.mkdirs();
      while (true) {
          try {
              // Url con la foto
              String input = comic.imageURL;
              System.out.println(comic.imageURL);
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

          } catch (IOException e) {
              System.out.println("Exception controlled: No more images have been found ");
              break;
          }
      }
    }
}
