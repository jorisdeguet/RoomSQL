package org.deguet.roomdemo;

import android.os.Bundle;
import android.util.Log;

import org.deguet.roomdemo.dao.MaBD;
import org.deguet.roomdemo.modele.DemoAlbum;

import java.util.Date;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MaBD bd = Room.databaseBuilder(getApplicationContext(), MaBD.class, "pipo")
                .allowMainThreadQueries()
                .build();

        DemoAlbum album = new DemoAlbum();
        album.artiste = "U2";
        album.dateDeSortie = new Date();
        album.nom = "TestDemo";
        bd.dao().creerAlbum(album);

        for(DemoAlbum a : bd.dao().tousLesAlbums()) {
            Log.i("ALBUM", ">> "+ a.nom + " " + a.dateDeSortie);
        }
    }
}
