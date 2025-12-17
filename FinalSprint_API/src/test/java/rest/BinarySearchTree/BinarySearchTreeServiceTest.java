package rest.BinarySearchTree;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;


@ExtendWith(MockitoExtension.class)
class BinarySearchTreeServiceTest {
    @Mock
    BinarySearchTreeRepository binarySearchTreeRepository;

    @InjectMocks BinarySearchTreeService binarySearchTreeService;

    @Test
    void testBuildBinarySearchTree() {
        int[] testNumbers = {1,8,3};

        TreeNode treeNodeUnderTest = binarySearchTreeService.buildBinarySearchTree(testNumbers);

        Assertions.assertEquals(1, treeNodeUnderTest.getValue());
        Assertions.assertEquals(8, treeNodeUnderTest.getRight().getValue());
        Assertions.assertEquals(3, treeNodeUnderTest.getRight().getLeft().getValue());
    }

    @Test
    void testSaveBinarySearchTree() {
        List<Integer> testNumbers = new ArrayList<>(3);

        testNumbers.add(1);
        testNumbers.add(8);
        testNumbers.add(3);

        String jsonTree = "BST Tree Data";

        BinarySearchTree binarySearchTreeUnderTest = new BinarySearchTree();
        binarySearchTreeUnderTest.setId(1L);
        binarySearchTreeUnderTest.setNumbers(testNumbers);
        binarySearchTreeUnderTest.setJsonTree(jsonTree);

        Mockito.when(binarySearchTreeService.saveBinarySearchTree(binarySearchTreeUnderTest)).thenReturn(binarySearchTreeUnderTest);

        BinarySearchTree resultBinarySearchTree = binarySearchTreeService.saveBinarySearchTree(binarySearchTreeUnderTest);

        Assertions.assertEquals(binarySearchTreeUnderTest.getId(), resultBinarySearchTree.getId());
        Assertions.assertEquals(binarySearchTreeUnderTest.getNumbers(), resultBinarySearchTree.getNumbers());
        Assertions.assertEquals(binarySearchTreeUnderTest.getJsonTree(), resultBinarySearchTree.getJsonTree());
    }

    @Test
    void testInsertValue() {
        int[] testNumbers = {2,1,7,6};

        TreeNode rootNode = new TreeNode(testNumbers[0]);

        rootNode = binarySearchTreeService.insertValue(rootNode, testNumbers[1]);
        rootNode = binarySearchTreeService.insertValue(rootNode, testNumbers[2]);
        rootNode = binarySearchTreeService.insertValue(rootNode, testNumbers[3]);

        Assertions.assertEquals(2, rootNode.getValue());
        Assertions.assertEquals(1, rootNode.getLeft().getValue());
        Assertions.assertEquals(7, rootNode.getRight().getValue());
        Assertions.assertEquals(6, rootNode.getRight().getLeft().getValue());

        Assertions.assertNull(rootNode.getLeft().getLeft());
    }
}