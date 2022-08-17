package Classes;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Tree {
    TreeNode root = new TreeNode();

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    public void put(String[] input, int n) {

        TreeNode tree = this.getRoot();
        var s = input[n];
        for(char ch : s.toLowerCase().toCharArray()) {
            if(!tree.getDescendants().containsKey(ch)) {
                tree.getDescendants().put(ch,new TreeNode());
            }
            tree = (TreeNode) tree.getDescendants().get(ch);
        }
        tree.getList().add(input);
    }
    public List<String[]> getAllStartingWith(String s) {
        TreeNode treeNode = this.getRoot();
        for(char ch : s.toLowerCase().toCharArray()) {
            if(!treeNode.getDescendants().containsKey(ch)) {
                return new ArrayList<>();
            }else {
                treeNode = (TreeNode) treeNode.getDescendants().get(ch);
            }
        }
        var res = new ArrayList<TreeNode>();
        getAllDescendants(treeNode, res);
        return res.stream().flatMap(x -> x.getList().stream()).collect(Collectors.toList());
    }

    private void getAllDescendants(TreeNode currentRoot, ArrayList<TreeNode> res) {
        if(currentRoot.getList() != null) {
            res.add(currentRoot);
        }
        for(var descendant : currentRoot.getDescendants().keySet()) {
            getAllDescendants((TreeNode) currentRoot.getDescendants().get(descendant), res);
        }
    }
}
