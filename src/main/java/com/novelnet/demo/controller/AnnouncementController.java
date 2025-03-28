package com.novelnet.demo.controller;

import com.novelnet.demo.pojo.Announcement;
import com.novelnet.demo.pojo.Result;
import com.novelnet.demo.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/announcement")
public class AnnouncementController {

    @Autowired
    private AnnouncementService announcementService;

    // 获取公告列表
    @GetMapping("/list")
    public Result getAllAnnouncements() {
        List<Announcement> Announcements = announcementService.getAllAnnouncements();
        return new Result(200, Announcements, "成功获取公告");
    }

    // 添加公告
    @PostMapping("/add")
    public Map<String, Object> addAnnouncement(@RequestBody Announcement announcement) {
        Map<String, Object> response = new HashMap<>();
        boolean success = announcementService.addAnnouncement(announcement);
        response.put("code", success ? 200 : 500);
        response.put("msg", success ? "公告添加成功" : "公告添加失败");
        return response;
    }

    // 删除公告
    @DeleteMapping("/delete/{id}")
    public Map<String, Object> deleteAnnouncement(@PathVariable Integer id) {
        Map<String, Object> response = new HashMap<>();
        boolean success = announcementService.deleteAnnouncement(id);
        response.put("code", success ? 200 : 500);
        response.put("msg", success ? "公告删除成功" : "公告删除失败");
        return response;
    }
}
