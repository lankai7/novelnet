package com.novelnet.demo.mapper;

import com.novelnet.demo.pojo.Announcement;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface AnnouncementMapper {
    List<Announcement> getAllAnnouncements();  // 查询所有公告
    int insertAnnouncement(Announcement announcement);  // 添加公告
    int deleteAnnouncement(Integer id);  // 删除公告
}
