export interface UserListResponse {
    content: User[];
    size: number;
    totalElements: number;
    pageNumber: number;
    first: boolean;
    last: boolean;
}

export interface User {
    id: string;
    dni: string;
    name: string;
    email: string;
    image: string;
    birthdate: Date;
}
