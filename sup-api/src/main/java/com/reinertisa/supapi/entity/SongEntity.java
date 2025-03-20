package com.reinertisa.supapi.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;

@Entity
@Table(name = "songs")
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class SongEntity extends Auditable {
    @Column(updatable = false, unique = true, nullable = false)
    private String songId;

    @Column(name = "title")
    private String title;

    @Column(name = "artist")
    private String artist;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "fk_songs_owner",
                    foreignKeyDefinition = "foreign key /* FK */ (user_id) references UserEntity",
                    value = ConstraintMode.CONSTRAINT
            )
    )
    private UserEntity owner;

    public SongEntity() {
    }

    public String getSongId() {
        return songId;
    }

    public void setSongId(String songId) {
        this.songId = songId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public UserEntity getOwner() {
        return owner;
    }

    public void setOwner(UserEntity owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "SongEntity{" +
                "songId='" + songId + '\'' +
                ", title='" + title + '\'' +
                ", artist='" + artist + '\'' +
                ", owner=" + owner +
                '}';
    }
}
