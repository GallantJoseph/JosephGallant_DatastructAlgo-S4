package rest.BinarySearchTree;

import org.antlr.v4.runtime.tree.Tree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BinarySearchTreeService {
    @Autowired
    BinarySearchTreeRepository binarySearchTreeRepository;

    private TreeNode insertValue(TreeNode currentNode, int value) {
        if (currentNode == null) {
            return new TreeNode(value);
        }

        if (value <= currentNode.value) {
            currentNode.left = insertValue(currentNode.left, value);
        } else {
            currentNode.right = insertValue(currentNode.right, value);
        }

        return currentNode;
    }

    public TreeNode buildBinarySearchTree(int[] values) {
        TreeNode root = null;

        for (int value : values) {
            root = insertValue(root, value);
        }

        return root;
    }

    public String buildJSONTree(TreeNode rootNode) {
        // TODO Traverse the tree to build the JSON String
        return null;
    }

    public Iterable<BinarySearchTree> getAllBinarySearchTrees() {
        return binarySearchTreeRepository.findAll();
    }

    public BinarySearchTree getBinarySearchTreeById(Long id) {
        return binarySearchTreeRepository.findById(id).orElse(null);
    }

    public BinarySearchTree saveBinarySearchTree(List<Integer> inputNumbers, TreeNode rootNode) {
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        binarySearchTree.setNumbers(inputNumbers);

        // TODO Generate the JSON data and save it to the string
        binarySearchTree.setJsonTree(inputNumbers.toString());

        return binarySearchTreeRepository.save(binarySearchTree);
    }

    public TreeNode createBinarySearchTree(List<Integer> numbers) {
        if (numbers.isEmpty()) {
            return null;
        }

        // Create the new BinarySearchTree from the numbers
        int[] numbersArray = numbers.stream()
                .mapToInt(i -> i)
                .toArray();

        return buildBinarySearchTree(numbersArray);
    }

    public void deleteAircraftById(Long id) {
        binarySearchTreeRepository.deleteById(id);
    }
}
