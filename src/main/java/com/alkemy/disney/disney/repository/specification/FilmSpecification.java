package com.alkemy.disney.disney.repository.specification;

import com.alkemy.disney.disney.dto.FilmFiltersDTO;
import com.alkemy.disney.disney.entity.CharacterEntity;
import com.alkemy.disney.disney.entity.FilmEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Component
public class FilmSpecification {

    public Specification<FilmEntity> getFiltered(FilmFiltersDTO filmFilters){

        // Lambda Function:
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            // == Title ==
            if(StringUtils.hasLength(filmFilters.getTitle())) {
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("title")),
                                "%" + filmFilters.getTitle().toLowerCase() + "%"
                        )
                );
            }

            // == Characters ==
            if(!CollectionUtils.isEmpty(filmFilters.getCharacters())) {
                Join<FilmEntity, CharacterEntity> join = root.join("filmCharacters", JoinType.INNER);
                Expression<String> characterId = join.get("id");
                predicates.add(characterId.in(filmFilters.getCharacters()));
            }
            //Remove duplicates
            query.distinct(true);

            // == Order ==
            String orderByField = "title";
            query.orderBy(
                    filmFilters.isASC() ?
                            criteriaBuilder.asc(root.get(orderByField)) :
                            criteriaBuilder.desc(root.get(orderByField))
            );


            // MAIN RETURN:
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
