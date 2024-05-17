export class AddUser {
    name: string;
    dni: string;
    email: string;
    password: string;
    repeatPassword: string;
    birthdate: string;
    constructor(name: string, dni: string, email: string, password: string, repeatPassword: string, birthdate: string) {
        this.name = name;
        this.dni = dni;
        this.email = email;
        this.password = password;
        this.repeatPassword = repeatPassword;
        this.birthdate = birthdate;
    }
}