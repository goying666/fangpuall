package com.renchaigao.fangpu.service;

import com.renchaigao.fangpu.domain.response.ResponseEntity;

public interface RankService {

    ResponseEntity addTermsRank();
    ResponseEntity getTermsRank(Integer endnum);
    ResponseEntity addRecordingRank();
    ResponseEntity getRecordingRank(Integer endnum);
}
