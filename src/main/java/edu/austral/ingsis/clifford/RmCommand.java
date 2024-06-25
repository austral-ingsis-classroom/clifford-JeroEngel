package edu.austral.ingsis.clifford;

import java.util.List;

public class RmCommand implements Command{

    private final String fileName;
    private final FileSystem fileSystem;
    private final boolean recursive;

    public RmCommand(FileSystem fileSystem, String fileName, boolean recursive) {
        this.fileName = fileName;
        this.fileSystem = fileSystem;
        this.recursive = recursive;
    }
  @Override
  public String execute() {
      List<Node> children = fileSystem.getCurrentDirectory().getChildren();
        for (Node child : children) {
            if (child.getName().equals(fileName)) {
                if (child instanceof Directory) {
                    if (recursive) {
                        fileSystem.getCurrentDirectory().removeChild(child);
                        return "'" + fileName + "' removed";
                    } else {
                        return "cannot remove '" + fileName + "', is a directory";
                    }
                } else {
                    fileSystem.getCurrentDirectory().removeChild(child);
                    return "'" + fileName + "' removed";
                }
            }
        }
        return "file or directory not found";
  }
}
