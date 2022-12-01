package org.tlc.microservices.reportingservice.mapper;

public interface ModelToDTO<M, T> { public T convertToDTO(M obj);}