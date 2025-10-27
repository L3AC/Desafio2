package MainFolder.util;

import com.toedter.calendar.JYearChooser;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Validacion {

    public static void permitirSolo(JTextField campo, String caracteresPermitidos, int limite) {
        campo.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();

                // Permitir retroceso (backspace), tabulación, teclas de control y pegado (aunque parcialmente)
                if (c == '\b' || c == '\t' || c == KeyEvent.CHAR_UNDEFINED) {
                    return;
                }
                // Obtener el texto actual del campo
                String textoActual = campo.getText();
                // Si ya se alcanzó el límite, no permitir más caracteres
                if (textoActual.length() >= limite) {
                    e.consume(); // Bloquear la escritura
                    return;
                }
                // Si el carácter NO está en la lista permitida, se bloquea
                if (caracteresPermitidos.indexOf(c) == -1) {
                    e.consume(); // Evita que se escriba
                }
            }
        });
    }

    public static void permitirSoloFecha(JYearChooser campo, String caracteresPermitidos) {
        campo.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                // Permitir retroceso (backspace), tabulación y teclas de control
                if (c == '\b' || c == '\t' || c == KeyEvent.CHAR_UNDEFINED) {
                    return;
                }
                // Si el carácter NO está en la lista permitida, se bloquea
                if (caracteresPermitidos.indexOf(c) == -1) {
                    e.consume(); // Evita que se escriba
                }
            }
        });
    }
}
