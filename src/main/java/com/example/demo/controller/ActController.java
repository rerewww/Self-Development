package com.example.demo.controller;

import com.example.demo.model.ResultCompile;
import com.example.demo.model.UserVO;
import com.example.demo.service.ActService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * Created by son on 2019-01-08.
 */

@Slf4j
@Controller
public class ActController {
    private static final String AUTH_KEY = "authentication";
    private ActService actService;

    @Autowired
    ActController(final ActService actService) {
        this.actService = actService;
    }
    
    @RequestMapping("/")
    public String init(final HttpSession httpSession) {
        if ("test".equals(httpSession.getAttribute(AUTH_KEY))) {
            return "index";
        }
        return "login";
    }

    @RequestMapping(value = "/main")
    public String login(
            final HttpSession httpSession,
            final UserVO userVO) {
        if (!actService.checkLogin()) {
            log.warn("Failed Check login");
            return "/";
        }

        httpSession.setAttribute(AUTH_KEY, "test");
        httpSession.setAttribute("isGuest", userVO.isGuest());
        return "index";
    }

    @RequestMapping("/logout")
    public String logout(final HttpSession httpSession) {
        httpSession.removeAttribute(AUTH_KEY);
        return "redirect:/";
    }

    @ResponseBody
    @RequestMapping(value = "/compile.cmd", method = RequestMethod.POST)
    public ResultCompile compile (
            @RequestParam("text") String text,
            @RequestParam("lang") String lang,
            @RequestParam("number") int number
    ) {
        log.info("Requeste Code " + text);
        File workDir = new File("C:\\tmp");
        workDir.mkdirs();

        List<Boolean> result = actService.run(text, lang, number);

        ResultCompile resultCompile = new ResultCompile();
        resultCompile.setSuccess(!StringUtils.isEmpty(result));
        resultCompile.setMessage("ok");
        resultCompile.setResult(result);
        return resultCompile;
    }

    @RequestMapping("/codingView.cmd")
    public ModelAndView codingView(final HttpSession session) {
        ModelAndView mv = new ModelAndView("question");
        mv.addObject("questions", actService.getQuestionsJsonString());
        mv.addObject("isGuest", session.getAttribute("isGuest"));
        return mv;
    }

    @RequestMapping(value = "/coding.cmd")
    public ModelAndView codingView(
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam("editor") String editor,
            @RequestParam("number") int number
    ) throws UnsupportedEncodingException {
        ModelAndView mv = new ModelAndView("editor");
        mv.addObject("title", title);
        mv.addObject("content", content);
        mv.addObject("number", number);

        // URLEncoder에서 공백을 '+'로 인코딩 함으로 다시 %20(공백)으로 치환한다.
        // 실제 '+'기호가 존재할 경우 인코딩은 %2B로 변환된다.
        mv.addObject("editor", URLEncoder.encode(editor, "UTF-8").replaceAll("\\+", "%20"));

        return mv;
    }
}
