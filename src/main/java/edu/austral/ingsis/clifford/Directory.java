package edu.austral.ingsis.clifford;

import java.util.ArrayList;
import java.util.List;

public class Directory implements Node {
  private final String name;
  private final Directory parent;
  private final List<Node> children;

  public Directory(String name, Directory parent) {
    this.name = name;
    this.parent = parent;
    this.children = new ArrayList<>();
    parent.addChild(this);
  }

  public Directory(String name) {
    this.name = name;
    this.parent = null;
    this.children = new ArrayList<>();
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public Directory getParent() {
    return parent;
  }

  void addChild(Node node) {
    children.add(node);
  }

  public List<Node> getChildren() {
    return children;
  }

  public void removeChild(Node node) {
    children.remove(node);
  }
}
