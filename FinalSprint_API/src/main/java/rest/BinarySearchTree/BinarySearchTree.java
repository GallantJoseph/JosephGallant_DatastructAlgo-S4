package rest.BinarySearchTree;

import jakarta.persistence.*;

@Entity
public class BinarySearchTree {
    @Id
    @SequenceGenerator(name = "binary_tree_sequence", sequenceName = "binary_tree_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "binary_tree_sequence")
    private Long id;
}
