package com.study.prospring5.repos;

import com.study.prospring5.domain.Singer;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SingerRepository extends PagingAndSortingRepository<Singer, Long> {

}
