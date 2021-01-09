public class index {
    static String url;
    static String path;
    static boolean soloTomo;
    static boolean allInOne;
    //URL dependencies
    static String pageForm;
    static int chap;
    static int firstChap;
    static int page;
    static String comicName;
    static String utilUrl;
    //Interactions
    static boolean works=true;
    static boolean end=true;


    public static void main(String[] args) { new MyFrame(); }


    public static void ComicWebToPDF(){
        MyFrame.fail.setIcon(null);
        end=false;
        if (url.contains("readcomicsonline.ru")){
            ReadComicsOnlineRU.complete();
        }
        else{ works=false; }

        if (works) {
            MyFrame.fail.setIcon(null);
            SaveImg.saveImgChap();
            if (allInOne) {
                ImgToPDF.allToPDF(path);
            } else {
                ImgToPDF.chapToPDF(path);
            }

            TrashBin.deleteTrash();

            MyFrame.fail.setIcon(MyFrame.successIcon);

        }else{ MyFrame.fail.setIcon(MyFrame.invalidIcon); }

        MyFrame.textField.setText("");
        end=true;
    }
























    public static void TestDebugging(){
        if (url.contains("readcomicsonline.ru")){
            ReadComicsOnlineRU.complete();
            System.out.println("sis");
        }

        System.out.println("url: "+url);
        System.out.println("path: "+path);
        System.out.println("soloTomo: "+soloTomo);
        System.out.println("allInOne: "+allInOne);
        System.out.println("chap: "+chap);
        System.out.println("firstChap: "+firstChap);
        System.out.println("page: "+page);
        System.out.println("comicName: "+comicName);
        System.out.println("utilUrl: "+utilUrl);



        SaveImg.saveImgChap();
        System.out.println("Save IMG complete");
        if (allInOne){ ImgToPDF.allToPDF(path);}
        else{ ImgToPDF.chapToPDF(path);}
        System.out.println("To pdf complete");

        TrashBin.deleteTrash();
        System.out.println("fin xd");
    }



}
