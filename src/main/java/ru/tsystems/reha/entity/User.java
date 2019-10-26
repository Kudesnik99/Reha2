package ru.tsystems.reha.entity;

import javax.persistence.*;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Entity
@Table(name ="user")
@NamedQueries({
        @NamedQuery(name = "User.findByEmail", query = "select u from User u where u.email = :email")
})
public class User {

    private int     userId;
    private String  firstName;
    private String  lastName;
    private Role    role;
    private String  avatar;
    private String  email;
    private String  password;

    public User(){};

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    public Role getRole() {
        return role;
    }
    public void setRole(Role role) { this.role = role; }

    @Column(name = "avatar")
    public String getAvatar() {
        return avatar;
    }
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Column(name = "email")
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "password")
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof User))
            return false;
        User user = (User) o;
        return getEmail() != null && getEmail().equals(user.getEmail());
    }

    @Override
    public int hashCode() {
        HashCodeBuilder hb = new HashCodeBuilder();
        hb.append(email);
        return hb.toHashCode();
    }
}
