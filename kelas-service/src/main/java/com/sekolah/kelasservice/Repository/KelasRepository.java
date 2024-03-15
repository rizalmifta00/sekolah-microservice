package com.sekolah.kelasservice.Repository;

import com.sekolah.kelasservice.Model.Kelas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KelasRepository extends JpaRepository<Kelas,Long> {
}
