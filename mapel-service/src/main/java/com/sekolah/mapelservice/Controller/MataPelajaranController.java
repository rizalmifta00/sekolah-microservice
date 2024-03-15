package com.sekolah.mapelservice.Controller;

import com.sekolah.mapelservice.Model.MataPelajaran;
import com.sekolah.mapelservice.Service.MataPelajaranService;
import com.sekolah.mapelservice.Util.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/mapel")
public class MataPelajaranController {


    @Autowired
    private MataPelajaranService mataPelajaranService;

    @GetMapping("/")
    public ResponseEntity<BaseResponse<List<MataPelajaran>>> getAllMataPelajaran() {
        List<MataPelajaran> mataPelajaranList = mataPelajaranService.getAllMataPelajaran();
        BaseResponse<List<MataPelajaran>> response = new BaseResponse<>("success", "Data retrieved successfully", mataPelajaranList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<MataPelajaran>> getMataPelajaranById(@PathVariable Long id) {
        Optional<MataPelajaran> mataPelajaran = mataPelajaranService.getMataPelajaranById(id);
        if (mataPelajaran.isPresent()) {
            BaseResponse<MataPelajaran> response = new BaseResponse<>("success", "Data retrieved successfully", mataPelajaran.get());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            BaseResponse<MataPelajaran> response = new BaseResponse<>("error", "Mata pelajaran not found", null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public ResponseEntity<BaseResponse<MataPelajaran>> createMataPelajaran(@RequestBody MataPelajaran mataPelajaran) {
        MataPelajaran createdMataPelajaran = mataPelajaranService.createMataPelajaran(mataPelajaran);
        BaseResponse<MataPelajaran> response = new BaseResponse<>("success", "Mata pelajaran created successfully", createdMataPelajaran);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse<MataPelajaran>> updateMataPelajaran(@PathVariable Long id, @RequestBody MataPelajaran mataPelajaran) {
        try {
            MataPelajaran updatedMataPelajaran = mataPelajaranService.updateMataPelajaran(id, mataPelajaran);
            BaseResponse<MataPelajaran> response = new BaseResponse<>("success", "Mata pelajaran updated successfully", updatedMataPelajaran);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (RuntimeException e) {
            BaseResponse<MataPelajaran> response = new BaseResponse<>("error", "Mata pelajaran not found", null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse<Void>> deleteMataPelajaran(@PathVariable Long id) {
        boolean result = mataPelajaranService.deleteMataPelajaran(id);
        if (result) {
            BaseResponse<Void> response = new BaseResponse<>("success", "Mata pelajaran deleted successfully", null);
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        } else {
            BaseResponse<Void> response = new BaseResponse<>("error", "Mata pelajaran not found", null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
}
