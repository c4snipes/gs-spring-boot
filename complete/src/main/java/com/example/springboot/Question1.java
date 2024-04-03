package com.example.springboot;

public class Question1 {

    int currentPosX = 0;
    int currentPosY = 0;

    public int move(int diceEast, int diceSouth, int luckySpin) {
        if (diceEast > 6 || diceEast < -6) {
            return 0;
        }
        if (diceSouth > 6 || diceSouth < -6) {
            return 0;
        }
        if (luckySpin > 0 && luckySpin <= 3) {
            diceEast = diceEast + 3;
            diceSouth = diceSouth + 3;
        }
        if (luckySpin > 3) {
            diceEast = diceEast * 2;
            diceSouth = diceSouth * 2;
        }
        currentPosX = currentPosX + diceEast;
        currentPosY = currentPosY + diceSouth;
        return diceEast + diceSouth;
    }
}

