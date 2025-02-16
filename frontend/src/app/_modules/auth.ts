export interface registerUser {
  username: string;
  email: string;
  password: string;
}

export interface loginUser {
  username: string;
  password: string;
}

export interface authenticateResponse {
  token: string;
  refreshToken: string;
}
