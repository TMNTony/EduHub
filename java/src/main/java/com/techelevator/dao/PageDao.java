package com.techelevator.dao;

import com.techelevator.model.Page;

import java.util.List;

public interface PageDao {
    List<Page> getPagesByCurriculumId(int curriculumId);

    Page getPageById(int pageId);

    Page createPage(Page page);

    Page updatePage(Page page);

    boolean deletePage(int pageId);
}
