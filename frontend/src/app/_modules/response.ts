export interface profileResponse {
  username: string;
  totalProducts: number;
  totalSold: number;
  listings: Array<listingResponse>;
}

export interface listingResponse {
  title: string;
  description: string;
  price: number;
  category: string;
  image: string;
  id: number;
  username: string;
  number: string;
}
