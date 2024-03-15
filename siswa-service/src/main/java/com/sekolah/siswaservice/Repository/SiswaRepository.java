package com.sekolah.siswaservice.Repository;

import com.sekolah.siswaservice.Model.Siswa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SiswaRepository extends JpaRepository<Siswa,Long> {
}
