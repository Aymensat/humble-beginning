package com.aymen_yassine.first_site.repository;

import com.aymen_yassine.first_site.entity.AppEnums;
import com.aymen_yassine.first_site.entity.StudentGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentGroupRepository extends JpaRepository<StudentGroup, Integer> {
    Object findByClassYearAndFieldAndClassLetter(AppEnums.ClassYearEnum classYearEnum, AppEnums.FieldEnum field, AppEnums.ClassLetterEnum classLetterEnum);
}
