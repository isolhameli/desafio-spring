package com.mercadolibre.desafiospring.services;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UtilServiceImpl implements UtilService{

    public Sort getSort(String order, Class responseType, String defaultProperty){
        String direction;
        String property;
        List declaredFields = Arrays.stream(responseType.getDeclaredFields()).map(el -> el.getName()).collect(Collectors.toList());

        String[] splitOrder = order.toLowerCase().split("_");
        try {
            property = checkPropertyExistence(splitOrder[0], declaredFields);
            if (property == null){
                property = defaultProperty;
            }
        } catch (IndexOutOfBoundsException e){
            property = defaultProperty;
        }
        try{
            direction = splitOrder[1];
            if (!direction.equals("desc")){
                direction = "asc";
            }
        } catch (IndexOutOfBoundsException e){
            direction = "asc";
        }
        Sort.Direction sortDirection = direction.equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        return Sort.by(sortDirection,property);
    }

    private String checkPropertyExistence(String property, List<String> fields){

        for (int i = 0; i < fields.size(); i++){
            if(fields.get(i).toLowerCase().equals(property)){
                return fields.get(i);
            }
        }

        if (property.endsWith("id") || property.startsWith("id")){
            return "id";
        } else if (property.endsWith("name")) {
            return "userName";
        }

        return null;
    }
}
