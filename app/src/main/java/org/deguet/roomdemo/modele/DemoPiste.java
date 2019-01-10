package org.deguet.roomdemo.modele;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = DemoAlbum.class,
        parentColumns = "id",
        childColumns = "albumId"),
        indices = {@Index("albumId")}
)
public class DemoPiste {

    @PrimaryKey(autoGenerate = true)
    public Long id;

    @ColumnInfo
    public String nom;

    @ColumnInfo
    public Long albumId;


}
