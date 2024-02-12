package com.swarna.collegeapi.service.impl;

import com.swarna.collegeapi.entity.CourseMaterial;
import com.swarna.collegeapi.repository.CourseMaterialRepository;
import com.swarna.collegeapi.service.CourseMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseMaterialServiceImpl implements CourseMaterialService {

    @Autowired
    CourseMaterialRepository courseMaterialRepository;

    @Override
    public List<CourseMaterial> findAllCourseMaterials() {
        return courseMaterialRepository.findAll();
    }

    @Override
    public CourseMaterial findCourseMaterialById(Long courseMaterialId) {
        Optional<CourseMaterial> optional = courseMaterialRepository.findById(courseMaterialId);
        return optional.orElse(null);
    }

    /**
     * @param courseMaterial
     * @return
     */
    @Override
    public CourseMaterial addCourseMaterial(CourseMaterial courseMaterial) {
        return courseMaterialRepository.save(courseMaterial);
    }

    /**
     * @param courseMaterial
     * @return
     */
    @Override
    public CourseMaterial updateCourseMaterial(CourseMaterial courseMaterial) {
        return courseMaterialRepository.save(courseMaterial);
    }

    /**
     * @param courseMaterialId
     */
    @Override
    public void deleteCourseMaterial(Long courseMaterialId) {
        courseMaterialRepository.deleteById(courseMaterialId);
    }
}
