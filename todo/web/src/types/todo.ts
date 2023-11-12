export interface ITodo {
    id: string ;
    title: string;
    completed: boolean;
    description: string | undefined;
}

export enum Filter {
    all = "all",
    active = "active",
    completed = "completed",
}

export const absurd = (_value: never): any => {
};
