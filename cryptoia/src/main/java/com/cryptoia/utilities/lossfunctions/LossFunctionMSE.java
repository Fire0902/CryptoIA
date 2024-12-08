package com.cryptoia.utilities.lossfunctions;

import com.cryptoia.utilities.LossFunction;

public class LossFunctionMSE implements LossFunction{
    
    @Override
    public Double lossFunction(Double yPredit, Double yReel){
        return Math.pow(yPredit - yReel, 2);
    }
}
