package org.example.model.repository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.example.model.entity.Book;
import org.example.model.entity.Config;
import org.example.model.entity.Persona;

import java.io.*;
import java.lang.reflect.Type;

public class ConfigRepository {
    private static final String CONFIG_FILE_PATH = "src/main/resources/config.json";
    private static final String PATH_FILE_ID = "src/main/resources/idPersonas.json";
    private static final String PATH_FILE_IDBOOK = "src/main/resources/idBooks.json";
    private static Config config;
    private static Gson gson = new Gson();

    public static Config loadConfig() {
        if (config == null) {
            try (Reader reader = new FileReader(CONFIG_FILE_PATH)) {
                config = gson.fromJson(reader, Config.class);
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("Failed to load configuration", e);
            }
        }
        return config;
    }

    public static void cargarIdPersonas() {
        try (Reader reader = new FileReader(PATH_FILE_ID)) {
            Type typelist = new TypeToken<Integer>() {
            }.getType();
            Persona.setId(gson.fromJson(reader, typelist));
            if(Persona.getId() == null)
            {
                Persona.setId(0);
            }

        } catch (FileNotFoundException e) {
            System.out.println(e.fillInStackTrace());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void guardarIDPersonas() {

        try (Writer writer = new FileWriter(PATH_FILE_ID)) {
            gson.toJson((Integer) Persona.getId(), writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void cargarIdBook() {
        try (Reader reader = new FileReader(PATH_FILE_IDBOOK)) {
            Type typelist = new TypeToken<Integer>() {
            }.getType();
            Book.setId(gson.fromJson(reader, typelist));
            if(Book.getId() == null)
            {
                Book.setId(0);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.fillInStackTrace());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void guardarIDBook() {

        try (Writer writer = new FileWriter(PATH_FILE_IDBOOK)) {
            gson.toJson((Integer) Book.getId(), writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
