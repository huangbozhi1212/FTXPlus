package com.hbz.web.admin;

import com.hbz.pojo.Tag;
import com.hbz.pojo.Type;
import com.hbz.service.TagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class TagsController {
    @Autowired
    private TagsService tagsService;

    @GetMapping("/tags")
    public String findAllTags(@PageableDefault(size = 10, sort = {"id"}, direction = Sort.Direction.DESC)
                                      Pageable pageable, Model model) {
        model.addAttribute("page", tagsService.listTags(pageable));

        return "admin/tags";
    }
    @GetMapping("/tags/{id}/input")
    public String editInput(@PathVariable Long id,Model model){
        model.addAttribute("tag",tagsService.getTags(id));
        return "admin/tags-input";
    }

    @GetMapping("/tags/input")
    public String insertTag(Model model) {
        model.addAttribute("tag", new Tag());
        return "admin/tags-input";
    }

    @PostMapping("/tags")
    public String insertTags(@Valid Tag tag, BindingResult result, RedirectAttributes attributes) {
        Tag tags = tagsService.getTagsByName(tag.getName());
        if (tags != null) {
            result.rejectValue("name", "nameError", "不能重复添加标签");
        }
        if (result.hasErrors()) {
            return "admin/tags-input";
        }
        Tag tag1 = tagsService.saveTags(tag);
        if (tag1 == null) {
            attributes.addFlashAttribute("message", "添加失败");
        } else {
            attributes.addFlashAttribute("message", "添加成功");
        }

        return "redirect:/admin/tags";
    }
    @PostMapping("tags/{id}")
    public String updateTags(@Valid Tag tag,BindingResult result,@PathVariable Long id, RedirectAttributes attributes){
        Tag tags = tagsService.getTagsByName(tag.getName());
        if (tags != null) {
            result.rejectValue("name", "nameError", "不能重复添加标签");
        }
        if (result.hasErrors()) {
            return "admin/tags-input";
        }
        Tag t = tagsService.updateTags(id,tag);
        if (t == null) {
            attributes.addFlashAttribute("message", "添加标签失败");
        } else {
            attributes.addFlashAttribute("message", "添加标签成功");
        }

        return "redirect:/admin/tags";
    }
    @GetMapping("/tags/{id}/delete")
    public String delInput(@PathVariable Long id,RedirectAttributes attributes){

        tagsService.deleteTags(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/tags";
    }
}
