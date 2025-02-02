package org.example.model.repository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.example.model.entity.Cliente;
import org.example.exception.MisExcepciones;
import org.example.model.repository.implementations.CRUD;
import org.example.model.repository.implementations.Logueo;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

public class ClienteRepository implements CRUD, Logueo {
    private final String CLIENTE_PATH = "src/main/resources/clientes.json";
    private final Gson gson = new Gson();
    public static Map<String, Cliente> mapClientes = new TreeMap<>();

    public ClienteRepository() {
    }

    @Override
    public void Register(Object c) {
        if (c instanceof Cliente) {
            Cliente client = (Cliente) c;
            mapClientes.put(client.getDni(), client);
            saveClientes();
        }
    }

    @Override
    public Cliente findUser(Object o) {
        if (o instanceof String) {
            Cliente p = mapClientes.get(o);
            if (p == null) {
                MisExcepciones.usuarioNoEncontrado();
            }
            return p;
        } else {
            MisExcepciones.datoInvalido();
        }
        return null;
    }


    public boolean dniCheck(String dni) {
        for (Cliente cliente : mapClientes.values()) {
            if (cliente.getDni().equals(dni)) {
                return true;
            }
        }
        return false;
    }

    public void saveClientes() {
        try (Writer writer = new FileWriter(CLIENTE_PATH)) {
            gson.toJson(mapClientes, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadClientes() {
        try (Reader reader = new FileReader(CLIENTE_PATH)) {
            Type userListType = new TypeToken<Map<String, Cliente>>() {
            }.getType();
            mapClientes = gson.fromJson(reader, userListType);
            if (mapClientes == null) {
                mapClientes = new TreeMap<>();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isActive(String dni) {
        Cliente cliente = mapClientes.get(dni);
        return cliente != null && cliente.isUserActive();
    }

    public void setActive(String dni, boolean active) {
        Cliente cliente = mapClientes.get(dni);
        if (cliente != null) {
            cliente.setUserActive(active);
        } else {
            throw new IllegalArgumentException("Cliente con DNI " + dni + " no encontrado");
        }
    }

    @Override
    public Object create() {
        return null;
    }

    @Override
    public Map<String, Cliente> read(Object o) {
        return mapClientes;
    }

    @Override
    public Object update(Object o) {

        if (o instanceof Cliente) {
            System.out.println("Actualizado Correctamente.");

            saveClientes();
        }
        return o;
    }

    @Override
    public void delete(Object o) {

    }
}
