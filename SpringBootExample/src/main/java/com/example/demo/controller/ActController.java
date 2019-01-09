package com.example.demo.controller;

import com.example.demo.model.ResultCompile;
import com.example.demo.process.CompileManager;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;

/**
 * Created by son on 2019-01-08.
 */

@Controller
public class ActController {
    @RequestMapping("/")
    public String init() {
        return "index";
    }

    @ResponseBody
    @RequestMapping(value = "/compile.action", method = RequestMethod.POST)
    public ResultCompile compile (
            @RequestParam("text") String text
    ) {
        System.out.println("--------------- 요청된 코드 ---------------");
        System.out.println(text);
        System.out.println("------------------------------------------");
        File workDir = new File("C:\\tmp");
        workDir.mkdirs();

        String result = CompileManager.run(text);

        ResultCompile resultCompile = new ResultCompile();
        resultCompile.setSuccess(!StringUtils.isEmpty(result));
        resultCompile.setMessage("ok");
        resultCompile.setResult(result);
        return resultCompile;
    }

    @RequestMapping("/codingView.action")
    public String codingView() {
        return "editor";
    }
}