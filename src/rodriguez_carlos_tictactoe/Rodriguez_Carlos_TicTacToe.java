package rodriguez_carlos_tictactoe;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

public class Rodriguez_Carlos_TicTacToe {
    private JFrame ventana;
    private JButton[][] tablero;
    private JLabel etiquetaEstado;
    private boolean esTurnoDeX = true; // Turno inicial de X
    private String jugadorX; // Nombre del jugador X
    private String jugadorO; // Nombre del jugador O

    public Rodriguez_Carlos_TicTacToe() {
        // Solicitar nombres de los jugadores
        jugadorX = JOptionPane.showInputDialog("Ingresa el nombre del jugador 1 (X):");
        jugadorO = JOptionPane.showInputDialog("Ingresa el nombre del jugador 2 (O):");

        ventana = new JFrame("Tic Tac Toe");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(300, 400);
        ventana.setLayout(null);

        // Etiqueta de estado
        etiquetaEstado = new JLabel("Turno de " + jugadorX + " (X)", SwingConstants.CENTER);
        etiquetaEstado.setBounds(10, 10, 260, 30);
        ventana.add(etiquetaEstado);

        // Crear el tablero de botones
        tablero = new JButton[3][3];
        int tamaño = 80;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tablero[i][j] = new JButton("");
                tablero[i][j].setBounds(10 + j * tamaño, 50 + i * tamaño, tamaño, tamaño);
                tablero[i][j].setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 24));
                tablero[i][j].addActionListener(e -> manejarClic((JButton) e.getSource()));
                ventana.add(tablero[i][j]);
            }
        }

        // Botón para salir
        JButton botonSalir = new JButton("Salir");
        botonSalir.setBounds(100, 310, 80, 30);
        botonSalir.addActionListener(e -> System.exit(0)); // Cierra la aplicación
        ventana.add(botonSalir);

        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }

    // Manejar clic en un botón
    private void manejarClic(JButton boton) {
        if (!boton.getText().isEmpty()) return; // Ignorar si ya está ocupado

        boton.setText(esTurnoDeX ? "X" : "O"); // Colocar X o O
        if (verificarGanador()) {
            etiquetaEstado.setText("¡Ganó " + (esTurnoDeX ? jugadorX : jugadorO) + "!");
            deshabilitarBotones();
        } else if (tableroLleno()) {
            etiquetaEstado.setText("¡Empate!");
        } else {
            esTurnoDeX = !esTurnoDeX; // Cambiar turno
            etiquetaEstado.setText("Turno de " + (esTurnoDeX ? jugadorX : jugadorO));
        }
    }

    // Comprobar si hay un ganador
    private boolean verificarGanador() {
        for (int i = 0; i < 3; i++) {
            // Revisar filas y columnas
            if (tablero[i][0].getText().equals(tablero[i][1].getText()) &&
                tablero[i][1].getText().equals(tablero[i][2].getText()) &&
                !tablero[i][0].getText().isEmpty()) return true;
            if (tablero[0][i].getText().equals(tablero[1][i].getText()) &&
                tablero[1][i].getText().equals(tablero[2][i].getText()) &&
                !tablero[0][i].getText().isEmpty()) return true;
        }
        // Revisar diagonales
        if (tablero[0][0].getText().equals(tablero[1][1].getText()) &&
            tablero[1][1].getText().equals(tablero[2][2].getText()) &&
            !tablero[0][0].getText().isEmpty()) return true;
        if (tablero[0][2].getText().equals(tablero[1][1].getText()) &&
            tablero[1][1].getText().equals(tablero[2][0].getText()) &&
            !tablero[0][2].getText().isEmpty()) return true;

        return false;
    }

    // Verificar si el tablero está lleno
    private boolean tableroLleno() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tablero[i][j].getText().isEmpty()) return false;
            }
        }
        return true;
    }

    // Deshabilitar botones al terminar el juego
    private void deshabilitarBotones() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tablero[i][j].setEnabled(false);
            }
        }
    }

    public static void main(String[] args) {
        new Rodriguez_Carlos_TicTacToe();
    }
}
