package com.swarna.collegeapi.service;

import com.swarna.collegeapi.entity.CourseMaterial;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CourseMaterialService {

    List<CourseMaterial> findAllCourseMaterials();

    CourseMaterial findCourseMaterialById(Long courseMaterialId);

    CourseMaterial addCourseMaterial(CourseMaterial courseMaterial);

    CourseMaterial updateCourseMaterial(CourseMaterial courseMaterial);

    void deleteCourseMaterial(Long courseMaterialId);
}
