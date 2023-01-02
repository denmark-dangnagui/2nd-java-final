package javatest.test.controller;

import javatest.test.dto.ApplyDto;
import javatest.test.dto.ProfessorLectureDto;
import javatest.test.dto.WithdrawDto;
import javatest.test.entity.lecture.Lecture;
import javatest.test.service.ApplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/apply")
public class ApplyController {

    private final ApplyService applyService;

    @PostMapping
    public ResponseEntity<?> realTake(@RequestBody ApplyDto applyDto){
        applyService.applyLecture(applyDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/withdraw")
    public ResponseEntity<?> withdrawLecture(@RequestBody WithdrawDto withdrawDto){
        applyService.withdrawLecture(withdrawDto.getMemberId(), withdrawDto.getLectureId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/fail")
    public ResponseEntity<?> deleteAllZZIM(){
        applyService.deleteApplication();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
