package wen.jian.zhu.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import wen.jian.zhu.aspect.LogAspect;
import wen.jian.zhu.service.HeaderinfoService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created by Administrator on 2016/7/6.
 */
//@Controller
public class IndexController {
    private static final Logger log = LoggerFactory.getLogger(LogAspect.class);

    @Autowired
    private HeaderinfoService headerinfoService;

    @RequestMapping(path = {"/", "/index2"})
    @ResponseBody
    public String index() {
        return headerinfoService.say();
    }

    @RequestMapping("/profile/{groupId}/{userId}")
    @ResponseBody
    public String profile(@PathVariable("groupId") String groupId,
                          @PathVariable("userId") int userId,
                          @RequestParam(value = "type", defaultValue = "1") int type,
                          @RequestParam(value = "key", defaultValue = "xx") String key) {
        return String.format("%s,%d,%d,%s", groupId, userId, type, key);
    }

    @RequestMapping("/vm")

    public String news(Model model) {
        model.addAttribute("value1", "hello kitty");
        List<String> list = Arrays.asList(new String[]{"RED", "GREEN", "BLUE", "BLACK"});
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < 4; i++) {
            map.put(String.valueOf(i), String.valueOf(i * i));
        }
        model.addAttribute("map", map);
        model.addAttribute("list", list);
        ///model.addAttribute("user", new User("Evan", 18));
        return "news";
    }

    @RequestMapping("/request")
    @ResponseBody
    public String request(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        StringBuilder sb = new StringBuilder();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            sb.append(name + ":" + request.getHeader(name) + "<br>");
        }
        for (Cookie cookies : request.getCookies()) {
            sb.append("Cookie:");
            sb.append(cookies.getName());
            sb.append(":");
            sb.append(cookies.getValue());
            sb.append("<br>");
        }
        sb.append("getMethod" + request.getMethod() + "<br>");
        sb.append("getPathInfo" + request.getPathInfo() + "<br>");
        sb.append("getQueryString" + request.getQueryString() + "<br>");
        sb.append("getRequestURI" + request.getRequestURI() + "<br>");

        return sb.toString();
    }

    @RequestMapping("/response")
    @ResponseBody
    public String response(@CookieValue(value = "nowcodeid", defaultValue = "m") String nowcodeId,
                           @RequestParam(value = "key", defaultValue = "key") String key,
                           @RequestParam(value = "value", defaultValue = "value") String value, HttpServletResponse response) {
        response.addCookie(new Cookie(key, value));
        response.addHeader(key, value);
        return "nowCodeId from Cookie " + nowcodeId;
    }

    @RequestMapping("/redirect/{code}")
    public String redirect(@PathVariable("code") int code, HttpSession session) {
        session.setAttribute("msg", "Jump from redirect~");
        return "redirect:/";
    }

    @RequestMapping("/admin")
    @ResponseBody
    public String admin(@RequestParam(value = "key", defaultValue = "key") String key) {
        if ("admin".equals(key)) {
            return "hello admin!";

        }
        throw new IllegalArgumentException("Key problem");
    }

    @ExceptionHandler
    @ResponseBody
    public String error(Exception e) {
        return "error:" + e.getMessage();
    }
}
