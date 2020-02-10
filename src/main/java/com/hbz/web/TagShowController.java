package com.hbz.web;

import com.hbz.pojo.BlogQuery;
import com.hbz.pojo.Tag;
import com.hbz.pojo.Type;
import com.hbz.service.BlogService;
import com.hbz.service.TagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class TagShowController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TagsService tagsService;

    @GetMapping("tags/{id}")
    public String listTag(@PageableDefault(size = 8, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                          @PathVariable Long id, Model model){

    List<Tag> tags = tagsService.listTagTop(10000);
        if(id==-1)

    {
        id = tags.get(0).getId();
    }

    BlogQuery blogQuery = new BlogQuery();
        blogQuery.setTypeId(id);
        model.addAttribute("tags",tags);
        model.addAttribute("page",blogService.listBlog(id,pageable));
        model.addAttribute("activeTagId",id);
        return"tags";
}

}
