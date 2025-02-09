package com.reinertisa.supapi.model;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SongMapper {

    @Mapping(source = "songId", target = "id")
    Song toEntityFromRequest(SongRequest songRequest);

    @Mapping(source = "id", target = "songId")
    SongDto toDtoFromEntity(Song song);

    List<SongDto> toDtoListFromEntityList(List<Song> songs);
}
