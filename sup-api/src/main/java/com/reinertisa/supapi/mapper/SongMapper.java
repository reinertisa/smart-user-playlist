package com.reinertisa.supapi.mapper;

import com.reinertisa.supapi.dto.Song;
import com.reinertisa.supapi.entity.SongEntity;
import com.reinertisa.supapi.dtorequest.SongRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SongMapper {

    @Mapping(source = "songId", target = "id")
    SongEntity toEntityFromRequest(SongRequest songRequest);

    @Mapping(source = "id", target = "songId")
    Song toDtoFromEntity(SongEntity songEntity);

    List<Song> toDtoListFromEntityList(List<SongEntity> songEntities);
}
