import './PreviousBSTs.css'
import { useState } from "react"
import {getAllBSTs} from '../bstapi/BST_API_Service.js'


function PreviousBSTs() {
    const [binarySearchTrees, setBinarySearchTrees] = useState();
    const [errorMessage, setErrorMessage] = useState(null);
    const [successMessage, setSuccessMessage] = useState(null);

    const getBinaryTrees = async () => {
            setErrorMessage(null);
            setSuccessMessage(null);
        try {
            const data = await getAllBSTs();

            setBinarySearchTrees(data);
        } catch (error) {
            setErrorMessage("An error occurred while fetching the data.");
        }
        
    }

    return (
        <>
            <h1>Binary Search Trees</h1>
            <button onClick={getBinaryTrees}>Get All</button>
            <p id='error-message'>{errorMessage && errorMessage}</p>
            <p id='success-message'>{successMessage && successMessage}</p>
            <>
            <div id='bst-data-container'>
                <ul>
                    {binarySearchTrees && binarySearchTrees.map((bst) => (
                        <li key={bst.id}>
                            <pre className='bst-element'>
                                ID: {bst.id}<br/>
                                Numbers selected:  {bst.numbers.toString().replaceAll(",", ", ")}
                            </pre>

                            <h3>Binary Search Tree Representation</h3>
                            <pre className='bst-json-element'>{bst.jsonTree}</pre>
                        </li>
                    ))}
                </ul>
            </div>
            </>
        </>
    );
}

export default PreviousBSTs;