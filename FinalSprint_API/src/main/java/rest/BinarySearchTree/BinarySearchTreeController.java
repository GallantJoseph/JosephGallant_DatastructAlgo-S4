package rest.BinarySearchTree;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bst")
@CrossOrigin
public class BinarySearchTreeController {
    @Autowired
    BinarySearchTreeRepository binarySearchTreeRepository;

    @Autowired
    BinarySearchTreeService binarySearchTreeService;

    @GetMapping
    public ResponseEntity<Iterable<BinarySearchTree>> getAllBinarySearchTrees(){
        return ResponseEntity.ok(binarySearchTreeRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BinarySearchTree> getBinarySearchTreeById(@PathVariable Long id,
                                                                    @RequestParam(value = "balanced_tree", required = false) Boolean balancedTree){

        BinarySearchTree binarySearchTree = binarySearchTreeRepository.findById(id).orElse(null);

        if (binarySearchTree == null){
            return ResponseEntity.notFound().build();
        }

        if (balancedTree != null && balancedTree) {
            //TODO Code to balance the tree here
        }

        return ResponseEntity.ok(binarySearchTree);
    }

    @PostMapping
    public ResponseEntity<String> createBinarySearchTree(@RequestBody List<Integer> numbers){
        if (numbers.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        TreeNode rootNode = binarySearchTreeService.createBinarySearchTree(numbers);
        String jsonTree = binarySearchTreeService.buildJSONTree(rootNode);

        BinarySearchTree binarySearchTree = new BinarySearchTree();
        binarySearchTree.setNumbers(numbers);
        binarySearchTree.setJsonTree(jsonTree);

        binarySearchTreeService.saveBinarySearchTree(binarySearchTree);

        return ResponseEntity.ok(jsonTree);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBinarySearchTreeById(@PathVariable Long id){
        binarySearchTreeRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
