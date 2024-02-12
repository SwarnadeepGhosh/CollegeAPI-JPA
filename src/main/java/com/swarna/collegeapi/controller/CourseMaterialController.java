package com.swarna.collegeapi.controller;

import com.swarna.collegeapi.entity.CourseMaterial;
import com.swarna.collegeapi.service.CourseMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/course-materials")
public class CourseMaterialController {

    @Autowired
    CourseMaterialService courseMaterialService;

    @GetMapping("/")
    public List<CourseMaterial> findAllCourseMaterials() {
        return courseMaterialService.findAllCourseMaterials();
    }

    @GetMapping("/{courseMaterialId}")
    public CourseMaterial findCourseMaterialById(@PathVariable("courseMaterialId") Long courseMaterialId) {
        return courseMaterialService.findCourseMaterialById(courseMaterialId);
    }

    @PostMapping("/")
    public CourseMaterial addCourseMaterial(@RequestBody CourseMaterial courseMaterial) {
        return courseMaterialService.addCourseMaterial(courseMaterial);
    }

    @PutMapping("/")
    public CourseMaterial updateCourseMaterial(@RequestBody CourseMaterial courseMaterial) {
        return courseMaterialService.updateCourseMaterial(courseMaterial);
    }

    @DeleteMapping("/{courseMaterialId}")
    public void deleteCourseMaterial(@PathVariable("courseMaterialId") Long courseMaterialId) {
        courseMaterialService.deleteCourseMaterial(courseMaterialId);
    }
}
