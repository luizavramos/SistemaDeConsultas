package com.tech_challenge.notificacao_service.consumer;

import com.tech_challenge.notificacao_service.model.Consulta;
import com.tech_challenge.notificacao_service.service.NotificacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ConsultaConsumer {

    private final NotificacaoService notificacaoService;

    @KafkaListener(topics = "consulta.eventos", groupId = "notificacao-service", containerFactory = "consultaFactory")
    public void consumir(Consulta consulta) {
        notificacaoService.enviarNotificacao(consulta);
    }
}
