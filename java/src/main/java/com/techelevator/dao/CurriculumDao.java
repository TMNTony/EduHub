package com.techelevator.dao;

import com.techelevator.model.Curriculum;

import java.util.List;

public interface CurriculumDao {

    List<Curriculum> getCurriculum();

    Curriculum getCurriculumById(int curriculumId);
    List<Curriculum> getCurriculumByCourseId(int courseId);

    Curriculum getCurriculumByName(String title);

    Curriculum createCurriculum(Curriculum curriculum);

    Curriculum updateCurriculum(Curriculum curriculum);

    boolean deleteCurriculum(int curriculumId);
}
