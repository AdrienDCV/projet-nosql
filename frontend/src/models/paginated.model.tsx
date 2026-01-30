export type Paginated<T> = {
  content: T[];
  pageNumber: number
  pageSize: number;
  totalElement: number;
  totalPages: number;
}