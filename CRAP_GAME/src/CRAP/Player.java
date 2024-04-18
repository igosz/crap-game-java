package CRAP;

import java.awt.event.ActionListener;

public class Player {
    public int x;
    public int y;

    private boolean isDead;
    Player(){
        isDead = false;
        this.x = 2;
        this.y = 8;
    }


    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }
}
