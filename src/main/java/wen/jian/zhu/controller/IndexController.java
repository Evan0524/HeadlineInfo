package wen.jian.zhu.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import wen.jian.zhu.aspect.LogAspect;
import wen.jian.zhu.model.User;
import wen.jian.zhu.service.HeaderinfoService;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/6.
 */
@Controller
public class IndexController {
    private static final Logger log = LoggerFactory.getLogger(LogAspect.class);

    @Autowired
    private HeaderinfoService headerinfoService;

    @RequestMapping(path = {"/", "/index2"})
    @ResponseBody
    public String index() {
        return  headerinfoService.say();
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

    public String news(Model model){
        model.addAttribute("value1","hello kitty");
        List<String> list = Arrays.asList(new String[]{"RED","GREEN","BLUE","BLACK"});
        Map<String,String> map = new HashMap<>();
        for(int i = 0; i < 4; i++){
            map.put(String.valueOf(i),String.valueOf(i * i));
        }
        model.addAttribute("map",map);
        model.addAttribute("list",list);
        model.addAttribute("user", new User("Evan",18));
        return "news";
    }
}
