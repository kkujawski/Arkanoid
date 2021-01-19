package model;

import controller.Controller;

import java.util.Random;

public class BlockModel {
    public static int width = 50, height = 20;
    public static int numberOfBlocks;

    public int blocksLeft = Controller.randomNumber.nextInt(15 + 1) + 10;

    public static int[][][] blockArray = new int[6][6][2]; // [][][isHit, powerups]

    public BlockModel(){ createBlocks(); }

    public void createBlocks(){
        //Clears block array and generates new one
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 6; j++){
                for (int k = 0; k < 2; k++){
                    blockArray[i][j][k] = 0;

                }

            }

        }

        //Chooses random number of blocks between 10 and 36
        blocksLeft = Controller.randomNumber.nextInt(26 + 1) + 10;
        int i = blocksLeft;
        int row, col, powerup;
        while (i > 0){
            //Get random column and row number
            row = Controller.randomNumber.nextInt(6);
            col = Controller.randomNumber.nextInt(6);
            powerup = Controller.randomNumber.nextInt(10);
            //Check if there is already a block
            if (blockArray[row][col][0] != 1){

                blockArray[row][col][0] = 1;
                if(powerup == 1){
                    blockArray[row][col][1] = 1;

                } else if (powerup == 2){
                    blockArray[row][col][1] = 2;

                } else if (powerup == 3){
                    blockArray[row][col][1] = 3;

                } else if (powerup == 4){
                    blockArray[row][col][1] = 4;

                }

                i--;

            }

        }

    }


    public static boolean isHit(int row, int col){
        return blockArray[row][col][0] == 0;

    }


    public void didWin(){
        if (blocksLeft <= 0){
            Controller.win = true;
            Controller.active = false;

        }

    }
}
