package objects;

public class ComicFactory {

    public static ComicWeb createComicWeb(String url){
        ComicWeb comic = null;
        if (url.contains("readcomicsonline.ru")){
            comic = new ReadComicsOnlineRU(url);
        }
        return comic;
    }

}
