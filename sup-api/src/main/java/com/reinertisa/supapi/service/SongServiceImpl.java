package com.reinertisa.supapi.service;

import com.reinertisa.supapi.model.Song;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongServiceImpl implements SongService {
    @Override
    public List<Song> findAll() {
        return List.of();
    }

    @Override
    public Song findById(long id) {
        return null;
    }

    @Override
    public Song create(Song song) {
        return null;
    }

    @Override
    public Song update(long id, Song song) {
        return null;
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public List<Song> filter(String title) {
        return List.of();
    }
}
