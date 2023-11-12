export interface Page<T> {
    content: T;
    size: number,
    numberOfElements: number,
    totalElements: number,
    totalPages: number
}