package org.deguet.roomdemo;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.deguet.roomdemo.dao.MaBD;
import org.deguet.roomdemo.modele.DemoAlbum;

import java.util.Date;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final MaBD bd = Room.databaseBuilder(getApplicationContext(), MaBD.class, "pipo")
                //.allowMainThreadQueries()
                .build();

        DemoAlbum album = new DemoAlbum();
        album.artiste = "U2";
        album.dateDeSortie = new Date();
        album.nom = "TestDemo";
        bd.dao().creerAlbum(album)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                Toast.makeText(MainActivity.this, ""+aLong, Toast.LENGTH_SHORT).show();
                bd.dao().tousLesAlbums()
                        .subscribeOn(Schedulers.io())
                        .observeOn(Schedulers.io())
                        .subscribe(new Consumer<List<DemoAlbum>>() {
                                       @Override
                                       public void accept(List<DemoAlbum> demoAlbums) throws Exception {
                                           for(DemoAlbum a : demoAlbums) {
                                               Log.i("ALBUM", ">> "+ a.nom + " " + a.dateDeSortie);
                                           }
                                       }
                                   }
                        );

            }
        });



    }
}
