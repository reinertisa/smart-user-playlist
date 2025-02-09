package com.reinertisa.supapi.controller;


import com.reinertisa.supapi.model.Song;
import com.reinertisa.supapi.model.SongDto;
import com.reinertisa.supapi.model.SongRequest;
import com.reinertisa.supapi.service.SongService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/songs")
@CrossOrigin(origins = "*")
public class SongController {
    private final SongService songService;

    public SongController(SongService songService) {
        this.songService = songService;
    }

    @GetMapping("")
    public ResponseEntity<List<SongDto>> getAllSongs() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(songService.findAll());
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
        }
    }

    @PostMapping("")
    public ResponseEntity<SongDto> createSong(@RequestBody SongRequest songRequest) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(songService.create(songRequest));
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
        }
    }

}
