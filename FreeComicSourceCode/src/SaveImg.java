import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class SaveImg {
    
    public static String pageForm(){
        if (index.url.contains("readcomicsonline.ru")) {
            if (index.page < 10) {
                return "0" + index.page;
            }
            return "" + index.page;
        }
        return "-1";
    }

    public static void saveImgChap() {
        boolean primero=true;

        while (true) {
            if (index.page == -1) {
                break;
            }
            File directorio = new File("chap" + index.chap);
            directorio.mkdirs();

            if (!primero && index.soloTomo) { index.chap++; break; } //asi no da error al generar el PDF

            while (true) {

                try {
                    // Url con la foto
                    String input = index.utilUrl + index.chap + "/" + pageForm() + ".jpg";

                    URL site = new URL(input);

                    // establecemos conexion
                    URLConnection urlCon = site.openConnection();
                    urlCon.addRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");

                    // Se obtiene el inputStream de la foto web y se abre el fichero
                    InputStream is = urlCon.getInputStream();
                    FileOutputStream fos = new FileOutputStream("chap" + index.chap + "\\" + index.page + ".jpg");

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
                    
                    
                    index.page++;
                    
                } catch (Exception e) {
                    e.printStackTrace();

                    //MyFrame.label.setText(Integer.toString(index.chap));
                    primero=false;
                    if (index.page == 1) { index.page = -1; }
                    else {
                        index.page = 1;
                        index.chap++;
                    }
                    break;
                }
            }
        }
    }
}

