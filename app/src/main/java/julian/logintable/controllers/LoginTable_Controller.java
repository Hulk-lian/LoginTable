package julian.logintable.controllers;

/**************************
 * Error codes.
 *  0   OK
 *  1   no digit (PASSWORD_DIGIT)
 *  2   no lowerchar or no upperchar (PASSWORD_CASE)
 *  3   leng<8 (PASSWORD_LENGTH)
* *************************/

/**
 * Class that implements the business rules login
 */
public class LoginTable_Controller implements ILoginMVC {

    //errores
    public static final int OK=0;
    public static final int PASSWORD_DIGIT=1;
    public static final int PASSWORD_CASE = 2;
    public static final int PASSWORD_LENGTH = 3;

    //patrones
    String patronpass="((?=.*[0-9]).*)";//number
    String patronpass2="(.*(?=.*[a-z])(?=.*[A-Z]).*)";//characters

    public int validateCredentials(String user, String password) {
        int res=0;

        if (password.length()>=8){

            if(password.matches(patronpass)) {

                if(password.matches(patronpass2)){
                    res=0;
                }
                else {
                    res=PASSWORD_CASE;
                }

            }
            else {
                res=PASSWORD_DIGIT;
            }

        }
        else{
            res=PASSWORD_LENGTH;
        }

    return res;
    }

}
