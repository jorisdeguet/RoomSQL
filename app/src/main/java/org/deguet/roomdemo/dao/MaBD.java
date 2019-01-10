package org.deguet.roomdemo.dao;

import org.deguet.roomdemo.modele.DemoAlbum;
import org.deguet.roomdemo.modele.DemoPiste;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;


@Database(entities = {DemoAlbum.class, DemoPiste.class},
        version = 1)
@TypeConverters({Converters.class})
public abstract class MaBD   extends RoomDatabase{
    public abstract DemoDAO dao();
}
