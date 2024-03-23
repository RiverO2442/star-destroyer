import NextAuth, {AuthOptions} from "next-auth"
import GithubProvider from "next-auth/providers/github"
import Google from "next-auth/providers/google"

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
