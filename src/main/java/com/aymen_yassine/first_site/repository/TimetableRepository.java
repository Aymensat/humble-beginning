package com.aymen_yassine.first_site.repository;

import java.util.List;

import com.aymen_yassine.first_site.entity.AppEnums;
import com.aymen_yassine.first_site.entity.Timetable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimetableRepository extends JpaRepository<Timetable, Integer> {

     List<Timetable> findByStudentGroupClassYearAndStudentGroupFieldAndStudentGroupClassLetter(
             AppEnums.ClassYearEnum studentGroup_classYear, AppEnums.FieldEnum studentGroup_field, AppEnums.ClassLetterEnum studentGroup_classLetter);
}
