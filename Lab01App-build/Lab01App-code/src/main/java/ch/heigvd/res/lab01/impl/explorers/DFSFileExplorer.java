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

  @Override
  public void explore(File rootDirectory, IFileVisitor visitor) {
    visitor.visit(rootDirectory); // We start by visiting the current folder
    File[] folderContent = rootDirectory.listFiles(); // Fetches folder content

    if (null != folderContent) { // listFiles can return null if the directory does not exists

      for (File f : folderContent) {
        if (f.isDirectory())  // If it is a directory, we explore it
          explore(f, visitor);
        else  // We visit each file
          visitor.visit(f);
      }
    }
  }
}
