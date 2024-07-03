import { createBrowserRouter, createRoutesFromElements, Route } from "react-router-dom";
import Home from "../pages/home/Home";

export const Router = createBrowserRouter(createRoutesFromElements(
    <>
        <Route path="/" element={<Home />} />
        <Route path="/home" element={<Home />} />
    </>
));