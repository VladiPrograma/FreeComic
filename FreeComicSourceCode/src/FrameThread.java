public class FrameThread extends Thread{
    @Override
    public void run(){
        index.ComicWebToPDF();
    }
}
