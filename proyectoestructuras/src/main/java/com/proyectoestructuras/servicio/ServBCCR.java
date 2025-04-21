package com.proyectoestructuras.servicio;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;

public class ServBCCR {

    private static final String URL = "https://gee.bccr.fi.cr/Indicadores/Suscripciones/WS/wsindicadoreseconomicos.asmx/ObtenerIndicadoresEconomicos";

    public BigDecimal obtenerTipoCambio(String indicador, String fecha, String nombre, String correo, String token) throws Exception {
        String postData = "Indicador=" + indicador
                + "&FechaInicio=" + fecha
                + "&FechaFinal=" + fecha
                + "&Nombre=" + nombre
                + "&SubNiveles=N"
                + "&CorreoElectronico=" + correo
                + "&Token=" + token;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL))
                .POST(HttpRequest.BodyPublishers.ofString(postData, StandardCharsets.UTF_8))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .build();

        HttpClient client = HttpClient.newHttpClient();
        CompletableFuture<HttpResponse<String>> responseFuture = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
        HttpResponse<String> response = responseFuture.join();

        if (response.statusCode() == 200) {
            try (InputStream inputStream = new ByteArrayInputStream(response.body().getBytes())) {
                JAXBContext jaxbContext = JAXBContext.newInstance(IndicadorEconomico.class);
                Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
                IndicadorEconomico indicadorEconomico = (IndicadorEconomico) unmarshaller.unmarshal(inputStream);
                return indicadorEconomico.getValor();
            }
        } else {
            throw new Exception("Error en la solicitud HTTP: " + response.statusCode());
        }
    }
}
