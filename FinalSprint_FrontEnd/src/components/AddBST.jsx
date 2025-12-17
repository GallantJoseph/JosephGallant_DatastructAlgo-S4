import './PreviousBSTs.css'
import { useState } from "react"
import {postBST} from '../bstapi/BST_API_Service.js'

function AddBST() {
    const [binarySearchTree, setBinarySearchTree] = useState();
    const [errorMessage, setErrorMessage] = useState(null);
    const [successMessage, setSuccessMessage] = useState(null);

    const handleSubmit = async (event) => {
        event.preventDefault();
        setErrorMessage(null);
        setSuccessMessage(null);

        let numbers;        

        numbers = event.target.bstNumbersTextBox.value;

        if (numbers.trim() == "") {
            setErrorMessage("Please enter a least one number.");
            return;
        }

        numbers = numbers.split(",");

        if (numbers.some(isNaN)) {
            setErrorMessage("Invalid format. Numbers must be separated by a comma.");
            return;
        }

        try {
            const data = await postBST(numbers);
            
            setBinarySearchTree(JSON.stringify(data, null, " "));
            setSuccessMessage("Binary Search Tree saved to the database.")
        } catch (error) {
            setErrorMessage("An error has occurred while sending the numbers.")
        }
    }

    return (
        <>
            <h1>Generate a Binary Search Tree</h1>
            <form name="addBSTForm" id="addBSTForm" onSubmit={handleSubmit}>
                <label htmlFor="bstNumbersTextBox">Enter the numbers:</label>
                <input type="text" name="bstNumbersTextBox" />
                <button type='submit'>Generate Now</button>
                <p id='error-message'>{errorMessage && errorMessage}</p>
                <p id='success-message'>{successMessage && successMessage}</p>
            </form>

            {binarySearchTree && (
                <div id='bst-data-container'>
                    <ul>
                        <li>
                            <h3>Generated Tree</h3>
                            <pre className='bst-json-element'>{binarySearchTree && binarySearchTree}</pre>
                        </li>
                    </ul>

                </div>
            )}


        </>
    );
}

export default AddBST;