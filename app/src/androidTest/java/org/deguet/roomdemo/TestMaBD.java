package org.deguet.roomdemo;

import android.content.Context;

import org.deguet.roomdemo.dao.MaBD;
import org.deguet.roomdemo.modele.DemoAlbum;
import org.deguet.roomdemo.modele.DemoPiste;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.runner.AndroidJUnit4;

import static org.junit.Assert.*;

public class TestMaBD {

    @Test
    public void testPeu() {
        Context context = ApplicationProvider.getApplicationContext();
        MaBD bd = Room.inMemoryDatabaseBuilder(context, MaBD.class).build();
        // MaBD bd = Room.databaseBuilder(context, MaBD.class, "test").build();
        for (int i = 0 ; i < 10 ; i++ ) {
            DemoAlbum a = new DemoAlbum();
            a.artiste = "Joris";
            a.dateDeSortie = new Date();
            a.nom = "album" + i;
            bd.dao().creerAlbum(a);
        }
        List<DemoAlbum> albums = bd.dao().tousLesAlbums();
        assertEquals(10, albums.size());
        bd.close();
    }

    @Test
    public void textBeaucoup() {
        Context context = ApplicationProvider.getApplicationContext();
        MaBD bd = Room.inMemoryDatabaseBuilder(context, MaBD.class).build();
        // MaBD bd = Room.databaseBuilder(context, MaBD.class, "test").build();
        for (int i = 0 ; i < 100 ; i++ ) {
            DemoAlbum a = new DemoAlbum();
            a.artiste = "Joris";
            a.dateDeSortie = new Date();
            a.nom = "album" + i;
            bd.dao().creerAlbum(a);
        }
        List<DemoAlbum> albums = bd.dao().tousLesAlbums();
        assertEquals(100, albums.size());
        bd.close();
    }

    @Test
    public void testTransaction() {
        Context context = ApplicationProvider.getApplicationContext();
        MaBD bd = Room.inMemoryDatabaseBuilder(context, MaBD.class).build();
        // MaBD bd = Room.databaseBuilder(context, MaBD.class, "transaction").build();
        DemoAlbum a = new DemoAlbum();
        a.artiste = "Joris 漢字";
        a.dateDeSortie = new Date();
        a.nom = "album de test";
        List<DemoPiste> pistes = new ArrayList<>();
        for (int i = 0 ; i < 100 ; i++) {
            DemoPiste piste = new DemoPiste();
            piste.nom = "piste "+i;
            pistes.add(piste);
        }

        bd.dao().creerAlbumPistes(a, pistes);

        List<DemoAlbum> albums = bd.dao().tousLesAlbums();
        assertEquals(1, albums.size());
        bd.close();
    }

}
