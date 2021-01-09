import java.io.File;

public class TrashBin {
    public static void deleteTrash(){
        for (int i=index.firstChap; i<=index.chap; i++){
            int j=0;
            String path = "chap"+i+"\\";
            while(true){
                j++;
                File f = new File(path+j+".jpg");
                if (!f.exists()){ break; }

                f.delete();

            }
            File file = new File("chap"+i);
            file.delete();

        }
    }
}
