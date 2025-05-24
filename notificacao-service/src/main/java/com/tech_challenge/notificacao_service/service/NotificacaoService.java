package com.tech_challenge.notificacao_service.service;

import com.tech_challenge.notificacao_service.model.Consulta;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotificacaoService {
    public void enviarNotificacao(Consulta consulta) {
        log.info("Enviando notificação para paciente ID={} sobre consulta em {}", consulta.getPacienteId(), consulta.getDataHora());
    }}
