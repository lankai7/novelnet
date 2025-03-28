package com.novelnet.demo.service.impl;

import com.novelnet.demo.mapper.AnnouncementMapper;
import com.novelnet.demo.pojo.Announcement;
import com.novelnet.demo.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnnouncementServiceImpl implements AnnouncementService {

    @Autowired
    private AnnouncementMapper announcementMapper;

    @Override
    public List<Announcement> getAllAnnouncements() {
        return announcementMapper.getAllAnnouncements();
    }

    @Override
    public boolean addAnnouncement(Announcement announcement) {
        return announcementMapper.insertAnnouncement(announcement) > 0;
    }

    @Override
    public boolean deleteAnnouncement(Integer id) {
        return announcementMapper.deleteAnnouncement(id) > 0;
    }
}
