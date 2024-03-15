package com.sekolah.guruservice.Controller;

import com.sekolah.guruservice.Model.Guru;
import com.sekolah.guruservice.Service.GuruService;
import com.sekolah.guruservice.Util.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/guru")
public class GuruController {
    @Autowired
    private GuruService guruService;

    @GetMapping("/")
    public ResponseEntity<BaseResponse<List<Guru>>> getAllGuru() {
        List<Guru> guruList = guruService.getAllGuru();
        BaseResponse<List<Guru>> response = new BaseResponse<>("success", "Data retrieved successfully", guruList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<Guru>> getGuruById(@PathVariable Long id) {
        Optional<Guru> guru = guruService.getGuruById(id);
        if (guru.isPresent()) {
            BaseResponse<Guru> response = new BaseResponse<>("success", "Data retrieved successfully", guru.get());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            BaseResponse<Guru> response = new BaseResponse<>("error", "Guru not found", null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public ResponseEntity<BaseResponse<Guru>> createGuru(@RequestBody Guru guru) {
        Guru createdGuru = guruService.createGuru(guru);
        BaseResponse<Guru> response = new BaseResponse<>("success", "Guru created successfully", createdGuru);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse<Guru>> updateGuru(@PathVariable Long id, @RequestBody Guru guru) {
        try {
            Guru updatedGuru = guruService.updateGuru(id, guru);
            BaseResponse<Guru> response = new BaseResponse<>("success", "Guru updated successfully", updatedGuru);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (RuntimeException e) {
            BaseResponse<Guru> response = new BaseResponse<>("error", "Guru not found", null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse<Void>> deleteGuru(@PathVariable Long id) {
        boolean result = guruService.deleteGuru(id);
        if (result) {
            BaseResponse<Void> response = new BaseResponse<>("success", "Guru deleted successfully", null);
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        } else {
            BaseResponse<Void> response = new BaseResponse<>("error", "Guru not found", null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
}
