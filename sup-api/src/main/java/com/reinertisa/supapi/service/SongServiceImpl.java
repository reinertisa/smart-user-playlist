package com.reinertisa.supapi.service;

import com.reinertisa.supapi.exception.ResourceNotFoundException;
import com.reinertisa.supapi.dto.Song;
import com.reinertisa.supapi.entity.SongEntity;
import com.reinertisa.supapi.mapper.SongMapper;
import com.reinertisa.supapi.dtorequest.SongRequest;
import com.reinertisa.supapi.repository.SongRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongServiceImpl implements SongService {

    private final SongMapper songMapper;
    private final SongRepository songRepository;

    public SongServiceImpl(SongMapper songMapper, SongRepository songRepository) {
        this.songMapper = songMapper;
        this.songRepository = songRepository;
    }

    @Override
    public List<Song> findAll() {
        return songMapper.toDtoListFromEntityList(songRepository.findAll());
    }

    @Override
    public Song findById(long id) {
        return null;
    }

    @Override
    @Transactional
    public Song create(SongRequest songRequest) {
        SongEntity songEntity = songRepository.save(songMapper.toEntityFromRequest(songRequest));
        return songMapper.toDtoFromEntity(songEntity);
    }

    @Override
    public Song update(long id, SongService songService) {
        return null;
    }

    @Override
    public void delete(long id) throws ResourceNotFoundException {
        SongEntity songEntity = songRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid songEntity id: " + id));
        songRepository.delete(songEntity);
    }

    @Override
    public List<Song> filter(String title) {
        return List.of();
    }
}
