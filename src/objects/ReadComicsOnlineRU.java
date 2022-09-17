package objects;

/**@TODO Manage the existence of "Book" links like "Clementine" */
/**@TODO Implement Factory Pattern" */

public class ReadComicsOnlineRU extends ComicWeb {
    String imageLink= "https://readcomicsonline.ru/uploads/manga/";
    public ReadComicsOnlineRU(String url){
        super(url);
        url = url.replace("#", "");

        if(url.split("/").length == 5){
            url+="/1";
        }

        if(url.split("/").length == 6){
            url+="/01";
        }

        this.name = getComicName(url);
        this.type = url.contains("chapters") ? comicType.CHAPTER : comicType.BOOK;
        this.chapter = getChapter(url);
        this.page = 1;

        this.imageURL = getImageURL();
    }

    @Override
    public String getImageURL(){
        return imageLink+name+"/chapters/"+chapter+"/"+pageFormat()+".jpg";
    }

    @Override
    public void nextPage(){
        page++;
        imageURL = imageLink+name+"/chapters/"+chapter+"/"+pageFormat()+".jpg";
    }

    @Override
    public void nextComic(){
        chapter++;
        page = 1;
        this.imageURL = getImageURL();
    }

    @Override
    public int getChapter(String url) {
        String[] args = url.split("/");
        return Integer.parseInt(args[args.length-2]);
    }
    @Override
    public int getPage(String url) {
        String[] args = url.split("/");
        String pages = args[args.length-1];
        return Integer.parseInt(pages);
    }
    @Override
    public String getComicName(String url) {
        String[] args = url.split("/");
        return args[args.length-3];
    }
}