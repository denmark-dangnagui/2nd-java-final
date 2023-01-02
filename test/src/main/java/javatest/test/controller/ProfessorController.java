package javatest.test.controller;

import javatest.test.dto.ProfessorLectureDto;
import javatest.test.service.ApplyService;
import javatest.test.service.ProfessorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/professor")
public class ProfessorController {
    private final ProfessorService professorService;
    @GetMapping("/{professor}")
    public ResponseEntity<?> getAllProfessorLecture(@PathVariable String professor){
        List<ProfessorLectureDto> lectures = professorService.professorLecture(professor);
        return new ResponseEntity<>(lectures, HttpStatus.OK);
    }
}
