package labs.lab3.tree;

import labs.lab3.model.Course;
import labs.lab3.model.Stipendia;
import labs.lab3.model.Student;

import java.util.List;
import java.util.Map;

/**/
public interface IMyBinaryTree{

    /**/
    Student add(Integer key, Student student);

    /**/
    String getStringTreeParalel();

    /**/
    String getStringTreeShirina();

    int size();

    /**/
    List<Student> findByCriteria(Course course, Stipendia stipuha);

    /**/
    Student remove(Integer key);


}
