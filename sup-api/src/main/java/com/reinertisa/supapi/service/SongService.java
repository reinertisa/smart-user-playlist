package com.reinertisa.supapi.service;

import com.reinertisa.supapi.model.Song;

import java.util.List;

public interface SongService {

    List<Song> findAll();

    Song findById(long id);

    Song create(Song song);

    Song update(long id, Song song);

    void delete(long id);

    List<Song> filter(String title);
}
