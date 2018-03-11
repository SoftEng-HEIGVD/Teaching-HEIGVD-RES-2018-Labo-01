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
    visitor.visit(rootDirectory);
    exploreUnderRootDirectory(rootDirectory, visitor);
  }

  private void exploreUnderRootDirectory(File directory, IFileVisitor visitor) {
    File[] folderContent = directory.listFiles();
    if (null != folderContent)  // listFiles can return null if the directory does not exists
      for (File f : folderContent) {
          visitor.visit(f);
          if (f.isDirectory())
            exploreUnderRootDirectory(f, visitor);
        }
  }
}
