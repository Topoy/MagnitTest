package main.controller;

import main.model.Test;
import main.service.TestService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@ComponentScan({"main.service"})
@RestController
@RequestMapping("/api/test")
public class TestController {

    private final TestService testService;

    public TestController(TestService testService) {this.testService = testService;}

    @GetMapping("")
    public ResponseEntity<List<Test>> getTestContent() {
        List<Test> testContent = testService.getTestContent();
        if (testContent.isEmpty()) {
            return new ResponseEntity<>(testContent, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(testContent, HttpStatus.OK);
    }

    @PostMapping("/fill")
    public ResponseEntity<String> fillTestContent(@RequestParam int fieldsNumber) {
        List<Test> newTestContent = testService.fillTestContent(fieldsNumber);
        if (newTestContent.isEmpty()) {
            String errorMessage = "Input parameter is not correct. Please check it.";
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Successful", HttpStatus.OK);
    }

    @GetMapping("/xml")
    public ResponseEntity<String> createXML() {
        testService.createXML();
        return new ResponseEntity<>("XML document created", HttpStatus.OK);
    }

    @GetMapping("/xml/convert")
    public ResponseEntity<String> transformXML() {
        testService.transformXML();
        return new ResponseEntity<>("XML document converted", HttpStatus.OK);
    }

    @GetMapping("/xml/fieldSum")
    public ResponseEntity<Long> getFieldValuesSum() {
        Long fieldValuesSum = testService.getFieldValuesSum();
        System.out.println("Sum of all values equals to " + fieldValuesSum);
        return new ResponseEntity<>(fieldValuesSum, HttpStatus.OK);
    }
}
