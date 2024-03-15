package com.sekolah.mapelservice.Service;

import com.sekolah.mapelservice.Model.MataPelajaran;
import com.sekolah.mapelservice.Repository.MataPelajaranRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MataPelajaranService {

    @Autowired
    private MataPelajaranRepository mataPelajaranRepository;

    public List<MataPelajaran> getAllMataPelajaran() {
        return mataPelajaranRepository.findAll();
    }

    public Optional<MataPelajaran> getMataPelajaranById(Long id) {
        return mataPelajaranRepository.findById(id);
    }

    public MataPelajaran createMataPelajaran(MataPelajaran mataPelajaran) {
        return mataPelajaranRepository.save(mataPelajaran);
    }

    public MataPelajaran updateMataPelajaran(Long id, MataPelajaran updatedMataPelajaran) {
        Optional<MataPelajaran> optionalMataPelajaran = mataPelajaranRepository.findById(id);
        if (optionalMataPelajaran.isPresent()) {
            MataPelajaran mataPelajaran = optionalMataPelajaran.get();
            mataPelajaran.setNama(updatedMataPelajaran.getNama());
            mataPelajaran.setDeskripsi(updatedMataPelajaran.getDeskripsi());
            return mataPelajaranRepository.save(mataPelajaran);
        } else {
            throw new RuntimeException("Mata pelajaran not found");
        }
    }

    public boolean deleteMataPelajaran(Long id) {
        Optional<MataPelajaran> optionalMataPelajaran = mataPelajaranRepository.findById(id);
        if (optionalMataPelajaran.isPresent()) {
            mataPelajaranRepository.delete(optionalMataPelajaran.get());
            return true;
        } else {
            return false;
        }
    }
}
