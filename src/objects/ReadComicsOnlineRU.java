package objects;

import logic.FileManager;

public class ReadComicsOnlineRU extends ComicWeb {
    public ReadComicsOnlineRU(String url){
        super();
        url = url.replace("#", "");

        this.imageURL =  "https://readcomicsonline.ru/uploads/manga/";
        String[] args = url.split("/");

        this.type = getType(url);
        this.name = getComicName(args);
        this.chapter = getChapter(args);
        this.page = 1;

    }

    public int getChapter( String[] args){
        if(args.length == 5) return 1;

        String chapter = args[args.length-2];
        if(args.length == 6) chapter = args[args.length-1];
        if (chapter.contains("book-")){ return Integer.parseInt(chapter.split("-")[1]); }
        if (chapter.contains("GN"))   { return -1;}
        return Integer.parseInt(chapter);
    }

    public int getPage(String[] args) {
        if(args.length == 5) return 1;
        String pages = args[args.length-1];
        return Integer.parseInt(pages);
    }

    public ComicType getType(String url){
        String[] args = url.split("/");
        if(args.length == 5) return getUnknownType(url);

        String chapter = args[args.length-2];

        if (chapter.contains("book-")){ return ComicType.BOOK; }
        if (chapter.contains("GN")){ return ComicType.GRAPHIC_NOVEL; }
        return ComicType.CHAPTER;
    }
    public ComicType getUnknownType(String url){
        if(FileManager.WebExists(url+"/1")){
            return ComicType.CHAPTER;
        }
        if(FileManager.WebExists(url+"/book-1")){
            return ComicType.BOOK;
        }
        return ComicType.GRAPHIC_NOVEL;
    }

    public String getComicName(String[] args) {
      if(args.length == 5) return args[args.length-1];
      if(args.length == 6) return args[args.length-2];
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