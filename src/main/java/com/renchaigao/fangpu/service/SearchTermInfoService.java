package com.renchaigao.fangpu.service;

import com.renchaigao.fangpu.domain.response.ResponseEntity;
import com.renchaigao.fangpu.domain.search.SearchTerm;

public interface SearchTermInfoService {


    ResponseEntity searchTermInfo(SearchTerm searchTerm);
}
