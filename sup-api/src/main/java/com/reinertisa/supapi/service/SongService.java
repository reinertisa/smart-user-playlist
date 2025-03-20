package com.reinertisa.supapi.service;

import com.reinertisa.supapi.exception.ResourceNotFoundException;
import com.reinertisa.supapi.dto.Song;
import com.reinertisa.supapi.dtorequest.SongRequest;

import java.util.List;

public interface SongService {

    List<Song> findAll();
    Song findById(long id);
    Song create(SongRequest songRequest);
    Song update(long id, SongService songService);
    void delete(long id) throws ResourceNotFoundException;
    List<Song> filter(String title);
}
