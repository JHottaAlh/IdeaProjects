//入力結果を保持するためのクラス

package InputSample;

import java.io.Serializable;

/**
 * Created by smi_kun on 2017/05/18.
 */
public class InputValues implements Serializable{
    private static final long serialVersionUID = 1L;

    /*それぞれの変数をString型で宣言*/
    private String name;
    private String password;
    private String hidden;
    private String sex;
    private String[] hobby;
    private String nationality;
    private String[] language;
    private String memo;

    private String submit1;
    private String submit2;
    private String image_x;
    private String image_y;

    //name
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    //password
    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }

    //hidden
    public String getHidden(){
        return hidden;
    }
    public void setHidden(String hidden){
        this.hidden = hidden;
    }

    //sex
    public String getSex(){
        return sex;
    }
    public void setSex(String sex){
        this.sex = sex;
    }

    //hobby
    public String[] getHobby(){
        return hobby;
    }
    public void setHobby(String[] hobby){
        this.hobby = hobby;
    }

    //nationality
    public String getNationality(){
        return nationality;
    }
    public void setNationality(String nationality){
        this.nationality = nationality;
    }

    //language
    public String[] getLanguage(){
        return language;
    }
    public void setLanguage(String[] language){
        this.language = language;
    }

    //memo
    public String getMemo(){
        return memo;
    }
    public void setMemo(String memo){
        this.memo = memo;
    }

    //submit1
    public String getSubmit1(){
        return submit1;
    }
    public void setSubmit1(String submit1){
        this.submit1 = submit1;
    }

    //submit2
    public String getSubmit2(){
        return submit2;
    }
    public void setSubmit2(String submit2){
        this.submit2 = submit2;
    }

    //image.x
    public String getImage_x(){
        return image_x;
    }
    public void setImage_x(String image_x){
        this.image_x = image_x;
    }

    //image.y
    public String getImage_y(){
        return image_y;
    }
    public void setImage_y(String image_y){
        this.image_y = image_y;
    }
}
