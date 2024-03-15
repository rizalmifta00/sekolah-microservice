package com.sekolah.siswaservice.Controller;

import com.sekolah.siswaservice.Model.Siswa;
import com.sekolah.siswaservice.Service.SiswaService;
import com.sekolah.siswaservice.Util.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/siswa")
public class SiswaController {


    @Autowired
    private SiswaService siswaService;

    @GetMapping("/")
    public ResponseEntity<BaseResponse<List<Siswa>>> getAllSiswa() {

        try {
            List<Siswa> siswaList = siswaService.getAllSiswa();
            BaseResponse<List<Siswa>> response = new BaseResponse<>("success", "Data retrieved successfully", siswaList);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            BaseResponse<List<Siswa>> response = new BaseResponse<>("error", "Failed to retrieve data", null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<Siswa>> getSiswaById(@PathVariable Long id) {
        try {
            Siswa siswa = siswaService.getSiswaById(id);
            if (siswa != null) {
                BaseResponse<Siswa> response = new BaseResponse<>("success", "Data retrieved successfully", siswa);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                BaseResponse<Siswa> response = new BaseResponse<>("error", "Siswa not found", null);
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            BaseResponse<Siswa> response = new BaseResponse<>("error", "Failed to retrieve data", null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/")
    public ResponseEntity<BaseResponse<Siswa>> createSiswa(@RequestBody Siswa siswa) {
        try {
            Siswa createdSiswa = siswaService.createSiswa(siswa);
            BaseResponse<Siswa> response = new BaseResponse<>("success", "Siswa created successfully", createdSiswa);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            BaseResponse<Siswa> response = new BaseResponse<>("error", "Failed to create siswa", null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse<Siswa>> updateSiswa(@PathVariable Long id, @RequestBody Siswa updatedSiswa) {
        try {
            Siswa siswa = siswaService.updateSiswa(id, updatedSiswa);
            if (siswa != null) {
                BaseResponse<Siswa> response = new BaseResponse<>("success", "Siswa updated successfully", siswa);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                BaseResponse<Siswa> response = new BaseResponse<>("error", "Siswa not found", null);
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            BaseResponse<Siswa> response = new BaseResponse<>("error", "Failed to update siswa", null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse<Void>> deleteSiswa(@PathVariable Long id) {
        try {
            boolean result = siswaService.deleteSiswa(id);
            if (result) {
                BaseResponse<Void> response = new BaseResponse<>("success", "Siswa deleted successfully", null);
                return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
            } else {
                BaseResponse<Void> response = new BaseResponse<>("error", "Siswa not found", null);
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            BaseResponse<Void> response = new BaseResponse<>("error", "Failed to delete siswa", null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
