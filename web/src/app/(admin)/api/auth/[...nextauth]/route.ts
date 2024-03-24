import NextAuth, {AuthOptions} from "next-auth"
import GithubProvider from "next-auth/providers/github"
import Google from "next-auth/providers/google"
import axios, {AxiosError, HttpStatusCode} from "axios";


export const authOptions: AuthOptions = {
    providers: [
        GithubProvider({
            clientId: process.env.GITHUB_ID!,
            clientSecret: process.env.GITHUB_SECRET!,
        }),
        Google({
            clientId: process.env.GOOGLE_CLIENT_ID!,
            clientSecret: process.env.GOOGLE_CLIENT_SECRET!,
        })
    ],
    callbacks: {
        async jwt({token, user, account, profile}) {
            if (account && account.provider === "google") {
                token.accessToken = account.id_token
            }
            return token;
        },
        async redirect({baseUrl}) {
            return "/"
        },
        async session({token, session}) {
            // Send properties to the client, like an access_token and user id from a provider.
            console.log("Session: ", session, token)
            return {
                ...session,
                accessToken: token.accessToken
            }
        },
        async signIn({user, account, profile, email, credentials}) {
            console.log("Sign in: ", user, account, profile, email, credentials)
            try {
                await axios.get(`${process.env.NEXT_PUBLIC_API_HOST}/api/v1/users/${user.id}`)
            } catch (error: any) {
                error = error as AxiosError
                if (error.response && error.response.status === HttpStatusCode.NotFound) {
                    try {
                        await axios.post(`${process.env.NEXT_PUBLIC_API_HOST}/api/v1/register`, null, {
                            headers: {
                                Authorization: `Bearer ${account?.id_token}`
                            }
                        })
                    } catch (registerError: any) {
                        registerError = registerError as AxiosError
                        if (registerError.response.status !== HttpStatusCode.Ok) {
                            return false;
                        }
                    }
                } else {
                    console.error('Register error:', error.message);
                    return false;
                }
            }
            return true;
        }
    },
    pages: {
        signIn: "/signin",
        error: "/signin/error"
    },
    session: {
        strategy: "jwt"
    },
    debug: true,
    secret: process.env.NEXTAUTH_SECRET,
}
const handler = NextAuth(authOptions);

export {handler as GET, handler as POST}
