package com.cryptoia.utilities.lossfunctions;

import com.cryptoia.utilities.LossFunction;

public class LossFunctionMSENoSquare implements LossFunction{
    @Override
    public Double lossFunction(Double yPredit, Double yReel){
        return yPredit - yReel;
    }
}
