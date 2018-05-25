package com.renchaigao.fangpu.controller;


import com.renchaigao.fangpu.dao.RecordingInfo;
import com.renchaigao.fangpu.domain.response.ResponseEntity;
import com.renchaigao.fangpu.domain.search.SearchTerm;
import com.renchaigao.fangpu.service.impl.SearchTermInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/search")
public class SearchController {

    @Autowired
    SearchTermInfoServiceImpl searchTermInfoServiceImpl;

    @PostMapping(value = "/terminfo", consumes = "application/json")
    @ResponseBody
    public ResponseEntity searchTermInfo(@RequestBody SearchTerm searchTerm) {
        return searchTermInfoServiceImpl.searchTermInfo(searchTerm);
    }

}
