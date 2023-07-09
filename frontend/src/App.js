import {Route, Routes} from "react-router-dom";
import Layout from "./Layout";
import NotFoundPage from "./components/pages/NotFoundPage";
import IndexPage from "./components/pages/IndexPage";
import UserPage from "./components/pages/UserPage";
import Logout from "./components/pages/Logout";
import {useEffect, useState} from "react";
import LoginPage from "./components/pages/LoginPage";
import RegisterPage from "./components/pages/RegisterPage";



function App() {
    const [user, setUser] = useState(null);

    useEffect(() => {
        setUser(localStorage.getItem("user"))
    }, [user]);
    return (
        <div className="App">
            {user &&
            <Routes>
                <Route path="/" element={<Layout/>}>

                    <Route index element={<IndexPage/>}/>{/*
              <Route path="friends" element={<Friends />}/>
              <Route path="people" element={<People/>}/>
              <Route path="logout" element={<Logout/>}/>
              <Route path="user/:login" element={<UserPage/>}/>*/}
                    <Route path="user/:login" element={<UserPage/>}/>
                    <Route path="logout" element={<Logout setUser={setUser}/>}/>
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
