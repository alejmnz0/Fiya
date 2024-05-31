export class EditUser {
    name: string;
    email: string;
    actualEmail: string;
    birthdate: string;
    constructor(name: string, email: string, birthdate: string, actualEmail: string) {
        this.name = name;
        this.email = email;
        this.birthdate = birthdate;
        this.actualEmail = actualEmail;
    }
}