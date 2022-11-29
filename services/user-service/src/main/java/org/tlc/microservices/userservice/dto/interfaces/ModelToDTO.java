package org.tlc.microservices.userservice.dto.interfaces;

public interface ModelToDTO<M, T> { public T convertToDTO(M obj);}
