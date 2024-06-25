package edu.austral.ingsis.clifford;

public class TouchCommand implements Command {
  private final String fileName;
  private final FileSystem fileSystem;

  public TouchCommand(FileSystem fileSystem, String fileName) {
    this.fileName = fileName;
    this.fileSystem = fileSystem;
  }

  @Override
  public String execute() {
    new File(fileName, fileSystem.getCurrentDirectory());
    return "'" + fileName + "' file created";
  }
}
