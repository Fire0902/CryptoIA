package com.cryptoia.utilities.lossfunctions;

import com.cryptoia.utilities.LossFunction;

public class LossFunctionMAE implements LossFunction{
    @Override
    public Double lossFunction(Double yPredit, Double yReel){
        return Math.abs(yPredit - yReel);
    }
}
