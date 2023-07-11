import {Route, Routes} from "react-router-dom";
import Layout from "./Layout";
import NotFoundPage from "./components/pages/NotFoundPage";
import IndexPage from "./components/pages/IndexPage";
import UserPage from "./components/pages/UserPage";
import Logout from "./components/pages/Logout";
import {useEffect, useState} from "react";
import LoginPage from "./components/pages/LoginPage";
import RegisterPage from "./components/pages/RegisterPage";
import UsersPage from "./components/pages/UsersPage";
import WriteRecipePage from "./components/pages/WriteRecipePage";



function App() {
    const [user, setUser] = useState(null);

    useEffect(() => {
        console.log(localStorage.getItem("user"))
        setUser(JSON.parse(localStorage.getItem("user")))
    }, []);

    return (

        <div className="App">
            {user &&
            <Routes>
                <Route path="/" element={<Layout user={user}/>}>
                    <Route index element={<IndexPage user={user}/>}/>
                    <Route path="user/:login" element={<UserPage/>}/>
                    <Route path="logout" element={<Logout setUser={setUser}/>}/>
                    <Route path="writePost" element={<WriteRecipePage/>}/>
                    {user.role === "ADMIN" &&
                    <Route path="users" element={<UsersPage user={user}/>}/>}
                    <Route path="*" element={<NotFoundPage/>}/>
                </Route>
            </Routes>
            }
            {!user &&
                <Routes>
                    <Route path="/" element={<Layout/>}>
                        <Route path="*" element={<LoginPage setUser={setUser}/>}/>
                        <Route index element={<LoginPage setUser={setUser}/>}/>
                        <Route path="register" element={<RegisterPage/>}/>
                    </Route>
                </Routes>
            }
        </div>
    );
}

export default App;
