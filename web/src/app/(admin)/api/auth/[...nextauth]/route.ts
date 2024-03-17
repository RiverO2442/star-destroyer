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
            return token;
        },
        async redirect({baseUrl}) {
            return "/"
        }
        // async session({session, token, user}) {
        //     // Send properties to the client, like an access_token from a provider.
        //     console.log("session", session);
        //     return session;
        // }
    },
    pages: {
        signIn: "/signin",
        error: "/signin/error"
    }
}

const handler = NextAuth(authOptions)

export {handler as GET, handler as POST}
