export const DEFAULT_AVATAR = "/images/unknow-cat/jpeg";

export interface User {
    id: string;
    username: string,
    name: string;
    email: string;
    avatar: string;
}

export function newUser(args: {
    id: string;
    username: string,
    name: string;
    email: string;
    avatar?: string;
}): User {
    return {
        avatar: args.avatar || DEFAULT_AVATAR,
        ...args
    }
}

export interface LoginRequest {
    username: string;
    password: string;
}

// {
//     "iss": "https://accounts.google.com",
//     "azp": "759254302934-h0u67i38q8idqkvvjr5v0dmfmni59a0m.apps.googleusercontent.com",
//     "aud": "759254302934-h0u67i38q8idqkvvjr5v0dmfmni59a0m.apps.googleusercontent.com",
//     "sub": "105508277509507699531",
//     "email": "tranphanthanhlong18@gmail.com",
//     "email_verified": true,
//     "nbf": 1707676209,
//     "name": "Trần Phan Thanh Long",
//     "picture": "https://lh3.googleusercontent.com/a/ACg8ocLUQ7VeT_nUb2d5SrjhQMc3rJycf9-7X0c8LEnlIJhwqQ=s96-c",
//     "given_name": "Trần Phan",
//     "family_name": "Thanh Long",
//     "locale": "vi",
//     "iat": 1707676509,
//     "exp": 1707680109,
//     "jti": "fa7f88696b08ff631500e7a03d0773cd81a6946e"
// }

export interface JwtPayload {
    iss: string;
    sub: string;
    email: string;
    name: string;
    picture: string;
    givenName: string;
    familyName: string;
}