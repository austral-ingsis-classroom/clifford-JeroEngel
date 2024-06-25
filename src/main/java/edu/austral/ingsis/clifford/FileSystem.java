package edu.austral.ingsis.clifford;

public class FileSystem {
  private final Directory root;
  private Directory currentDirectory;

  public FileSystem() {
    this.root = new Directory("");
    this.currentDirectory = root;
  }

  public Directory getRoot() {
    return root;
  }

  public Directory getCurrentDirectory() {
    return currentDirectory;
  }

  public void setCurrentDirectory(Directory currentDirectory) {
    this.currentDirectory = currentDirectory;
  }

  public String execute(String command) {
    String[] commandParts = command.split(" ");
    String commandName = commandParts[0];

    switch (commandName) {
      case "ls":
        String order = commandParts.length > 1 ? commandParts[1].split("=")[1] : null;
        return new LsCommand(this, order).execute();
      case "cd":
        String directoryNameCD = commandParts[1];
        return new CdCommand(this, directoryNameCD).execute();
      case "touch":
        String fileName = commandParts[1];
        return new TouchCommand(this, fileName).execute();
      case "mkdir":
        String directoryNameMKDIR = commandParts[1];
        return new MkdirCommand(this, directoryNameMKDIR).execute();
      case "pwd":
        return new PwdCommand(this).execute();
      case "rm":
        String nodeName = commandParts[1];
        boolean recursive = false;
        if (commandParts.length > 2) {
          nodeName = commandParts[2];
          recursive = commandParts[1].equals("--recursive");
        } else {
          nodeName = commandParts[1];
        }
        return new RmCommand(this, nodeName, recursive).execute();
      default:
        return "Command: " + commandName + "not found";
    }
  }
}
