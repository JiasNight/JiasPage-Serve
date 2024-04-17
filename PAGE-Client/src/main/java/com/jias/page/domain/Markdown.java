package com.jias.page.domain;

public class Markdown {

    private String mdId;

    private String mdText;

    private String mdHtml;

    private String createTime;

    private String updateTime;

    public String getMdId() {
        return mdId;
    }

    public void setMdId(String mdId) {
        this.mdId = mdId;
    }

    public String getMdText() {
        return mdText;
    }

    public void setMdText(String mdText) {
        this.mdText = mdText;
    }

    public String getMdHtml() {
        return mdHtml;
    }

    public void setMdHtml(String mdHtml) {
        this.mdHtml = mdHtml;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
