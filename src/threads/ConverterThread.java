package threads;

import logic.FileManager;
import objects.ComicWeb;

import javax.swing.*;

import static logic.FileManager.WebExists;
import static screen.HomeScreen.sucessAdvise;
import static screen.HomeScreen.waitAdvise;

public class ConverterThread extends Thread{

    ComicWeb comic;
    JFrame frame;
    JLabel advise;
    int option;

    public ConverterThread(ComicWeb comic, int option, JFrame frame, JLabel label){
        this.comic = comic;
        this.frame = frame;
        this.advise = label;
        this.option = option;

    }

    @Override
    public void run(){
        if (option == 1){
            downloadComic();
            return;
        }
        if (option == 2){
            downloadComic();
            comic.nextComic();
            downloadComic();
            comic.nextComic();
            downloadComic();
            sucessAdvise(advise, frame);
            return;
        }
        /** @TODO Check if chapter exists */
        /** @TODO waitAdvise with chap number */
        /** @TODO try to move away screen logic from backend logic */


        try {
            System.out.println("While loop: "+comic.imageURL);
            while (WebExists(comic.getDownloadPageURL())){
                System.out.println("Webo Exists");
                downloadComic();
                comic.nextComic();
            }
        }catch (Exception e){
            System.out.println("No more chapters to add.");
            sucessAdvise(advise, frame);
            this.interrupt();
        }

    }

    public void downloadComic(){
        waitAdvise(advise, frame, "Downloading Images...");
        FileManager.downloadChapterImages(comic, advise, frame);
        waitAdvise(advise, frame, "Images to PDF...");
        FileManager.ChapterImagesToPDF(comic);
        waitAdvise(advise, frame, "Cleaning...");
        FileManager.removeImages(comic.chapter, comic.chapter);
        sucessAdvise(advise, frame);
    }
}
