package be.henallux.smartclass.services.mappers;

import be.henallux.smartclass.model.Test;
import be.henallux.smartclass.repositories.dto.TestDto;

public class TestMapper {
    private static TestMapper instance = null;

    public static TestMapper getInstance() {
        if (instance == null) {
            instance = new TestMapper();
        }
        return instance;
    }

    public Test mapToTest(TestDto dto) {
        if (dto == null) {
            return null;
        }

        return new Test(dto.getTitle(), dto.getResult(), dto.getNote(), dto.getMaxvalue(), dto.getCategory(), dto.getSchoolsubject());
    }
}
