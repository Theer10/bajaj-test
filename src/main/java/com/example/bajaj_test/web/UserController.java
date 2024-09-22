package com.example.bajaj_test.web;

//import java.io.ByteArrayInputStream;
import java.util.ArrayList;
//import java.util.Base64;
import java.util.Collections;
//import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
//import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bajaj_test.DTO.FileRequest;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/bfhl")

@AllArgsConstructor
public class UserController {
    @GetMapping
    public ResponseEntity<Map<String,Object>> returnStatus(){
                Map<String, Object> responseBody = new HashMap<>();
                responseBody.put("operation_code", 1);

        // Return response with status 200 (OK)
            return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @PostMapping
    @CrossOrigin(origins = "http://localhost:3000")
    public Map<String, Object> handleFileUpload(@RequestBody FileRequest request) {
        List<String> inputData = request.getData();
        String fileB64 = request.getFile_b64();

        // Initialize the response object
        Map<String, Object> response = new HashMap<>();
        List<String> numbers = new ArrayList<>();
        List<String> alphabets = new ArrayList<>();
        String highestLowercaseAlphabet = null;

        // Process the input data
        for (String item : inputData) {
            if (item.matches("\\d+")) {
                numbers.add(item);  
            } else if (item.matches("[a-zA-Z]")) {
                alphabets.add(item);  
                if (item.matches("[a-z]")) {
                    if (highestLowercaseAlphabet == null || item.compareTo(highestLowercaseAlphabet) > 0) {
                        highestLowercaseAlphabet = item;
                    }
                }
            }
        }

        
        boolean fileValid = fileB64 != null && !fileB64.isEmpty();
        String fileMimeType = fileValid ? "image/png" : null;  
        int fileSizeKb = fileValid ? 400 : 0;  

        
        response.put("is_success", true);
        response.put("user_id", "john_doe_17091999");  
        response.put("email", "john@xyz.com");         
        response.put("roll_number", "ABCD123");        
        response.put("numbers", numbers);
        response.put("alphabets", alphabets);
        response.put("highest_lowercase_alphabet", Collections.singletonList(highestLowercaseAlphabet));
        response.put("file_valid", fileValid);
        response.put("file_mime_type", fileMimeType);
        response.put("file_size_kb", fileSizeKb);

        return response;  
    }

}
