package br.edu.fatecpg.tp.api.Api.service;

public interface IConverteEndereco {
    <T> T enviarDados(String json, Class<T> classe);
}
