package objects;


import static logic.FileManager.WebExists;

/**@TODO Manage the existence of "Book" links like "Clementine" */

public class ReadComicsOnlineRU extends ComicWeb {
    public ReadComicsOnlineRU(String url){
        super();
        url = url.replace("#", "");

        this.imageURL =  "https://readcomicsonline.ru/uploads/manga/";
        String[] args = url.split("/");

        if(!url.contains(imageURL)&& args.length == 5){
            ///@TODO Check if diferent URL could exist and with this info try to add chapter.
            System.out.println("PENDING TO IMPLEMENT THIS ACCESS");
            url += "/1/01.jpg";
        }
        if(!url.contains(imageURL) && args.length == 6){
            System.out.println("Hasta aqui bien ");
            String chap = args[args.length-1];
            System.out.println("CHap"+chap);
            args[args.length-1] = "chapters/"+chap;
            url = "";
            for (String x : args) {
                System.out.println(url);
                url+= x+"/";
            }
            url+="01.jpg";
        }

        System.out.println("URL:"+url);
        this.type = getType(url);
        this.name = getComicName(url);
        this.chapter = getChapter(url);

        this.page = 1;

    }

    public int getChapter(String url){
        String[] args = url.split("/");
        String chapter = args[args.length-2];

        if (chapter.contains("book-")){ return Integer.parseInt(chapter.split("-")[1]); }
        if (chapter.contains("GN"))   { return -1;}
        return Integer.parseInt(chapter);
    }

    public int getPage(String url) {
        String[] args = url.split("/");
        String pages = args[args.length-1];
        return Integer.parseInt(pages);
    }

    public ComicType getType(String url){
        String[] args = url.split("/");
        String chapter = args[args.length-2];

        if (chapter.contains("book-")){ return ComicType.BOOK; }
        if (chapter.contains("GN")){ return ComicType.GRAPHIC_NOVEL; }
        return ComicType.CHAPTER;
    }

    public String getComicName(String url) {
      String[] args = url.split("/");
      return args[args.length-4];
    }

    @Override
    public String getDownloadPageURL(){
        if (type.equals(ComicType.CHAPTER)){
            return imageURL+name+"/chapters/"+chapter+"/"+pageFormat()+".jpg";
        }
        if( type.equals(ComicType.BOOK)){
            return imageURL+name+"/chapters/book-"+chapter+"/"+pageFormat()+".jpg";
        }
        if( type.equals(ComicType.GRAPHIC_NOVEL)){
            return imageURL+name+"/chapters/GN/"+pageFormat()+".jpg";
        }
        return null;
    }

    @Override
    public void nextPage(){ page++; }

    @Override
    public void nextComic(){
        if(type.equals(ComicType.GRAPHIC_NOVEL)){
            type = ComicType.GRAPHIC_NOVEL;
        }
        chapter++;
        imageURL = getDownloadPageURL();
    }
}