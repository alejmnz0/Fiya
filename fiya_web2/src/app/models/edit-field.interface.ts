export class EditField {
    name: string;
    latitude: number;
    longitude: number;
    price: number;
    teamCapacity: number;
    ground: string;
    description: string;
    constructor(name: string, latitude: number, longitude: number, price: number, teamCapacity: number, ground: string, description: string) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.price = price;
        this.teamCapacity = teamCapacity;
        this.ground = ground;
        this.description = description;
    }
}