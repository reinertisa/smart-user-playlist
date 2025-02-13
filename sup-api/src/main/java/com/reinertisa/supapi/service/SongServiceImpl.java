package com.reinertisa.supapi.service;

import com.reinertisa.supapi.exception.ResourceNotFoundException;
import com.reinertisa.supapi.model.Song;
import com.reinertisa.supapi.model.SongDto;
import com.reinertisa.supapi.model.SongMapper;
import com.reinertisa.supapi.model.SongRequest;
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
    public List<SongDto> findAll() {
        return songMapper.toDtoListFromEntityList(songRepository.findAll());
    }

    @Override
    public SongDto findById(long id) {
        return null;
    }

    @Override
    @Transactional
    public SongDto create(SongRequest songRequest) {
        Song song = songRepository.save(songMapper.toEntityFromRequest(songRequest));
        return songMapper.toDtoFromEntity(song);
    }

    @Override
    public SongDto update(long id, SongService songService) {
        return null;
    }

    @Override
    public void delete(long id) throws ResourceNotFoundException {
        Song song = songRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid song id: " + id));
        songRepository.delete(song);
    }

    @Override
    public List<SongDto> filter(String title) {
        return List.of();
    }
}
