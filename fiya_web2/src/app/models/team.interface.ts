export interface TeamListResponse {
    content: Team[];
    size: number;
    totalElements: number;
    pageNumber: number;
    first: boolean;
    last: boolean;
}

export interface Team {
    id: number;
    name: string;
    urlImage: string;
    primaryColor: number;
    secondaryColor: number;
}
