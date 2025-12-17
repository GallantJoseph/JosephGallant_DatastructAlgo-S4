package rest.BinarySearchTree;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BinarySearchTreeService {
    @Autowired
    BinarySearchTreeRepository binarySearchTreeRepository;

    public TreeNode buildBinarySearchTree(int[] values) {
        TreeNode root = null;

        for (int value : values) {
            root = insertValue(root, value);
        }

        return root;
    }

    protected TreeNode insertValue(TreeNode currentNode, int value) {
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

    public String buildJSONTree(TreeNode rootNode) {
        String jsonTree = "";

        try {
            ObjectMapper objectMapper = new ObjectMapper();

            Map<String, Object> wrapper = new HashMap<>();
            wrapper.put("root", rootNode);

            jsonTree = objectMapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(wrapper);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonTree;
    }

    public BinarySearchTree saveBinarySearchTree(BinarySearchTree binarySearchTree) {
        return binarySearchTreeRepository.save(binarySearchTree);
    }

    public TreeNode createBinarySearchTree(List<Integer> numbers) {
        if (numbers.isEmpty()) {
            return null;
        }

        int[] numbersArray = numbers.stream()
                .mapToInt(i -> i)
                .toArray();

        return buildBinarySearchTree(numbersArray);
    }
}
