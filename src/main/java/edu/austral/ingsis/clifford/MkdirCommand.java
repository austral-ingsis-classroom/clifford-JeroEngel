package edu.austral.ingsis.clifford;

public class MkdirCommand implements Command {

  private final String directoryName;
  private final FileSystem fileSystem;

  public MkdirCommand(FileSystem fileSystem, String directoryName) {
    this.directoryName = directoryName;
    this.fileSystem = fileSystem;
  }

  @Override
  public String execute() {
    new Directory(directoryName, fileSystem.getCurrentDirectory());
    return "'" + directoryName + "' directory created";
  }
}
