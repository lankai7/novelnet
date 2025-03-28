package com.novelnet.demo.service;

import com.novelnet.demo.pojo.Announcement;
import java.util.List;

public interface AnnouncementService {
    List<Announcement> getAllAnnouncements();
    boolean addAnnouncement(Announcement announcement);
    boolean deleteAnnouncement(Integer id);
}