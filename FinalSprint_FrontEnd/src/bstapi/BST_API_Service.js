import axios from "axios"

const API_URL = "http://localhost:8080";

export const getAllBSTs = async () => {
    return (
        await axios.get(`${API_URL}/bst`)
            .then(response => {
                return response.data;
            })
            .catch(error => {
                console.error('Error fetching the Binary Search Trees.', error);
                throw error;
            })
    );
}

export const postBST = async (numbers) => {
    return (
        await axios.post(`${API_URL}/bst`, numbers)
            .then(response => {
                return response.data;
            })
            .catch(error => {
                console.error('Error adding the Binary Search Tree.', error);
                throw error;
            })
    );
}