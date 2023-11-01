import {ITodo} from "../types/todo.ts";
import {useState} from "react";
import {todoApi} from "./dataFetcher.ts";
import {AxiosResponse} from "axios";

export function useTodos(initialTodos: ITodo[]) {
    const [todos, setTodos] = useState<ITodo[]>(initialTodos);

    const addTodo = (title: string): void => {
        const newTodo: ITodo = {
            id: "",
            title: title,
            completed: false,
            description: undefined
        };
        todoApi.post("/todos", newTodo)
            .then((resp: AxiosResponse<ITodo, undefined>) => {
                console.log(resp)
                setTodos((prevState) => [...prevState, resp.data]);
            })
    };

    const toggleTodo = (id: string): void => {
        setTodos((prevState) =>
            prevState.map((todo) => {
                if (todo.id === id) {
                    return {
                        ...todo,
                        completed: !todo.completed,
                    };
                }
                return todo;
            })
        );
    };

    const editTodo = (it: ITodo): void => {
        todoApi.put(`/todos/${it.id}`, {
            title: it.title,
            description: it.description,
            completed: it.completed
        }).then(() => {
            setTodos((prevState) => {
                return prevState.map((todo) => {
                    if (todo.id === it.id) {
                        return {
                            ...todo,
                            title: it.title,
                            description: it.description,
                            completed: it.completed
                        };
                    }
                    return todo;
                });
            });
        })
    };

    const removeTodo = (id: string): void => {
        todoApi.delete(`/todos/${id}`).then(() => {
            setTodos((prevState) =>
                prevState.filter((todo) => todo.id !== id)
            );
        })
    };

    const getItemsLeft = () => {
        return todos.reduce((counter, todo) => {
            if (!todo.completed) {
                return counter + 1;
            }
            return counter;
        }, 0);
    };

    const removeCompleted = (): void => {
        setTodos((prevState) => prevState.filter((todo) => !todo.completed));
    };

    return {
        todos,
        setTodos,
        addTodo,
        toggleTodo,
        editTodo,
        removeTodo,
        getItemsLeft,
        removeCompleted,
    };
}
