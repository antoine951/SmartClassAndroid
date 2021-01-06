package be.henallux.smartclass.services.mappers;

public class TutorMapper {

    private static TutorMapper instance = null;

    public static TutorMapper getInstance(){
        if(instance == null){
            instance =new TutorMapper();
        }
        return instance;
    }

    /*public Tutor mapToTutor(TutorDto dto){
        if (dto == null) {
            return null;
        }

        return new Tutor(dto.getUsername(), dto.getPassword());
    }*/
}
