package com.jias.page.mapper;

import com.jias.page.domain.Markdown;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MarkdownMapper {

    /**
     * 保存md文档
     * @param markdown md
     * @return boolean
     */
    boolean markdownSave(Markdown markdown);

    /**
     * 获取md
     * @param mdId mdId
     * @return md
     */
    List<Markdown> markdownById(String mdId);
}
