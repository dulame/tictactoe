package com.example.tictactoe;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class MainController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label label;

    private boolean isX;
    private boolean isDraw;
    private boolean endGame;
    private int[][] field;
    private int clickCount;

    private void check() {
        for (int i = 0; i < 3; ++i) {
            if (field[i][0] == field[i][1] && field[i][1] == field[i][2] && field[i][0] != 0 ||
                    field[0][i] == field[1][i] && field[1][i] == field[2][i] && field[0][i] != 0) {
                winAction();
            }
        }

        if (field[0][0] == field[1][1] && field[1][1] == field[2][2] ||
            field[0][2] == field[1][1] && field[1][1] == field[2][0]) {
                if (field[1][1] != 0) {
                    winAction();
                }
        }

        if (clickCount >= 8) {
            isDraw = true;
            winAction();
        }
    }

    private void winAction() {
        if (isDraw) label.setText("DRAW");
        else if (isX) label.setText("X WON");
        else label.setText("O WON");

        endGame = true;
    }

    @FXML
    void btnClicked(ActionEvent event) {
        if (!endGame) {
            Button btn = (Button) event.getSource();
            clickCount++;

            int x = GridPane.getRowIndex(btn) == null ? 0 : GridPane.getRowIndex(btn);
            int y = GridPane.getColumnIndex(btn) == null ? 0 : GridPane.getColumnIndex(btn);

            if (field[y][x] != 0) return;

            if (isX) {
                field[y][x] = 2;
                btn.setText("X");
            } else {
                field[y][x] = 1;
                btn.setText("O");
            }

            check();
            if (!endGame) label.setText((isX ? "O MOVE" : "X MOVE"));
            isX = !isX;
        }
    }

    @FXML
    void initialize() {
        assert label != null : "fx:id=\"label\" was not injected: check your FXML file 'Main.fxml'.";

        isX = false;
        isDraw = false;
        endGame = false;
        field = new int[3][3];
        clickCount = 0;
    }

}
