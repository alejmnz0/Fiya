export class EditTeam {
    name: string;
    actualName: string;
    urlImage: string;
    primaryColor: number;
    secondaryColor: number;
    constructor(name: string, urlImage: string, secondaryColor: number, primaryColor: number, actualName: string) {
        this.name = name;
        this.urlImage = urlImage;
        this.secondaryColor = secondaryColor;
        this.primaryColor = primaryColor;
        this.actualName = actualName;
    }
}