package org.example.model.repository.implementations;

import org.example.exception.MisExcepciones;

public interface View<T> {

    T pedirEntero(T t);
    T pedirDouble(T t);
    T pedirDato(T t)throws MisExcepciones;


}

