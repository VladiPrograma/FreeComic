package objects;


public class  ComicWeb {
    enum ComicType {CHAPTER, BOOK, GRAPHIC_NOVEL}

    public String imageURL;
    public String name;
    public ComicType type;
    public int chapter;
    public int page;


    public  ComicWeb(){ }
    public void nextComic(){}
    public void nextPage(){}
    public String pageFormat(){
        if (page < 10) { return "0" + page; }
        return Integer.toString(page);
    }
    public String getDownloadPageURL(){ return ""; }
}

