import {JWT} from "next-auth/jwt";
import {Session} from "next-auth";

export interface DysonJWT extends JWT {
    accessToken?: string,
    provider?: string,
}

export interface DysonSession extends Session {
    accessToken?: string;
}

