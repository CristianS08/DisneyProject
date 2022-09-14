package com.alkemy.disney.disney.mapper;

import com.alkemy.disney.disney.dto.KindDTO;
import com.alkemy.disney.disney.entity.KindEntity;
import org.springframework.stereotype.Component;

@Component
public class KindMapper {

    public KindEntity kindDTO2Entity(KindDTO dto){
        KindEntity kindEntity = new KindEntity();
        kindEntity.setImage(dto.getImage());
        kindEntity.setName(dto.getName());

        return kindEntity;
    }

    public KindDTO kindEntity2DTO(KindEntity entity){
        KindDTO kindDTO = new KindDTO();
        kindDTO.setId(entity.getId());
        kindDTO.setImage(entity.getImage());
        kindDTO.setName(entity.getName());

        return kindDTO;
    }

}
