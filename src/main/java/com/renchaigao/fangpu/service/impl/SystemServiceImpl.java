package com.renchaigao.fangpu.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.renchaigao.fangpu.dao.TermInfo;
import com.renchaigao.fangpu.dao.TodayTenTerm;
import com.renchaigao.fangpu.dao.mapper.TermInfoMapper;
import com.renchaigao.fangpu.dao.mapper.TodayTenTermMapper;
import com.renchaigao.fangpu.domain.response.RespCode;
import com.renchaigao.fangpu.domain.response.ResponseEntity;
import com.renchaigao.fangpu.function.dateUse;
import com.renchaigao.fangpu.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class SystemServiceImpl implements SystemService {
    @Autowired
    TodayTenTermMapper todayTenTermMapper;

    @Autowired
    TermInfoMapper termInfoMapper;


    public ResponseEntity todayTermGet() {
        try {
            TodayTenTerm todayTenTerm = todayTenTermMapper.selectByDate(dateUse.getTodayDate());
            if (todayTenTerm == null) {
                todayTenTerm = new TodayTenTerm();
                todayTenTerm.setTodaydate(dateUse.getTodayDate());
                String termlistStr = null;
                List<TermInfo> tenterm = termInfoMapper.selectAllUserTermByUserId(0);
                if (tenterm != null) {
                    for (int i = 0; i < 10; i++) {
                        if (termlistStr == null)
                            termlistStr = tenterm.get(tenterm.size() - i -1).getId().toString();
                        else
                            termlistStr = termlistStr + "-" + tenterm.get(i).getId().toString();
                    }
                    todayTenTerm.setTermidlist(termlistStr);
                    todayTenTermMapper.insert(todayTenTerm);
                }else {
                    return new ResponseEntity(RespCode.WRONGINFO);
                }
            }
            List<String> termlist = Arrays.asList(todayTenTerm.getTermidlist().split("-"));
            List<TermInfo> tenterm = new ArrayList<>();
            for (String termidstr : termlist) {
                tenterm.add(termInfoMapper.selectByPrimaryKey(Integer.parseInt(termidstr)));
            }
            return new ResponseEntity(RespCode.SUCCESS, tenterm);
        } catch (Exception e) {
            return new ResponseEntity(RespCode.EXCEPTION, e);
        }
    }

}
