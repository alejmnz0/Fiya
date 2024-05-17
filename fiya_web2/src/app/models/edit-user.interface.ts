export class EditUser {
    name: string;
    email: string;
    birthdate: string;
    constructor(name: string, email: string, birthdate: string) {
        this.name = name;
        this.email = email;
        this.birthdate = birthdate;
    }
}