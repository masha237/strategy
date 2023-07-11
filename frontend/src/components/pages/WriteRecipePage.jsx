import React from 'react';
import "./../../styles/form.css"
import RecipeForm from "./molecules/RecipeForm";
const WriteRecipePage = () => {

    return (
        <div>
            <div>Напишите рецепт</div>
            <RecipeForm/>
        </div>
    );
};

export default WriteRecipePage;