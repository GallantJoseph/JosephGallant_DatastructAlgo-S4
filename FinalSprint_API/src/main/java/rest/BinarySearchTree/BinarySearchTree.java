package rest.BinarySearchTree;

import jakarta.persistence.*;

import java.util.LinkedList;
import java.util.List;

@Entity
public class BinarySearchTree {
    @Id
    @SequenceGenerator(name = "binary_tree_sequence", sequenceName = "binary_tree_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "binary_tree_sequence")
    private Long id;
    private List<Integer> numbers = new LinkedList<>();

    @Column(length = 65536)
    private String jsonTree;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public void setNumbers(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public String getJsonTree() {
        return jsonTree;
    }

    public void setJsonTree(String jsonTree) {
        this.jsonTree = jsonTree;
    }
}
