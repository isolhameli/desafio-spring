package com.mercadolibre.desafiospring.views;


public class PostView {

    public interface Simple { }
    public interface Detailed extends Simple { }
    public interface PromotionalDetailed extends Detailed { }
    public interface PromotionalSimple extends Simple { }
}
