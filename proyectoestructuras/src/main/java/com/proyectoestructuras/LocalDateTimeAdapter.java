package com.proyectoestructuras;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
/**
 * Clase que permite la serialización y deserialización de objetos de tipo LocalDateTime
 * Despues de investigar, se encontró que la librería Gson no soporta la serialización de objetos de tipo LocalDateTime por defecto, lo cual nos generaba problemas al guardar los archivos que son null, como el tiempo en el que se atendio el ticket, por lo que se implementó esta clase para poder serializar y deserializar objetos de tipo LocalDateTime
 */
public class LocalDateTimeAdapter extends TypeAdapter<LocalDateTime> {


    // Formato de fecha y hora
    // Se utiliza el formato ISO_LOCAL_DATE_TIME
    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    /**
     * Metodo que permite escribir un objeto de tipo LocalDateTime en un archivo JSON
     * @param jsonWriter escritor de JSON
     * @param localDateTime objeto de tipo LocalDateTime
     * @throws IOException
     * 
     * Si el objeto es null, se escribe un valor nulo
     * Si el objeto no es null, se escribe el objeto en formato de fecha y hora yyyy-MM-ddTHH:mm:ss[.SSS]
     */
    @Override
    public void write(JsonWriter jsonWriter, LocalDateTime localDateTime) throws IOException {
        if (localDateTime == null) {
            jsonWriter.nullValue();
        } else {
            jsonWriter.value(localDateTime.format(formatter));
        }
    }

    /**
     * Metodo que permite leer un objeto de tipo LocalDateTime de un archivo JSON
     * @param jsonReader lector de JSON
     * @return objeto de tipo LocalDateTime
     * @throws IOException
     * 
     * Si el valor es nulo, se retorna un valor nulo
     * Si el valor no es nulo, se retorna un objeto de tipo LocalDateTime con el valor leido
     */
    @Override
    public LocalDateTime read(JsonReader jsonReader) throws IOException {
        String value = jsonReader.nextString();
        return value == null ? null : LocalDateTime.parse(value, formatter);
    }
}
