export interface FieldListResponse {
    content: Field[];
    size: number;
    totalElements: number;
    pageNumber: number;
    first: boolean;
    last: boolean;
}

export interface Field {
    id: number;
    name: string;
    latitude: number;
    longitude: number;
    description: string;
    price: number;
    teamCapacity: number;
    ground: string;
}

