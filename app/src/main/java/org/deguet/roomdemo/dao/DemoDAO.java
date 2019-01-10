package org.deguet.roomdemo.dao;

import org.deguet.roomdemo.modele.DemoAlbum;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface DemoDAO {

    @Insert
    Long creerAlbum(DemoAlbum album);

    @Query("SELECT * FROM DemoAlbum")
    List<DemoAlbum> tousLesAlbums();

}
