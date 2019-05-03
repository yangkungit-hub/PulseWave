package com.db.web.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.db.web.entity.Wave;
import com.db.web.service.WaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DownloadController {

    @Autowired
    WaveService waveService;

    // 下载请求
    @CrossOrigin(origins = "*")
    @GetMapping(value = "/downloadRequest", params = {"id", "startTime", "endTime"})
    public String downloadRequest(@RequestParam(value = "id") String id,
                                  @RequestParam(value = "startTime") String startTime,
                                  @RequestParam(value = "endTime") String endTime) {
        JSONObject backJson = new JSONObject();
        backJson.put("success", "true");

        List<Wave> waveList = waveService.selectWave_timeRange(id, startTime, endTime);

        // List<Wave>转JSONArray
        JSONArray waveJSONArray = new JSONArray();
        waveJSONArray.addAll(waveList);
        backJson.put("wave", waveJSONArray);

        return backJson.toJSONString();
    }
}
