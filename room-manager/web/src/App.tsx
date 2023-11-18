import styled, {DefaultTheme, ThemeProvider} from "styled-components";
import {darkTheme, lightTheme} from "./styles/theme.ts";
import {FC} from "react";
import {TodoList} from "./components/TodoList.tsx";
import useLocalStorage from "./hooks/useLocalStorage.ts";
import DarkModeIcon from "@mui/icons-material/DarkMode"
import GlobalStyles from "./styles/global.ts";
import AuthContextProvider from "./components/AuthContext.jsx";

const App: FC = () => {
    const [theme, setTheme] = useLocalStorage<DefaultTheme>("theme", darkTheme);

    const themeToggle = () => {
        const newTheme = theme === darkTheme ? lightTheme : darkTheme;
        setTheme(newTheme);
    };

    return (
        <ThemeProvider theme={theme}>
            <AuthContextProvider>
            <Header>📓 Todo List</Header>
                <TodoList/>
            </AuthContextProvider>
            <Footer>
                Double click on todo to edit <br/>
                <a target="_blank" href="https://www.maxim-grinev-resume.ru/">
                    © TikTzuki
                </a>
            </Footer>
            <ThemeToggle onClick={themeToggle}>
                <DarkModeIcon fontSize="medium"/>
            </ThemeToggle>
            <GlobalStyles/>
        </ThemeProvider>
    );
};

export const AppContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  padding-top: 40px;
`;

export const Header = styled.h1`
  text-align: center;
  font-size: 48px;
  padding: 50px 0 50px 0;
`;

export const Footer = styled.h6`
  text-align: center;
  font-size: 14px;
  font-weight: 200;
  font-style: italic;
  opacity: 0.5;
  padding-top: 25px;
  padding-bottom: 25px;
`;

export const ThemeToggle = styled.button`
  background: none;
  border: none;
  font-size: 24px;
  color: ${(props) => props.theme.colors.text};
  cursor: pointer;
  position: absolute;
  top: 20px;
  right: 20px;
`;

export default App;
