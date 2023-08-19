package com.techelevator.dao;
import com.techelevator.model.Homework;
import java.util.List;
public interface HomeWorkDao {

    List<Homework>getHomeWorkByParentId(int parentId);


    Homework getHomeworkById(int homeworkId);
    Homework getHomeworkByUserId(int userId);

    Homework createHomework(Homework homework);

    Homework updateHomework(Homework homework);

    boolean deleteHomeworkById(int homeworkId);
    boolean deleteAllHomework();
}
