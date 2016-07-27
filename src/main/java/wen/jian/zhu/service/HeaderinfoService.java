package wen.jian.zhu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wen.jian.zhu.dao.NewsDAO;
import wen.jian.zhu.model.News;

import java.util.List;

/**
 * Created by Administrator on 2016/7/7.
 */
@Service
public class HeaderinfoService {
    public String say() {
        return "This is from ToutiaoService";
    }

    @Autowired
    private NewsDAO newsDAO;

    public List<News> getLatestNews(int userId, int offset, int limit) {
        return newsDAO.selectByUserIdAndOffset(userId, offset, limit);
    }
}
