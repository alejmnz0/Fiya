export class AddUser {
    name: string;
    dni: string;
    email: string;
    password: string;
    repeatPassword: string;
    birthdate: Date;
    constructor(name: string, dni: string, email: string, password: string, repeatPassword: string, birthdate: Date) {
        this.name = name;
        this.dni = dni;
        this.email = email;
        this.password = password;
        this.repeatPassword = repeatPassword;
        this.birthdate = birthdate;
    }
}