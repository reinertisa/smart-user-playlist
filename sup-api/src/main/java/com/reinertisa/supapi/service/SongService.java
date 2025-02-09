package com.reinertisa.supapi.service;

import com.reinertisa.supapi.model.SongDto;
import com.reinertisa.supapi.model.SongRequest;

import java.util.List;

public interface SongService {

    List<SongDto> findAll();

    SongDto findById(long id);

    SongDto create(SongRequest songRequest);

    SongDto update(long id, SongService songService);

    void delete(long id);

    List<SongDto> filter(String title);
}
