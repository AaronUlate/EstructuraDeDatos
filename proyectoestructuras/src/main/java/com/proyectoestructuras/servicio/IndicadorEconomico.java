package com.proyectoestructuras.servicio;

import java.math.BigDecimal;
import java.util.List;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAccessType;

@XmlRootElement(name = "DataSet", namespace = "http://ws.sdde.bccr.fi.cr")
@XmlAccessorType(XmlAccessType.FIELD)
public class IndicadorEconomico {

    @XmlElement(name = "diffgram", namespace = "urn:schemas-microsoft-com:xml-diffgram-v1")
    private Diffgram diffgram;

    public BigDecimal getValor() {
        if (diffgram != null && diffgram.getDatosDeIndicadores() != null && !diffgram.getDatosDeIndicadores().getIndicadores().isEmpty()) {
            return diffgram.getDatosDeIndicadores().getIndicadores().get(0).getValor();
        }
        return null;
    }

    public Diffgram getDiffgram() {
        return diffgram;
    }

    public void setDiffgram(Diffgram diffgram) {
        this.diffgram = diffgram;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Diffgram {

        @XmlElement(name = "Datos_de_INGC011_CAT_INDICADORECONOMIC", namespace = "")
        private DatosDeIndicadores datosDeIndicadores;

        public DatosDeIndicadores getDatosDeIndicadores() {
            return datosDeIndicadores;
        }

        public void setDatosDeIndicadores(DatosDeIndicadores datosDeIndicadores) {
            this.datosDeIndicadores = datosDeIndicadores;
        }
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class DatosDeIndicadores {

        @XmlElement(name = "INGC011_CAT_INDICADORECONOMIC", namespace = "")
        private List<Indicador> indicadores;

        public List<Indicador> getIndicadores() {
            return indicadores;
        }

        public void setIndicadores(List<Indicador> indicadores) {
            this.indicadores = indicadores;
        }
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Indicador {

        @XmlElement(name = "NUM_VALOR", namespace = "")
        private BigDecimal valor;

        public BigDecimal getValor() {
            return valor;
        }

        public void setValor(BigDecimal valor) {
            this.valor = valor;
        }
    }
}
