package com.gn.testtaskriga.service.pagination.impl;

import com.gn.testtaskriga.service.pagination.PaginationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PaginationServiceImpl implements PaginationService {

    @Value("${pageSize}")
    private int pageSize;

    @Override
    public Pageable getPage(String pageStr) {
        int page;
        try{
            page = Integer.parseInt(pageStr);
            if(page <= 0)
                page = 1;
        }catch (NumberFormatException ex){
            page = 1;
        }

        return PageRequest.of(page - 1, pageSize);
    }
}
