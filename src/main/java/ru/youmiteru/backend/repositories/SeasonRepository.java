package ru.youmiteru.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.youmiteru.backend.domain.*;


import java.util.*;

@Repository
public interface SeasonRepository extends JpaRepository<Season, Long> {

    //Возвращает анонсы
    @Query(value = "select * from youmiteru_backend.season u where u.title_state = 'ANNOUNCEMENT' LIMIT 10", nativeQuery = true)
    List<Season> findAnnouncement();

    //Возвращает релизы
    @Query(value = "select * from youmiteru_backend.season u where u.title_state = 'RELEASED' LIMIT 10", nativeQuery = true)
    List<Season> findRecent();

    //Возвращает баннер
    @Query(value = "select * from youmiteru_backend.season LIMIT 10", nativeQuery = true)
    List<Season> findBanner();

    @Query(value = "select s.* from youmiteru_backend.season s JOIN youmiteru_backend.rating r ON (s.id = r.season_id) group by s.id order by AVG(value) desc LIMIT 10 ", nativeQuery = true)
    List<Season> findPopular();
}
