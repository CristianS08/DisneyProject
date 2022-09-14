package com.alkemy.disney.disney.service.implementation;

import com.alkemy.disney.disney.dto.KindDTO;
import com.alkemy.disney.disney.entity.KindEntity;
import com.alkemy.disney.disney.mapper.KindMapper;
import com.alkemy.disney.disney.repository.KindRepository;
import com.alkemy.disney.disney.service.KindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KindServiceImplementation implements KindService {

    @Autowired
    private KindMapper kindMapper;
    @Autowired
    private KindRepository kindRepository;

    public KindDTO save(KindDTO dto){
        KindEntity kindEntity = kindMapper.kindDTO2Entity(dto);
        KindEntity kindEntitySaved = kindRepository.save(kindEntity);
        KindDTO result = kindMapper.kindEntity2DTO(kindEntitySaved);

        return result;
    }


    public KindEntity getKindById(Long kindId) {
        Optional<KindEntity> kindEntity = kindRepository.findById(kindId);
        if (!kindEntity.isPresent()) {
  //          throw new ParamNotFound("Genre does not exist.");
        }
        return kindEntity.get();
    }

    public void delete(Long id) {
        kindRepository.deleteById(id);
    }


}
