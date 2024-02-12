package com.freeuni.coursewhisperer.repository;

import com.freeuni.coursewhisperer.data.entity.SubjectEntity;
import com.freeuni.coursewhisperer.data.enums.ESchool;
import com.freeuni.coursewhisperer.data.enums.ESemester;
import com.freeuni.coursewhisperer.data.model.Lecturer;
import com.freeuni.coursewhisperer.data.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends AbstractRepository<SubjectEntity, Long> {
    SubjectEntity findByName(String name);

    @Query("select s from SubjectEntity s where " +
            "(:name is null or s.name = :name) and " +
            "(:code is null or s.code = :code) and " +
            "(:schoolName is null or s.schoolName = :schoolName) and " +
            "(:creditScore is null or s.creditScore = :creditScore) and " +
            "(:lecturer is null or s.lecturer = :lecturer) and " +
            "(:semester is null or s.semester = :semester)")
    List<SubjectEntity> search(String name, String code, ESchool schoolName,
                               Integer creditScore, Lecturer lecturer, ESemester semester);
}
