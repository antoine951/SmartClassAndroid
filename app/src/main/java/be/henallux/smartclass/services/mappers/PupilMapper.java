package be.henallux.smartclass.services.mappers;

import be.henallux.smartclass.model.Pupil;
import be.henallux.smartclass.repositories.dto.PupilDto;

public class PupilMapper {

    private static PupilMapper instance = null;

    public static PupilMapper getInstance() {
        if (instance == null) {
            instance = new PupilMapper();
        }
        return instance;
    }

    public Pupil mapToPupil(PupilDto dto) {
        if (dto == null) {
            return null;
        }
        return new Pupil(dto.getToken(),dto.getFirstname(), dto.getLastname());
    }
}
