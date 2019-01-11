package org.deguet.roomdemo.modele;

import java.util.Date;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

//@Entity
@Entity(indices = {@Index("artiste")})
public class DemoAlbum {

    @PrimaryKey(autoGenerate = true)
    public Long id;

    @ColumnInfo
    public String nom;

    @ColumnInfo
    public String artiste;

    @ColumnInfo
    public Date dateDeSortie;

}
