package Classes;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class TreeNode extends Tree {
    TreeMap<Character, Tree> descendants = new TreeMap<>();
    List<String[]> list = new ArrayList<>();

    public void setDescendants(TreeMap<Character, Tree> descendants) {
        this.descendants = descendants;
    }

    public void setList(List<String[]> list) {
        this.list = list;
    }

    public TreeMap<Character, Tree> getDescendants() {
        return descendants;
    }

    public List<String[]> getList() {
        return list;
    }
}
