package org.deguet.roomdemo;

import android.content.Context;
import android.util.Log;

import org.deguet.roomdemo.dao.MaBD;
import org.deguet.roomdemo.modele.DemoAlbum;
import org.deguet.roomdemo.modele.DemoPiste;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.runner.AndroidJUnit4;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
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

    @Test
    public void testParArtiste() {
        Context context = ApplicationProvider.getApplicationContext();
        MaBD bd = Room.inMemoryDatabaseBuilder(context, MaBD.class).build();
        // MaBD bd = Room.databaseBuilder(context, MaBD.class, "transaction").build();
        long a = System.currentTimeMillis();
        // créer bcp d'album
        int size = 50000;
        List<DemoAlbum> liste = new ArrayList<>();
        for (int i = 0 ; i < size ; i++) {
            DemoAlbum album = new DemoAlbum();
            album.artiste = "Joris"+i;
            album.dateDeSortie = new Date();
            album.nom = "album"+i;
            liste.add(album);
        }
        bd.dao().creerAlbums(liste);
        long b = System.currentTimeMillis();
        Log.i("PERFORMANCE", "Fini creation " + (b-a));
        // accéder à un album en particulier
        Random random = new Random();
        for (int i = 0 ; i < 100 ; i++) {
            List<DemoAlbum> l = bd.dao().parArtiste("Joris"+ random.nextInt(size));
        }
        long c = System.currentTimeMillis();
        Log.i("PERFORMANCE", "Fini les requêtes " + (c-b));
        bd.close();
    }

}
