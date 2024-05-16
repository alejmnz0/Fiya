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
    latitude: string;
    longitude: string;
    description: string;
    price: number;
    teamCapacity: number;
    ground: string;
}

