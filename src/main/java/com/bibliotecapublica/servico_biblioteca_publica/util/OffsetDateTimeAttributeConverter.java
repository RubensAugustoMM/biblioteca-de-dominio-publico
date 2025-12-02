package com.bibliotecapublica.servico_biblioteca_publica.util;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Converter(autoApply = true)
public class OffsetDateTimeAttributeConverter implements AttributeConverter<OffsetDateTime, String> {

    private static final DateTimeFormatter OUT = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
    private static final DateTimeFormatter ISO_LOCAL = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    private static final DateTimeFormatter SQLITE_FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public String convertToDatabaseColumn(OffsetDateTime attribute) {
        if (attribute == null) return null;
        return attribute.format(OUT); 
    }

    @Override
    public OffsetDateTime convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isBlank()) return null;

        try {
            return OffsetDateTime.parse(dbData, OUT);
        } catch (DateTimeParseException ignored) {}

        try {
            LocalDateTime ldt = LocalDateTime.parse(dbData, ISO_LOCAL);
            return ldt.atOffset(ZoneOffset.systemDefault().getRules().getOffset(ldt));
        } catch (DateTimeParseException ignored) {}

        try {
            LocalDateTime ldt = LocalDateTime.parse(dbData, SQLITE_FMT);
            return ldt.atOffset(ZoneOffset.systemDefault().getRules().getOffset(ldt));
        } catch (DateTimeParseException ex) {
            throw new IllegalArgumentException("Cannot parse DB timestamp: " + dbData, ex);
        }
    }
}