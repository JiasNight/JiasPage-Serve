package com.jias.page.service;

import com.jias.page.domain.Markdown;

import java.util.List;

public interface IMarkdownService {

    /**
     * 保存markdown
     * @param markdown
     * @return boolean
     */
    boolean markdownSave(Markdown markdown);

    /**
     * 获取markdown
     * @param mdId mdId
     * @return Markdown
     */
    List<Markdown> markdownById(String mdId);
}
