package com.team_damda.domain.controller;

import com.team_damda.domain.dto.ClassReservationDto;
import com.team_damda.domain.dto.ClassReviewDto;
import com.team_damda.domain.service.ClassReservationService;
import com.team_damda.domain.service.ClassReviewService;
import com.team_damda.domain.service.ClassService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
public class ReviewController {

    private final ClassService classService;
    private final ClassReservationService classReservationService;
    private final ClassReviewService classReviewService;

    @PostMapping("/review")
    public ResponseEntity<String> createReview(@RequestBody ClassReviewDto reviewDto) {
        classReviewService.createReview(reviewDto);
        log.info("data: {}",reviewDto);
        return ResponseEntity.ok("리뷰작성이 완료되었습니다.");
    }

    @GetMapping("/review/{memberId}")
    public List<ClassReviewDto> getMemberReview(@PathVariable Long memberId) {
        return classReviewService.getMemberReview(memberId);
    }

    @PostMapping("/review/delete/{id}")
    public void deleteReview(@PathVariable Long id) {
        classReviewService.deleteReview(id);
    }
}
