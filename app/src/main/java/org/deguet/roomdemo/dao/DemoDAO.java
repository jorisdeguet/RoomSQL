package org.deguet.roomdemo.dao;

import org.deguet.roomdemo.modele.DemoAlbum;
import org.deguet.roomdemo.modele.DemoPiste;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import io.reactivex.Maybe;
import io.reactivex.Single;

@Dao
public abstract class DemoDAO {

    @Insert
    public abstract Single<Long> creerAlbum(DemoAlbum album);

    @Insert
    public abstract Single<List<Long>> creerAlbums(List<DemoAlbum> albums);

    @Insert
    public abstract Single<Long> creerPiste(DemoPiste piste);

    @Query("SELECT * FROM DemoAlbum")
    public abstract Maybe<List<DemoAlbum>> tousLesAlbums();

    @Query("SELECT * FROM DemoAlbum WHERE artiste = :art")
    public abstract Maybe<List<DemoAlbum>> parArtiste(String art);

    @Transaction
    public Single<Long> creerAlbumPistes(DemoAlbum a, List<DemoPiste> ps){
        Long id = this.creerAlbum(a).blockingGet();
        for (DemoPiste p : ps){
            p.albumId = id;
            this.creerPiste(p);
        }
        return Single.just(id);
    }

}
