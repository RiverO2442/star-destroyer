import {useSession} from "next-auth/react";

export default function AccessTokenComponent() {
    const {data: accessToken} = useSession()
    return <div>Access Token: {JSON.stringify(accessToken)}</div>
}