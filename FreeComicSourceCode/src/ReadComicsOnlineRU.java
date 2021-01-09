public class ReadComicsOnlineRU {

    public static void complete(){
        try {

            index.page = page();
            index.chap = chap();
            index.utilUrl = utilUrl();
            index.comicName = comicName();
            index.firstChap = chap();
        }catch (Exception e){
            index.works = false;
        }
    }


    public static int chap(){
        String[] toChap = index.url.split("/");
        return Integer.parseInt(toChap[7]);
    }
    public static int page(){
        String[] toChap = index.url.split("/");
        String pages = toChap[8];
        pages = pages.replace(".jpg", "");
        return Integer.parseInt(pages);
    }
    public static String comicName(){
        String[] parts = index.url.split("/");
        String[]name = parts[5].split("-");
        String res="";
        for (int i = 0; i < name.length-1; i++) {
            res+=name[i];
        }
        return res;
    }

    public static String utilUrl(){
        String[] parts = index.url.split("/");
        String res="";
        for (int i=0; i<7; i++){
            res+=parts[i]+"/";
        }
        return res;
    }


}
