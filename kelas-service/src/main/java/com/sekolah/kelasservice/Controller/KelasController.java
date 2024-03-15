package com.sekolah.kelasservice.Controller;

import com.sekolah.kelasservice.Model.Kelas;
import com.sekolah.kelasservice.Service.KelasService;
import com.sekolah.kelasservice.Util.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/kelas")
public class KelasController {

    private KelasService kelasService;
    @GetMapping("/")
    public ResponseEntity<BaseResponse<List<Kelas>>> getAllKelas() {
        List<Kelas> kelasList = kelasService.getAllKelas();
        BaseResponse<List<Kelas>> response = new BaseResponse<>("success", "Data retrieved successfully", kelasList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<Kelas>> getKelasById(@PathVariable Long id) {
        Optional<Kelas> kelas = kelasService.getKelasById(id);
        if (kelas.isPresent()) {
            BaseResponse<Kelas> response = new BaseResponse<>("success", "Data retrieved successfully", kelas.get());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            BaseResponse<Kelas> response = new BaseResponse<>("error", "Kelas not found", null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public ResponseEntity<BaseResponse<Kelas>> createKelas(@RequestBody Kelas kelas) {
        Kelas createdKelas = kelasService.createKelas(kelas);
        BaseResponse<Kelas> response = new BaseResponse<>("success", "Kelas created successfully", createdKelas);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse<Kelas>> updateKelas(@PathVariable Long id, @RequestBody Kelas kelas) {
        try {
            Kelas updatedKelas = kelasService.updateKelas(id, kelas);
            BaseResponse<Kelas> response = new BaseResponse<>("success", "Kelas updated successfully", updatedKelas);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (RuntimeException e) {
            BaseResponse<Kelas> response = new BaseResponse<>("error", "Kelas not found", null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse<Void>> deleteKelas(@PathVariable Long id) {
        boolean result = kelasService.deleteKelas(id);
        if (result) {
            BaseResponse<Void> response = new BaseResponse<>("success", "Kelas deleted successfully", null);
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        } else {
            BaseResponse<Void> response = new BaseResponse<>("error", "Kelas not found", null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
}
