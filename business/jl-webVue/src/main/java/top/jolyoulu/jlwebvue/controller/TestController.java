package top.jolyoulu.jlwebvue.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: JolyouLu
 * @Date: 2023/3/7 22:51
 * @Description
 */
@RestController
@RequestMapping("test")
public class TestController {

    @GetMapping("/1")
    public String test(){
        return "test";
    }
}
