package site.gongtong.Image.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import site.gongtong.s3.FileFolder;
import site.gongtong.s3.FileService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/image")
@RequiredArgsConstructor
public class ImageController {
    private final FileService fileService;

    @PostMapping("/upload")
    public ResponseEntity<List<String>> upload(@RequestBody List<MultipartFile> fileList) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < fileList.size(); i++) {
            MultipartFile file = fileList.get(i);
            String str = fileService.uploadFile(file, FileFolder.REVIEW_IMAGES);
            list.add(str);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
