package edu.austral.ingsis.clifford;

public class CdCommand implements Command {
  private final String directory;
  private final FileSystem fileSystem;

  public CdCommand(FileSystem fileSystem, String directory) {
    this.directory = directory;
    this.fileSystem = fileSystem;
  }

  @Override
  public String execute() {
    Directory currentDirectory = fileSystem.getCurrentDirectory();

    if (directory.equals("/")) {
      fileSystem.setCurrentDirectory(fileSystem.getRoot());
      return "moved to directory '/'";
    }
    String[] directories = directory.split("/");

    if (directories[0].equals("..")) {
      if (currentDirectory.getParent() != null) {
        fileSystem.setCurrentDirectory(currentDirectory.getParent());
        if (currentDirectory.getParent() == fileSystem.getRoot()) {
          return "moved to directory '/'";
        }
        return "moved to directory '" + currentDirectory.getParent().getName() + "'";
      } else {
        return "moved to directory '/'";
      }
    } else if (directories[0].equals(".")) {
      return "moved to directory: '" + currentDirectory.getName() + "'";
    } else {
      for (String dir : directories) {
        Directory newDirectory = getChildDirectory(currentDirectory, dir);
        if (newDirectory == null) {
          return "'" + directory + "' directory does not exist";
        }

        fileSystem.setCurrentDirectory(newDirectory);
        currentDirectory = newDirectory;
      }
      return "moved to directory '" + currentDirectory.getName() + "'";
    }
  }

  private Directory getChildDirectory(Directory currentDirectory, String name) {
    for (Node node : currentDirectory.getChildren()) {
      if (node instanceof Directory && node.getName().equals(name)) {
        return (Directory) node;
      }
    }
    return null;
  }
}
