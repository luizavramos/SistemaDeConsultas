package com.tech_challenge.agendamento_service.repository;

import com.tech_challenge.agendamento_service.model.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.time.LocalDateTime;

public interface ConsultaRepository extends JpaRepository<Consulta, String> {
    List<Consulta> findByPacienteId(String pacienteId);
    List<Consulta> findByPacienteIdAndDataHoraAfter(String pacienteId, LocalDateTime dataHora);

}

