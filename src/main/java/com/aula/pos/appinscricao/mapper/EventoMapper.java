package com.aula.pos.appinscricao.mapper;

import com.aula.pos.appinscricao.dto.EventoRequest;
import com.aula.pos.appinscricao.dto.EventoResponse;
import com.aula.pos.appinscricao.model.Evento;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Component
public class EventoMapper {

    public Evento toEntity(EventoRequest request) {
        return new Evento(
                null,
                request.nome().trim(),
                request.dataHoraEvento(),
                request.local().trim(),
                request.cidade().trim(),
                request.estado().trim().toUpperCase(),
                request.qtde()
        );
    }

    public void updateEntity(EventoRequest request, Evento evento) {
        evento.setNome(request.nome().trim());
        evento.setDataHoraEvento(request.dataHoraEvento());
        evento.setLocal(request.local().trim());
        evento.setCidade(request.cidade().trim());
        evento.setEstado(request.estado().trim().toUpperCase());
        evento.setQtde(request.qtde());
    }

    public EventoResponse toResponse(Evento evento) {
        return new EventoResponse(
                evento.getId(),
                evento.getNome(),
                evento.getDataHoraEvento(),
                evento.getLocal(),
                evento.getCidade(),
                evento.getEstado(),
                evento.getQtde(),
                calcularDiasParaEvento(evento)
        );
    }

    private Long calcularDiasParaEvento(Evento evento) {
        if (evento.getDataHoraEvento() == null) {
            return null;
        }
        // 0 no dia do evento, negativo se o evento já passou
        return ChronoUnit.DAYS.between(LocalDate.now(), evento.getDataHoraEvento().toLocalDate());
    }
}
