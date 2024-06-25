package edu.austral.ingsis.clifford;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LsCommand implements Command {
  private final FileSystem fileSystem;
  private final String order;

  public LsCommand(FileSystem fileSystem, String order) {
    this.fileSystem = fileSystem;
    this.order = order;
  }

  @Override
  public String execute() {
    List<Node> children = fileSystem.getCurrentDirectory().getChildren();
    List<String> names = children.stream().map(Node::getName).collect(Collectors.toList());
    if (order != null) {
      if (order.equals("asc")) {
        names.sort(String::compareTo);
      } else if (order.equals("desc")) {
        names.sort(Collections.reverseOrder());
      } else {
        return "Order not found";
      }
    }
    return String.join(" ", names);
  }
}
