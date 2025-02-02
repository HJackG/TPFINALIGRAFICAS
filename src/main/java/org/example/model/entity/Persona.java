package org.example.model.entity;

public abstract class Persona {
    private static Integer id = 0;
    private String dni;
    private String name;
    private String lastName;
    private Integer age;
    private String email;
    private String phone;
    private String adress;
    private String password;
    private boolean rol;
    private Integer idPersona;


    public static Integer getId() {
        return id;
    }

    public static void setId(Integer id) {
        Persona.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isRol() {
        return rol;
    }

    public void setRol(boolean rol) {
        this.rol = rol;
    }

    public Integer getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Persona(String dni, String name, String lastName, Integer age, String email, String phone, String adress, String password, boolean rol) {
        this.dni = dni;
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.phone = phone;
        this.adress = adress;
        this.password = password;
        this.rol = rol;
        this.idPersona = ++id;
    }

    public Persona() {
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("****************************\n")
                .append("*      Detalles cliente     *\n")
                .append("****************************\n")
                .append("* DNI: ").append(dni).append("\n")
                .append("* Nombre: ").append(name).append("\n")
                .append("* Apellido: ").append(lastName).append("\n")
                .append("* Edad: ").append(age).append("\n")
                .append("* Email: ").append(email).append("\n")
                .append("* Teléfono: ").append(phone).append("\n")
                .append("* Direccion: ").append(adress).append("\n")
                .append("* Contraseña: ").append(password).append("\n")
                .append("\n");
        return sb.toString();
    }


}
