package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BlockTest {
    private BlockModel block;

    @BeforeEach
    void init(){
        block = new BlockModel();
    }

    @Test
    void createBlocks() {
        int countBlocks = 0;
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 6; j++){

                    if(block.blockArray[i][j][0] == 1){
                        countBlocks++;
                    }

            }

        }
        assertEquals(block.blocksLeft, countBlocks);

    }

    @Test
    void isHit(){
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 6; j++){

                assertEquals(block.blockArray[i][j][0] == 0, block.isHit(i, j));

            }

        }
    }

}