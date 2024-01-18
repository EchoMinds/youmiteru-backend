package ru.youmiteru.backend.repositories;

import net.sf.jsqlparser.statement.select.Select;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import ru.youmiteru.backend.domain.*;
import ru.youmiteru.backend.dto.SeasonDTO;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public interface SeasonRepository extends JpaRepository<Season, Long> {

    //Возвращает анонсы
    default List<Season> findAnnouncement(){
        return findByTitleState(TitleState.ANNOUNCEMENT, PageRequest.of(0, 10));
    }

    //Возвращает релизы
    default List<Season> findRelease(){
        return findByTitleState(TitleState.RELEASED, PageRequest.of(0, 10));
    }

    //Возвращает баннер
    default List<Season> findBanner(){
        return findAll(PageRequest.of(0, 10)).stream().collect(Collectors.toList());
    }

    //Возвращает популярных
    default List<Season> findPopular(){
        return findByTitleState(TitleState.FINISHED, PageRequest.of(0, 10));
    }

    List<Season> findByTitleState(TitleState titleState, PageRequest pageRequest);



}
