package com.moecola.cms.controller;

import com.moecola.cms.domain.SubComment;
import com.moecola.cms.service.ISubCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("subComment")
public class SubCommentController {
    @Autowired
    private ISubCommentService subCommentService;

    @RequestMapping("addSubComment")
    public String addSubComment(){ return "subComment_add"; }

    @RequestMapping("addSubComment/do")
    public String addSubCommentDo(SubComment subComment, Model model, HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttributes){
        try {
            System.out.println(subComment);
            long a = subCommentService.addSubComment(subComment, request, session);
            redirectAttributes.addFlashAttribute("succ","回复添加成功！");
            return "redirect:"+request.getHeader("referer");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error","添加回复失败,原因："+e.getMessage().replace("WARN：",""));
        }
        return "redirect:"+request.getHeader("referer");
    }

    @RequestMapping("delSubComment/{scId}")
    public String delSubComment(@PathVariable("scId") Long scId, HttpServletRequest request,RedirectAttributes redirectAttributes){
        try {
            long a = subCommentService.delSubComment(scId, request);
            redirectAttributes.addFlashAttribute("succ","删除成功！");
            return "redirect:"+request.getHeader("referer");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error","删除回复失败,原因："+e.getMessage().replace("WARN：",""));
        }
        return "redirect:"+request.getHeader("referer");
    }
}
