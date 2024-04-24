package com.jias.page.controller;

import com.jias.page.domain.Markdown;
import com.jias.page.service.IMarkdownService;
import com.jias.page.utils.resultUtil.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/md")
public class markdownController {

    @Autowired
    IMarkdownService markdownService;

    @PostMapping("/markdownSave")
    public Result markdownSave(@RequestBody Markdown markdown) {
        try {
            System.out.println(markdown.toString());
            boolean isSave = markdownService.markdownSave(markdown);
            if (isSave) {
                return Result.success();
            } else {
                return Result.failure("保存失败");
            }
        } catch (Exception e) {
            System.out.println(e);
            return Result.failure("服务异常，保存失败");
        }
    }

    @GetMapping("/markdownById")
    public Result markdownSave(@RequestParam("mdId") String mdId) {
        try {
            Map<String, Markdown> dataMap = new HashMap<>();
            List<Markdown> markdowns = markdownService.markdownById(mdId);
            dataMap.put("md", markdowns.get(0));
            return Result.success(dataMap);
        } catch (Exception e) {
            System.out.println(e);
            return Result.failure("服务异常，获取失败");
        }
    }
}
