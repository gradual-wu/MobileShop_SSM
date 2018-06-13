package cn.wycclub.controller;

import cn.wycclub.dao.po.UserCollection;
import cn.wycclub.dao.po.UserInfo;
import cn.wycclub.dto.Collection;
import cn.wycclub.interceptor.UserLogin;
import cn.wycclub.service.BusinessCollectionService;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 收藏相关处理器
 *
 * @author WuYuchen
 * @create 2018-02-16 22:20
 **/

@Controller
public class CollectionController implements UserLogin {

    @Autowired
    private BusinessCollectionService service;

    private Logger log = Logger.getLogger(CollectionController.class);

    /**
     * 添加收藏
     * */
    @RequestMapping("/addCollection.do")
    public void addCollection(HttpSession session, HttpServletResponse response, Integer pid) {
        UserInfo user = (UserInfo) session.getAttribute("user");
        Map<String, Object> map = new HashMap<>();
        try {
            service.addCollection(pid, user.getUid());
            map.put("message", "已收藏!");
            map.put("flag", "true");
        } catch (Exception e) {
            map.put("message", "收藏失败!");
            map.put("flag", "false");
            e.printStackTrace();
        } finally {
            String json = JSONObject.toJSONString(map);
            try {
                response.getWriter().write(json);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }


    /**
     * 删除收藏
     * */
    @RequestMapping("/removeCollection.do")
    public void removeCollection(HttpServletResponse response, HttpSession session, Integer pid) {
        Map<String, Object> map = new HashMap<>();
        UserInfo userInfo = (UserInfo) session.getAttribute("user");
        try {
            if (service.deleteCollection(pid, userInfo.getUid())) {
                map.put("message", "取消收藏成功!");
                map.put("flag", "true");
            } else {
                map.put("message", "取消收藏失败!");
                map.put("flag", "false");
            }
        } catch (Exception e) {
            map.put("message", "取消收藏失败!");
            map.put("flag", "false");
        } finally {
            String json = JSONObject.toJSONString(map);
            try {
                response.getWriter().write(json);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 获取收藏列表
     * */
    @RequestMapping("/getCollections.do")
    public void getCollections(HttpSession session, HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
        try {
            UserInfo userInfo = (UserInfo) session.getAttribute("user");
            List<Collection> list = service.getCollection(userInfo.getUid());
            request.setAttribute("collections", list);
            request.getRequestDispatcher("/WEB-INF/jsp/collection.jsp").forward(request, response);
        } catch (Exception e) {
            Map<String, String> map = new HashMap<>();
            map.put("message", "服务器出了点小差错，请稍后再试");
            map.put("url", session.getServletContext().getContextPath() + "/index.do");
            request.setAttribute("map", map);
            request.getRequestDispatcher("/WEB-INF/jsp/message.jsp").forward(request, response);
        }
    }

}
