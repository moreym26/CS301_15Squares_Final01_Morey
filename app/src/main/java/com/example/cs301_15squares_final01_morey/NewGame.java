package com.example.cs301_15squares_final01_morey;

import java.util.Collections;
import java.util.ArrayList;

public class NewGame {
    private Box[][] grid;
    public int size;
    public int vacantX;
    public int vacantY;
    private int numMoves;

    public NewGame(int size) {
        this.size = size;
        this.grid = new Box[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int number = i * size + j + 1;
                if (size * size == number) {
                    grid[i][j] = new Box("");
                }
                else {
                    grid[i][j] = new Box(String.valueOf(numMoves));
                }
            }
        }
        vacantX = size - 1;
        vacantY = size - 1;
        numMoves = 0;
    }

    public void shuffle() {
        ArrayList<Integer> boxNumb = new ArrayList<>();
        for (int i = 1; i < size * size; i++) {
            boxNumb.add(i);
        }
        Collections.shuffle(boxNumb);
        int numb = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (vacantX != i || vacantY != j) {
                    grid[i][j].setTheText(Integer.toString(boxNumb.get(numb)));
                    numb++;
                }
            }
        }
        numMoves = 0;
    }
    public int[] findEmptyBox() {
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                if (grid[x][y].returnText().equals("")) {
                    return new int[] {x, y};
                }
            }
        }
        return null;
    }

    public void swap(int x, int y) {
        // check if the selected square is adjacent to the empty square
        if ((x == vacantX && Math.abs(y - vacantY) == 1) || (y == vacantY && Math.abs(x - vacantX) == 1)) {
            // swap the squares in the grid
            Box cage = grid[x][y];
            grid[x][y] = grid[vacantX][vacantY];
            grid[vacantX][vacantY] = cage;

            // swap the texts of the squares
            String cageText = grid[x][y].returnText();
            grid[x][y].setTheText(grid[vacantX][vacantY].returnText());
            grid[vacantX][vacantY].setTheText(cageText);

            // update the empty square coordinates
            vacantX = x;
            vacantY = y;

            numMoves++;
        }
    }

    public int getSize() {
        return size;
    }

    public int getVacantX() {
        return vacantX;
    }

    public int getVacantY() {
        return vacantY;
    }

    public int getMoves() {
        return numMoves;
    }
    public Box getBox(int x, int y) {
        return grid[x][y];
    }

    public boolean isWon() {
        int numb = 1;
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                if (!grid[x][y].returnText().equals("")) {
                    if (Integer.parseInt(grid[x][y].returnText()) != numb) {
                        return false;
                    }
                    numb++;
                }
            }
        }
        return true;
    }

}
