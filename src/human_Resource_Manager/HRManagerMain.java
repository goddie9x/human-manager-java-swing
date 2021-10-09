
package human_Resource_Manager;

import Controller.LoginController;

public class HRManagerMain {

    public static void main(String[] args) {
       LoginController login = new LoginController();
       login.loadViewLogin();
    }
}
