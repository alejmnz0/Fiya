export class AddField {
    name: string;
    latitude: number;
    longitude: number;
    price: number;
    teamCapacity: number;
    ground: string;
    constructor(name: string, latitude: number, longitude: number, price: number, teamCapacity: number, ground: string) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.price = price;
        this.teamCapacity = teamCapacity;
        this.ground = ground;
    }
}