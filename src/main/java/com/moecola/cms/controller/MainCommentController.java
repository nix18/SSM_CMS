package com.moecola.cms.controller;

import com.moecola.cms.domain.MainComment;
import com.moecola.cms.service.IMainCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("mainComment")
public class MainCommentController {
    @Autowired
    private IMainCommentService mainCommentService;

    @RequestMapping("addMainComment")
    public String addMainComment(){ return "mainComment_add"; }
    @RequestMapping("addMainComment/do")
    public String addMainCommentDo(MainComment mainComment, Model model, HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttributes){
        try {
            System.out.println(mainComment);
            boolean a = mainCommentService.addMainComment(mainComment, request, session);
            redirectAttributes.addFlashAttribute("succ","添加评论成功！");
            return "redirect:"+request.getHeader("referer");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error","添加评论失败,原因："+e.getMessage().replace("WARN：",""));
        }
        return "redirect:"+request.getHeader("referer");
    }

    @RequestMapping("{cId}")
    public String listMainComment(@PathVariable("cId") Long ByAID,Model model){
        List<MainComment> mainComments = mainCommentService.findMainCommentByAID(ByAID);
        System.out.println(mainComments);
        model.addAttribute("mainComments",mainComments);
        return "mainComment_detail";
    }

    @RequestMapping("delMainComment/{cId}")
    public String delMainComment(@PathVariable("cId") Long cId, Model model, HttpServletRequest request, RedirectAttributes redirectAttributes){
        try {
            mainCommentService.delMainComment(cId ,request);
            redirectAttributes.addFlashAttribute("succ","删除成功！");
            return "redirect:"+request.getHeader("referer");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error","删除失败，原因：未知原因");
        }
        return "redirect:"+request.getHeader("referer");
    }
}
