package objects;

public class  ComicWeb {
    public String url;
    public String name;
    public String imageURL;
    public int chapter;
    public int page;

    public ComicWeb(String url, String name, String baseURL, String imageURL, int chapter, int page){
        this.url = url;
        this.name = name;
        this.chapter = chapter;
        this.page = page;
        this.imageURL = imageURL;
    }

    public ComicWeb(String url){
        url = url.replace("#", "");
        this.url = url;
    }

    public void nextChapter(){
        chapter++;
        page = 0;
    }

    public void nextPage(){
        page++;
    }

    public String getImageURL(){
        return url+page+".jpg";
    }

    public int getChapter(String url) {
        String[] args = url.split("/");
        return Integer.parseInt(args[args.length-2]);
    }

    public int getPage(String url) { return page;}

    public String getComicName(String url) {
        String[] args = url.split("/");
        return args[args.length-3];
    }

    public String getBaseURL(String url) {
        String[] parts = url.split("/");
        StringBuilder res= new StringBuilder();
        for (int i=0; i<7; i++){
            res.append(parts[i]).append("/");
        }
        return res.toString();
    }

    public  String pageFormat(){
        if (page < 10) { return "0" + page; }
        return Integer.toString(page);
    }
}

