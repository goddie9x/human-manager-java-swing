package Util;

public class Employee extends Staff {

    private String staffCode;
    private String departmentCode;
    private String positionCode;
    private String educationalBackgroundCode;
    private int salaryLevel;

    public Employee() {
        super("", "", "", "", "", "");
        this.staffCode = "";
        this.departmentCode = "";
        this.positionCode = "";
        this.educationalBackgroundCode = "";
        this.salaryLevel = 0;
    }

    public Employee(
            String staffCode,
            String fullname,
            String departmentCode,
            String dateOfBirth,
            String country,
            String sex,
            String nation,
            String phoneNumber,
            String educationalBackgroundCode,
            String positionCode,
            int salaryLevel
    ) {
        super(
                fullname,
                dateOfBirth,
                country,
                sex,
                nation,
                phoneNumber
        );
        this.staffCode = staffCode.trim();
        this.departmentCode = departmentCode.trim();
        this.positionCode = positionCode.trim();
        this.educationalBackgroundCode = educationalBackgroundCode.trim();
        this.salaryLevel = salaryLevel;

    }

    public void setsalaryLevel(int salaryLevel) {
        this.salaryLevel = salaryLevel;
    }

    public int getsalaryLevel() {
        return salaryLevel;
    }

    public void setAttibuteByStringExceptsalaryLevel(String atribute,
            String value) {
        super.setAttibuteByString(atribute, value);

        switch (atribute) {
            case "staffCode":
                this.staffCode = value;
                break;
            case "departmentCode":
                this.departmentCode = value;
            case "educationalBackgroundCode":
                this.educationalBackgroundCode = value;
            case "positionCode":
                this.positionCode = value;
        }
    }

    public String getAttibuteByStringExceptsalaryLevel(String atribute) {
        String result = super.getAttibuteByString(atribute);

        if (result.equals("")) {
            switch (atribute) {
                case "staffCode": {
                    result = staffCode;
                    break;
                }
                case "departmentCode": {
                    result = departmentCode;
                    break;
                }
                case "educationalBackgroundCode": {
                    result = educationalBackgroundCode;
                    break;
                }
                case "positionCode": {
                    result = positionCode;
                    break;
                }
                default: {
                    result = "";
                }
            }
        }

        return result;
    }

    public void assign(Employee data) {
        this.setAttibuteByStringExceptsalaryLevel(
                "staffCode",
                data.getAttibuteByStringExceptsalaryLevel("staffCode")
        );
        this.setAttibuteByStringExceptsalaryLevel(
                "fullname",
                data.getAttibuteByStringExceptsalaryLevel("fullname")
        );
        this.setAttibuteByStringExceptsalaryLevel(
                "departmentCode",
                data.getAttibuteByStringExceptsalaryLevel("departmentCode")
        );
        this.setAttibuteByStringExceptsalaryLevel(
                "dateOfBirth",
                data.getAttibuteByStringExceptsalaryLevel("dateOfBirth")
        );
        this.setAttibuteByStringExceptsalaryLevel(
                "country",
                data.getAttibuteByStringExceptsalaryLevel("country")
        );
        this.setAttibuteByStringExceptsalaryLevel(
                "sex",
                data.getAttibuteByStringExceptsalaryLevel("sex")
        );
        this.setAttibuteByStringExceptsalaryLevel(
                "nation",
                data.getAttibuteByStringExceptsalaryLevel("nation")
        );
        this.setAttibuteByStringExceptsalaryLevel(
                "phoneNumber",
                data.getAttibuteByStringExceptsalaryLevel("phoneNumber")
        );
        this.setAttibuteByStringExceptsalaryLevel(
                "educationalBackgroundCode",
                data.getAttibuteByStringExceptsalaryLevel("educationalBackgroundCode")
        );
        this.setAttibuteByStringExceptsalaryLevel(
                "positionCode",
                data.getAttibuteByStringExceptsalaryLevel("positionCode")
        );
        this.setsalaryLevel(data.getsalaryLevel());
    }
}
