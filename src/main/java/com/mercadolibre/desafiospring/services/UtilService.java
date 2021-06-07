package com.mercadolibre.desafiospring.services;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


public interface UtilService {

    Sort getSort(String order, Class responseType, String defaultProperty);

}
