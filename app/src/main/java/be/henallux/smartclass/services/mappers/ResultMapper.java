package be.henallux.smartclass.services.mappers;

import be.henallux.smartclass.model.Result;
import be.henallux.smartclass.repositories.dto.ResultDto;

public class ResultMapper {

    private static ResultMapper instance = null;

    public static ResultMapper getInstance() {
        if (instance == null) {
            instance = new ResultMapper();
        }
        return instance;
    }

    public Result mapToResult(ResultDto dto) {
        if (dto == null) {
            return null;
        }
        return new Result(dto.getCategory(), dto.getSchoolsubject(), dto.getAverage()+"%");
    }
}
