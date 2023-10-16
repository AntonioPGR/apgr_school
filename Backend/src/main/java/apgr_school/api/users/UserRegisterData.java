package apgr_school.api.users;

import java.sql.Date;

public record UserRegisterData(long id, String name, Date date_of_birth, String email, String cellphone, String password, EnumGender gender, String PhotoPath){
}
