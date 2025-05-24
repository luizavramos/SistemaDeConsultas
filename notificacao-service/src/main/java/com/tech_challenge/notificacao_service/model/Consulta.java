package com.tech_challenge.notificacao_service.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Consulta {
    private String id;
    private String pacienteId;
    private String medicoId;
    private LocalDateTime dataHora;
    private String observacoes;
}
