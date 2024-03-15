package com.sekolah.kelasservice.Service;

import com.sekolah.kelasservice.Model.Kelas;
import com.sekolah.kelasservice.Repository.KelasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KelasService {

    @Autowired
    private KelasRepository kelasRepository;

    public List<Kelas> getAllKelas() {
        return kelasRepository.findAll();
    }

    public Optional<Kelas> getKelasById(Long id) {
        return kelasRepository.findById(id);
    }

    public Kelas createKelas(Kelas kelas) {
        return kelasRepository.save(kelas);
    }

    public Kelas updateKelas(Long id, Kelas updatedKelas) {
        Optional<Kelas> optionalKelas = kelasRepository.findById(id);
        if (optionalKelas.isPresent()) {
            Kelas kelas = optionalKelas.get();
            kelas.setNama(updatedKelas.getNama());
            kelas.setNamaAngka(updatedKelas.getNamaAngka());
            return kelasRepository.save(kelas);
        } else {
            throw new RuntimeException("Kelas not found");
        }
    }

    public boolean deleteKelas(Long id) {
        Optional<Kelas> optionalKelas = kelasRepository.findById(id);
        if (optionalKelas.isPresent()) {
            kelasRepository.delete(optionalKelas.get());
            return true;
        } else {
            return false;
        }
    }

}
