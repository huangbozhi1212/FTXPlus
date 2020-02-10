package com.hbz.service;

import com.hbz.pojo.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TagsService {
    Tag saveTags(Tag tag);

    Tag getTags(Long id);

    Tag getTagsByName(String name);

    Page<Tag> listTags(Pageable pageable);

    List<Tag> listTag();

    List<Tag> listTag(String ids);

    List<Tag> listTagTop(Integer size);

    Tag updateTags(Long id,Tag tag);

    void deleteTags(Long id);
}
