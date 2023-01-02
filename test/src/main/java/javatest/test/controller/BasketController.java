package javatest.test.controller;

import javatest.test.dto.ApplyDto;
import javatest.test.exception.NotYetException;
import javatest.test.service.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/basket")
public class BasketController {

    private final BasketService basketService;

    //장바구니 예비 수강 신청
    @PostMapping
    public ResponseEntity<?> applyBasketLecture(@RequestBody ApplyDto applyDto) {
        basketService.postTempBasket(applyDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    //장바구니로 실제 수강 신청
    @PutMapping
    public ResponseEntity<?> applyRealBasketLecture(){
        basketService.postBasketRealLecture();
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
