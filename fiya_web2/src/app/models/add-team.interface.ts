export class AddTeam {
    name: string;
    urlImage: string;
    primaryColor: number;
    secundaryColor: number;
    constructor(name: string, urlImage: string, primaryColor: number, secundaryColor: number) {
        this.name = name;
        this.urlImage = urlImage;
        this.primaryColor = primaryColor;
        this.secundaryColor = secundaryColor;
    }
}