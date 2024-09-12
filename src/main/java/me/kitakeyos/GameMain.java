package me.kitakeyos;

import com.barteo.emulator.app.Application;
import game.GameMIDLet;

public class GameMain {
    public static void main(String[] args) {
        Application application = new Application("Vương Quốc Sủng Vật", GameMIDLet.class, (short) 240, (short) 320, "/icon.png", args);
        application.start();
    }
}
