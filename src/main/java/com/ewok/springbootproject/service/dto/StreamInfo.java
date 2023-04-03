package com.ewok.springbootproject.service.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.LinkedHashMap;

@Getter
@RequiredArgsConstructor
public class StreamInfo {

    private final ArrayList data;
    private final Object pagination;

    public StreamInfo(LinkedHashMap map) {
        this.data = (ArrayList) map.get("data");
        this.pagination = map.get("pagination");
    }
}
