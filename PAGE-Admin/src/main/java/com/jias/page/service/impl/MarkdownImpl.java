package com.jias.page.service.impl;

import com.jias.page.domain.Markdown;
import com.jias.page.mapper.MarkdownMapper;
import com.jias.page.service.IMarkdownService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

@Service
public class MarkdownImpl implements IMarkdownService {

    @Resource
    MarkdownMapper markdownMapper;

    /**
     * 保存md文档
     * @param markdown md实体
     * @return boolean
     */
    @Override
    public boolean markdownSave(Markdown markdown) {
        try {
            markdown.setMdId(UUID.randomUUID().toString());
            String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis());
            markdown.setCreateTime(time);
            markdown.setUpdateTime(time);
            System.out.println(markdown.toString());
            return markdownMapper.markdownSave(markdown);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 获取markdown
     * @param mdId mdId
     * @return md
     */
    @Override
    public List<Markdown> markdownById(String mdId) {
        try {
            List<Markdown> markdowns = markdownMapper.markdownById(mdId);
            return markdowns;
        } catch (Exception e) {
            return null;
        }
    }
}
