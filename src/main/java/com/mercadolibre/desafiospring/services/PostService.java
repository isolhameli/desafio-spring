package com.mercadolibre.desafiospring.services;

import com.mercadolibre.desafiospring.models.Post;
import com.mercadolibre.desafiospring.requests.PostRequest;

public interface PostService {


    Post create(PostRequest postRequest);
}
