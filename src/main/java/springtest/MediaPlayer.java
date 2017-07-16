package springtest;

public class MediaPlayer {
    private CompactDisc cd;
    public MediaPlayer(CompactDisc cd) {
        this.cd = cd;
    }
    
    public void playTheCd() {
        cd.paly();
    }
}
