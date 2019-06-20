package edu.uni.userBaseInfo1.service;

import edu.uni.administrativestructure.bean.Position;

public interface OtherPositionService {
    Position selectPositionByPositionName(String PositionName);
}
