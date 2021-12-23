package com.jias.page.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Markdown {

    private String mdId;

    private String mdText;

    private String mdHtml;

    private String createTime;

    private String updateTime;
}
