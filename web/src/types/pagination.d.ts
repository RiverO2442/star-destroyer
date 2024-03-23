export interface PaginationMeta {
    pagination: {
        count: number;
        current_page: number;
        links: any;
        per_page: number;
        total: number;
        total_pages: number;
    };
}

export interface PageResponse<T> {
    content: T[],
    totalPages: number,
    totalElements: number,
    first: boolean,
    last: boolean,
    size: number,
    number: number
}

export type MetadataItem = {
    id: string,
    name: string
}