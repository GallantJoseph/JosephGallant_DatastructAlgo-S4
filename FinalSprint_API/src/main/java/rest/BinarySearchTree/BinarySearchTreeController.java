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
    BinarySearchTreeService binarySearchTreeService;

    @GetMapping
    public ResponseEntity<Iterable<BinarySearchTree>> getAllBinarySearchTrees(){
        return ResponseEntity.ok(binarySearchTreeService.getAllBinarySearchTrees());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BinarySearchTree> getBinarySearchTreeById(@PathVariable Long id,
                                                                    @RequestParam("balanced_tree") Boolean balancedTree){

        BinarySearchTree binarySearchTree = binarySearchTreeService.getBinarySearchTreeById(id);

        if (binarySearchTree == null){
            return ResponseEntity.notFound().build();
        }

        if (balancedTree) {
            //TODO Code to balance the tree here
        }

        return ResponseEntity.ok(binarySearchTree);
    }

    @PostMapping
    public ResponseEntity<String> createBinarySearchTree(@RequestBody List<Integer> numbers){
        if (numbers.isEmpty()) {
            return ResponseEntity.badRequest().body("At least one number is required to build a tree.");
        }

        TreeNode binarySearchTreeRootNode = binarySearchTreeService.createBinarySearchTree(numbers);

        return ResponseEntity.ok(binarySearchTreeService.saveBinarySearchTree(numbers, binarySearchTreeRootNode).getJsonTree());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBinarySearchTree(@PathVariable Long id){
        binarySearchTreeService.deleteAircraftById(id);
        return ResponseEntity.ok().build();
    }
}
