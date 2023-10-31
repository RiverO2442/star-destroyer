import {ITodo} from "../types/todo.ts";
import {useState} from "react";
import {todoApi} from "./dataFetcher.ts";

export function useTodos(initialTodos: ITodo[]) {
    const [todos, setTodos] = useState<ITodo[]>(initialTodos);

    const addTodo = (name: string): void => {
        const newTodo = {
            id: Date.now(),
            name,
            completed: false,
        };
        todoApi.post("/todos", newTodo)
            .then(value => {
                console.log(value)
                setTodos((prevState) => [...prevState, newTodo]);
            })
    };

    const toggleTodo = (id: number): void => {
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

    const editTodo = (id: number, name: string): void => {
        setTodos((prevState) => {
            return prevState.map((todo) => {
                if (todo.id === id) {
                    return {
                        ...todo,
                        name,
                    };
                }
                return todo;
            });
        });
    };

    const removeTodo = (id: number): void => {
        setTodos((prevState) => prevState.filter((todo) => todo.id !== id));
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
        addTodo,
        toggleTodo,
        editTodo,
        removeTodo,
        getItemsLeft,
        removeCompleted,
    };
}
