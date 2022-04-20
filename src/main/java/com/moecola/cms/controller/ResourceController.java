package com.moecola.cms.controller;

import com.moecola.cms.service.IArticleService;
import com.moecola.cms.service.IResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("resource")
public class ResourceController {
    @Autowired
    private IResourceService resourceService;
    @Autowired
    private IArticleService articleService;
    @RequestMapping("uploadR/{aId}")
    public String uploadR(HttpServletRequest request, MultipartFile upload, @PathVariable("aId") Long aId, Model model, RedirectAttributes redirectAttributes) throws Exception {
        model.addAttribute("Tags",articleService.Tags());
        try {
            resourceService.uploadR(request, upload, aId, model);
            redirectAttributes.addFlashAttribute("succ", "上传成功");
            return "redirect:"+request.getHeader("referer");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("INFO：发生了上传错误");
            redirectAttributes.addFlashAttribute("error","上传错误");
        }
        return "redirect:"+request.getHeader("referer");
    }
    @RequestMapping("deleteR/{rId}")
    public String deleteR(@PathVariable("rId") Long rId, Model model, HttpServletRequest request,RedirectAttributes redirectAttributes){
        model.addAttribute("Tags",articleService.Tags());
        try {
            resourceService.deleteR(rId,request);
            redirectAttributes.addFlashAttribute("succ","删除成功");
            return "redirect:"+request.getHeader("referer");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("INFO：发生了删除错误");
            redirectAttributes.addFlashAttribute("error","删除失败");
        }
        return "redirect:"+request.getHeader("referer");
    }
}
