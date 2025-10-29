package br.com.andersonrodriguesdev;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.time.Instant;

public class SerializadorPix {

    public static void main(String[] args) throws Exception {

        var pix = new Pix(1L, new BigDecimal("10.99"), "teste", Instant.now(), "😉");
        FileOutputStream fos = new FileOutputStream("pix.ser");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(pix);

    }
}