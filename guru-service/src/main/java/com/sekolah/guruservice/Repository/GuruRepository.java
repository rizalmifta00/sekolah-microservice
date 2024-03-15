package com.sekolah.guruservice.Repository;

import com.sekolah.guruservice.Model.Guru;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuruRepository extends JpaRepository<Guru,Long> {
}
