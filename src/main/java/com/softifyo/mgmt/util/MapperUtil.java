package com.softifyo.mgmt.util;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MapperUtil {

     public <D, E> D mapToDto(E entity, Class<D> dtoClass) {
        try {
            return dtoClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Error mapping entity to DTO: " + e.getMessage());
        }
    }

    public <E, D> E mapToEntity(D dto, Class<E> entityClass) {
        try {
            return entityClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Error mapping DTO to entity: " + e.getMessage());
        }
    }

    public <E, D> List<D> mapToDtoList(List<E> entities, Class<D> dtoClass) {
        return entities.stream()
                .map(entity -> mapToDto(entity, dtoClass))
                .toList();
    }
}
