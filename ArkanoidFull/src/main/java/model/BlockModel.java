package model;

import java.util.Random;

public class BlockModel {
    public static int width = 50, height = 20;
    public static int numberOfBlocks;
    Random randomNumber = new Random();
    public int blocksLeft = randomNumber.nextInt(15 + 1) + 10;

    // TODO: Check if those are zeros, i don't trust that
    public static int[][] blockArray = new int[5][5];

    public void createBlocks(){
        int i = blocksLeft;
        int row, col;
        while (i > 0){
            //Get random column and row number
            row = randomNumber.nextInt(4);
            col = randomNumber.nextInt(4);
            //Check if there is already a block
            if (blockArray[row][col] != 1){
                blockArray[row][col] = 1;
                i--;

            }

        }
    }

    public static boolean isHit(int row, int col){
        return blockArray[row][col] == 0;

    }

    public void blockHit(int row, int col){
        blockArray[row][col] = 0;
        blocksLeft -= 1;

    }

    public int howManyLeft(){
        return blocksLeft;

    }
}
