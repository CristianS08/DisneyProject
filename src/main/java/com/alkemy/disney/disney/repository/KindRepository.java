package com.alkemy.disney.disney.repository;

import com.alkemy.disney.disney.entity.KindEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KindRepository extends JpaRepository<KindEntity, Long> {


}
