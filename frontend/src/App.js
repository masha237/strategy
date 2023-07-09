import {Route, Routes} from "react-router-dom";
import Layout from "./Layout";
import * as PropTypes from "prop-types";
import NotFoundPage from "./components/pages/NotFoundPage";
import IndexPage from "./components/pages/IndexPage";


IndexPage.propTypes = {jwt0: PropTypes.any};

function App() {
  return (
    <div className="App">
      <Routes>
          <Route path="/" element={<Layout/>}>

              <Route index element={<IndexPage/>}/>{/*
              <Route path="friends" element={<Friends />}/>
              <Route path="people" element={<People/>}/>
              <Route path="logout" element={<Logout/>}/>
              <Route path="user/:login" element={<UserPage/>}/>*/}
              <Route path="*" element={<NotFoundPage/>}/>
          </Route>
      </Routes>
    </div>
  );
}

export default App;
