package com.reinertisa.supapi.resource;


import com.reinertisa.supapi.dto.Song;
import com.reinertisa.supapi.dtorequest.SongRequest;
import com.reinertisa.supapi.service.SongService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/songs")
@CrossOrigin(origins = "*")
public class SongResource {
    private final SongService songService;

    public SongResource(SongService songService) {
        this.songService = songService;
    }

    @GetMapping("")
    public ResponseEntity<List<Song>> getAllSongs() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(songService.findAll());
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
        }
    }

    @PostMapping("")
    public ResponseEntity<Song> createSong(@RequestBody SongRequest songRequest) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(songService.create(songRequest));
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSong(@PathVariable long id) {
        try {
            songService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
        }
    }

}
