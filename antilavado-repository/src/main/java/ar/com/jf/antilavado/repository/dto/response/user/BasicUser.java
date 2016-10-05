package ar.com.jf.antilavado.repository.dto.response.user;

/**
 * Created by fvaldes on 11/04/2016.
 */
public class BasicUser {

    private Long id;
    private String firstName;
    private String lastName;

    public String getName(){
        return firstName + " " + lastName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
