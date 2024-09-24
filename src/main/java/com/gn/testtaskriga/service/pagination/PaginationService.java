package com.gn.testtaskriga.service.pagination;

import org.springframework.data.domain.Pageable;

public interface PaginationService {

    Pageable getPage(String page);
}
