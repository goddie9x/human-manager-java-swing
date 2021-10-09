package Util;

import java.util.Date;

public class Staff {

    protected String fullname;
    protected String country;
    protected String nation;
    protected String phoneNumber;
    protected String dateOfBirth;
    protected String sex;

    //constructor
    Staff(
            String fullname,
            String dateOfBirth,
            String country,
            String sex,
            String nation,
            String phoneNumber
    ) {
        this.fullname = fullname.trim();
        this.country = country.trim();
        this.nation = nation.trim();
        this.phoneNumber = phoneNumber.trim();
        this.dateOfBirth = dateOfBirth.trim();
        this.sex = sex;
    }

    public void setAttibuteByString(String atribute,
            String value) {
        switch (atribute) {
            case "fullname":
                this.fullname = value;
            case "dateOfBirth":
                this.dateOfBirth = value;
            case "country":
                this.country = value;
            case "sex":
                this.sex = value;
            case "nation":
                this.nation = value;
            case "phoneNumber":
                this.phoneNumber = value;
        }
    }

    public String getAttibuteByString(String atribute) {
        String result;
        
        switch (atribute) {
            case "fullname": {
                result = this.fullname;
                break;
            }
            case "dateOfBirth": {
                result = this.dateOfBirth;
                break;
            }
            case "country": {
                result = this.country;
                break;
            }
            case "sex": {
                result = this.sex;
                break;
            }
            case "nation": {
                result = this.nation;
                break;
            }
            case "phoneNumber": {
                result = this.phoneNumber;
                break;
            }
            default:{
                result = "";
                break;
            }
        }
        return result;
    }
}
