package br.edu.fatecpg.tp.api.Api.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConverteEndereco implements IConverteEndereco {
    ObjectMapper objmapper = new ObjectMapper();
    @Override
    public <T> T enviarDados(String json, Class<T> classe) {
        try {
            return objmapper.readValue(json,classe);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
