package gsoc.mihaela.claudia.diosan.gsoc_2019_python_library_for_real_time_data_visualization.Models;


import java.io.Serializable;

public class Volunteer implements Serializable {

    private long id;
    private String firstNameVolunteer;
    private String lastNameVolunteer;
    private String phoneNumberVolunteer;
    private String ageVolunteer;
    private String email;

    public Volunteer(){
    }

    public Volunteer(String firstNameVolunteer, String lastNameVolunteer, String phoneNumberVolunteer,String ageVolunteer, String email){

        this.firstNameVolunteer = firstNameVolunteer;
        this.lastNameVolunteer = lastNameVolunteer;
        this.phoneNumberVolunteer = phoneNumberVolunteer;
        this.ageVolunteer = ageVolunteer;
        this.email = email;

    }

    public long getId() {
        return id;
    }

    public void setId(long id){
        this.id = id;
    }

    public String getFirstNameVolunteer(){
        return firstNameVolunteer;
    }

    public void setFirstNameVolunteer(String firstNameVolunteer){
        this.firstNameVolunteer = firstNameVolunteer;
    }

    public String getLastNameVolunteer(){
        return  lastNameVolunteer;
    }

    public void setLastNameVolunteer(String lastNameVolunteer){
        this.lastNameVolunteer = lastNameVolunteer;
    }

    public String getPhoneNumberVolunteer(){
        return phoneNumberVolunteer;
    }

    public void setPhoneNumberVolunteer(String phoneNumberVolunteer){
        this.phoneNumberVolunteer = phoneNumberVolunteer;
    }

    public  String getAgeVolunteer(){
        return  ageVolunteer;
    }

    public  void  setAgeVolunteer(String ageVolunteer){
        this.ageVolunteer = ageVolunteer;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    @Override
    public String toString(){
        return "Volunteer{" +
                "id" + id +
                ", firstNameVolunteer=" + firstNameVolunteer + '\'' +
                ", lastNameVolunteer=" + lastNameVolunteer + '\'' +
                ", phoneNumberVolunteer=" + phoneNumberVolunteer + '\'' +
                ", ageVolunteer=" + ageVolunteer + '\'' +
                ", email=" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Volunteer that = (Volunteer) o;

        if (id != that.id) return false;
        if (firstNameVolunteer != null ? !firstNameVolunteer.equals(that.firstNameVolunteer) : that.firstNameVolunteer != null) return false;
        if (lastNameVolunteer != null ? !lastNameVolunteer.equals(that.lastNameVolunteer) : that.lastNameVolunteer != null) return false;
        if (phoneNumberVolunteer != null ? !phoneNumberVolunteer.equals(that.phoneNumberVolunteer) : that.phoneNumberVolunteer != null) return false;
        if (ageVolunteer != null ? !ageVolunteer.equals(that.ageVolunteer) : that.ageVolunteer != null) return false;
        return email != null ? email.equals(that.email) : that.email == null;

    }




}
