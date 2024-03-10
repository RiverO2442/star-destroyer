"use client"


import ErrorPage from "@/components/ErrorPage";

export default function Error({reset}: { reset: () => void }) {
    return <ErrorPage reset={reset}/>;
}
