package com.tech_challenge.agendamento_service.service;

import com.tech_challenge.agendamento_service.dto.ConsultaInput;
import com.tech_challenge.agendamento_service.kafka.ConsultaProducer;
import com.tech_challenge.agendamento_service.model.Consulta;
import com.tech_challenge.agendamento_service.repository.ConsultaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ConsultaService {

    private final ConsultaRepository repository;
    private final ConsultaProducer kafkaProducer;

    public Consulta criarConsulta(ConsultaInput input) {
        Consulta consulta = Consulta.builder()
                .pacienteId(input.pacienteId())
                .medicoId(input.medicoId())
                .dataHora(input.dataHora())
                .observacoes(input.observacoes())
                .build();

        Consulta salva = repository.save(consulta);

        kafkaProducer.enviarEventoConsultaCriada(salva);
        return salva;
    }

    public List<Consulta> buscarPorPaciente(String pacienteId) {
        return repository.findByPacienteId(pacienteId);
    }

    public Consulta atualizarConsulta(String id, ConsultaInput input) {
        Consulta consulta = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consulta não encontrada"));

        consulta.setPacienteId(input.pacienteId());
        consulta.setMedicoId(input.medicoId());
        consulta.setDataHora(input.dataHora());
        consulta.setObservacoes(input.observacoes());

        return repository.save(consulta);
    }

    public List<Consulta> buscarFuturasPorPaciente(String pacienteId) {
        return repository.findByPacienteIdAndDataHoraAfter(pacienteId, LocalDateTime.now());
    }
}