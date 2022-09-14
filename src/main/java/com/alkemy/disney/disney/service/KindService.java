package com.alkemy.disney.disney.service;

import com.alkemy.disney.disney.dto.KindDTO;
import com.alkemy.disney.disney.entity.KindEntity;

import java.util.List;

public interface KindService {
    KindDTO save(KindDTO dto);

    KindEntity getKindById(Long kindId);

    void delete(Long id);

}
