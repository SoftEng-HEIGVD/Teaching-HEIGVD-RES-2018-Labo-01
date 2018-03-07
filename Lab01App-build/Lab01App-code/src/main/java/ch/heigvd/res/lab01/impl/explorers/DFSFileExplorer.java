package ch.heigvd.res.lab01.impl.explorers;

import ch.heigvd.res.lab01.interfaces.IFileExplorer;
import ch.heigvd.res.lab01.interfaces.IFileVisitor;
import java.io.File;
import java.util.Collections;
import java.util.List;

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
    // We get the list of Directories and Files present in rootDirectory (alphanumerical descending order).
    File[] dir = rootDirectory.listFiles();

    // We visit the current directory.
    visitor.visit(rootDirectory);
    
    // For each file or directory in rootDirectory (alphabetical ascending order).
    for (int i = dir.length; i > 0; --i) {
      // We recover the File from the tab "dir".
      File f = dir[i - 1];
      
      // If it is a directory, we explore it.
      // Otherwise, we visit the file.
      if(f.isDirectory()) {
        explore(f, visitor);
      } else {
        visitor.visit(f);
      }
    }
  }
}
