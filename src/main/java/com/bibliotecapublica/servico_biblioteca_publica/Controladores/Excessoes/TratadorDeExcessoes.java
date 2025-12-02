package com.bibliotecapublica.servico_biblioteca_publica.Controladores.Excessoes;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import org.springframework.web.bind.annotation.ExceptionHandler;
import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class TratadorDeExcessoes {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<RespostaErro> tratarTodosErros(Exception ex, HttpServletRequest requisicao) {
        RespostaErro respostaErro = new RespostaErro();
        respostaErro.setMensagemErro(ex.getMessage());
        respostaErro.setCodigo("INTERNAL_ERROR");
        respostaErro.setCaminho( requisicao.getRequestURI());
        respostaErro.setTraceId((String) requisicao.getAttribute("TRACE_ID"));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respostaErro);
    }
}
