package com.badlogic.UniSim2.mapmanager;

import com.badlogic.UniSim2.resources.Consts;

/**
 * This class is used to create Paths at the start of the game.
 */
public class Paths {
    
    private Paths(){
    }

    public static void createPaths(){
        new Path(Consts.H_PATH_1_X, Consts.H_PATH_1_Y, Consts.H_PATH_1_WIDTH, Consts.PATH_SIZE);
        new Path(Consts.H_PATH_2_X, Consts.H_PATH_2_Y, Consts.H_PATH_2_WIDTH, Consts.PATH_SIZE);

        new Path(Consts.V_PATH_1_X, Consts.V_PATH_1_Y, Consts.PATH_SIZE, Consts.V_PATH_1_HEIGHT);
        new Path(Consts.V_PATH_2_X, Consts.V_PATH_2_Y, Consts.PATH_SIZE, Consts.V_PATH_2_HEIGHT);
        new Path(Consts.V_PATH_3_X, Consts.V_PATH_3_Y, Consts.PATH_SIZE, Consts.V_PATH_3_HEIGHT);
        new Path(Consts.V_PATH_4_X, Consts.V_PATH_4_Y, Consts.PATH_SIZE, Consts.V_PATH_4_HEIGHT);
    }
}
