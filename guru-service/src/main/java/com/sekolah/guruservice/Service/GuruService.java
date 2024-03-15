package com.sekolah.guruservice.Service;

import com.sekolah.guruservice.Model.Guru;
import com.sekolah.guruservice.Repository.GuruRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GuruService {
    @Autowired
    private GuruRepository guruRepository;

    public List<Guru> getAllGuru() {
        return guruRepository.findAll();
    }

    public Optional<Guru> getGuruById(Long id) {
        return guruRepository.findById(id);
    }

    public Guru createGuru(Guru guru) {
        return guruRepository.save(guru);
    }

    public Guru updateGuru(Long id, Guru updatedGuru) {
        Optional<Guru> optionalGuru = guruRepository.findById(id);
        if (optionalGuru.isPresent()) {
            Guru guru = optionalGuru.get();
            guru.setNama(updatedGuru.getNama());
            return guruRepository.save(guru);
        } else {
            throw new RuntimeException("Guru not found");
        }
    }

    public boolean deleteGuru(Long id) {
        Optional<Guru> optionalGuru = guruRepository.findById(id);
        if (optionalGuru.isPresent()) {
            guruRepository.delete(optionalGuru.get());
            return true;
        } else {
            return false;
        }
    }
}
