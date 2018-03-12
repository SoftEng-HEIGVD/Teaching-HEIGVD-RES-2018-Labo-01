package ch.heigvd.res.lab01.impl.explorers;

import ch.heigvd.res.lab01.interfaces.IFileExplorer;
import ch.heigvd.res.lab01.interfaces.IFileVisitor;
import java.io.File;
import java.util.Arrays;

/**
 * This implementation of the IFileExplorer interface performs a depth-first
 * exploration of the file system and invokes the visitor for every encountered
 * node (file and directory). When the explorer reaches a directory, it visits all
 * files in the directory and then moves into the subdirectories.
 * 
 * @author Olivier Liechti
 */
public class DFSFileExplorer implements IFileExplorer {
  /**
   * Launches the DFS exporation of the file system
   * @param rootDirectory the folder the exploration starts from
   * @param visitor an IFIleVisitor that implements the visit(File) function
   *                that performs a custom action with any file/directory DFS visits
   */
  @Override
  public void explore(File rootDirectory, IFileVisitor visitor) {
    visitor.visit(rootDirectory);
    // Launch the DFS
    if (rootDirectory.isDirectory()) {
      runFileSystem(rootDirectory.listFiles(), visitor);
    }
  }

  /**
   * Runs a pre ordered DFS over the file system
   * @param files Files in the current directory
   * @param visitor Implements the visit(File) method
   */
  private void runFileSystem(File[] files, IFileVisitor visitor) {
    Arrays.sort(files);
    for (File file : files) {
      visitor.visit(file);
      if (file.isDirectory()) {
        runFileSystem(file.listFiles(), visitor);
      }
    }
  }

}
