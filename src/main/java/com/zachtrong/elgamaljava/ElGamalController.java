package com.zachtrong.elgamaljava;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.print.PageLayout;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Random;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;

public class ElGamalController {
    public static final int MAXIMUM_BITS_K = 2048;
    public static Random random = new Random();

    private static ScheduledExecutorService ioThread = new ScheduledThreadPoolExecutor(3);

    @FXML
    public TextField txtA;

    @FXML
    public TextField txtK;

    @FXML
    public TextField txtP;

    @FXML
    public TextArea txtM;

    @FXML
    public TextArea txtC;

    @FXML
    public TextField txtX;

    @FXML
    public Button btnGenerate;

    @FXML
    protected void onBtnGenerateClicked(ActionEvent actionEvent) {

        btnGenerate.setText("Loading");

        ioThread.submit(() -> {
            System.out.println("Key Generation");

            int k = Math.abs(random.nextInt(100) + MAXIMUM_BITS_K);
            Platform.runLater(() -> {
                txtK.setText(String.valueOf(k));
                System.out.println("k = " + txtK.getText());
            });

            BigInteger p = BigInteger.probablePrime(k, random);
            Platform.runLater(() -> {
                txtP.setText(p.toString());
                System.out.println("p = " + txtP.getText());
            });

            BigInteger a = BigInteger.ZERO;
            try {
                a = PrimitiveRootSearch.primitiveRootSearch(p);
            } catch (Exception e) {
                e.printStackTrace();
            }
            BigInteger finalA = a;
            Platform.runLater(() -> {
                txtA.setText(finalA.toString());
                System.out.println("a = " + txtA.getText());
            });

            BigInteger x;
            do {
                x = new BigInteger(p.bitLength(), random);
            } while (x.compareTo(p) >= 0);
            BigInteger finalX = x;
            Platform.runLater(() -> {
                txtX.setText(finalX.toString());
                System.out.println("x = " + txtX.getText());
            });

            Platform.runLater(() -> {
                btnGenerate.setText("Generate");
            });
        });
    }

    @FXML
    public void onBtnMessageEncryptionClicked(ActionEvent actionEvent) {
        if (txtK.getText().isBlank() || txtM.getText().isBlank() || txtA.getText().isBlank() || txtP.getText().isBlank() || txtX.getText().isBlank()) {
            txtC.setText("Error, invalid input");
        } else {
            try {
                System.out.println("Encryption");
                BigInteger a = new BigInteger(txtA.getText(), 10);
                BigInteger k = new BigInteger(txtK.getText(), 10);
                BigInteger p = new BigInteger(txtP.getText(), 10);
                BigInteger r = a.modPow(k, p);
                System.out.println("r = " + r);

                BigInteger x = new BigInteger(txtX.getText(), 10);
                BigInteger y = a.modPow(x, p);
                System.out.println("y = " + y);

                BigInteger M = new BigInteger(1, txtM.getText().getBytes());
                BigInteger C = y.modPow(k, p).multiply(M).mod(p);
                System.out.println("C = " + C);

                String cipherDecimal = "(" + C + "," + r + ")";
                String cipherStr = Base64.getEncoder().encodeToString(cipherDecimal.getBytes());

                if (txtC.getText().equals(cipherStr)) {
                    // double click event
                    txtC.setText(cipherDecimal);
                } else {
                    txtC.setText(cipherStr);
                }
            } catch (Throwable t) {
                txtC.setText(t.getMessage());
            }
        }
    }

    @FXML
    public void onBtnMessageDecryptionClicked(ActionEvent actionEvent) {
        if (txtC.getText().isBlank() || txtP.getText().isBlank() || txtX.getText().isBlank()) {
            txtM.setText("Error, invalid input");
        } else {
            try {
                System.out.println("Decryption");

                String cipherDecimal = txtC.getText();
                if (!(cipherDecimal.startsWith("(") && cipherDecimal.endsWith(")"))) {
                    cipherDecimal = new String(Base64.getDecoder().decode(cipherDecimal));
                }
                String[] ciphers = cipherDecimal.substring(1, cipherDecimal.length() - 1).split(",");
                BigInteger C = new BigInteger(ciphers[0], 10);
                BigInteger r = new BigInteger(ciphers[1], 10);
                System.out.println("C = " + C);
                System.out.println("r = " + r);

                BigInteger p = new BigInteger(txtP.getText(), 10);
                BigInteger x = new BigInteger(txtX.getText(), 10);

                BigInteger Z = r.modPow(x, p);
                BigInteger Z_inv = Z.modInverse(p);
                BigInteger M = Z_inv.multiply(C).mod(p);

                System.out.println("Z = " + Z);
                System.out.println("Z_inv = " + Z_inv);
                System.out.println("M = " + M);


                txtM.setText(new String(M.toByteArray()));
            } catch (Throwable t) {
                txtM.setText(t.getMessage());
            }
        }
    }
}