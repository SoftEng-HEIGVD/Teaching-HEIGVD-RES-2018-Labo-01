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
 * @author Walid Koubaa
 */
public class DFSFileExplorer implements IFileExplorer {

  @Override
  public void explore(File rootDirectory, IFileVisitor vistor) {
    vistor.visit(rootDirectory);

      // in the case where the directory does not exists, we stop
      if (!rootDirectory.exists()) {
          return;
      }
      // Otherwise the directory exist and in that case we create an array
      // of the files in the directory, we sort this array so that we are sure
      // that it will always lists files in a unique way ( the same way for all)
      File[] files = rootDirectory.listFiles();
      Arrays.sort(files);

      // Firstly we check if it's a file and if so, we visit it
      for (File f : files) {
          if (f.isFile()) {
              vistor.visit(f);
          }

          // Secondly we check if it's a directory and if so, we explore its content
          if (f.isDirectory())
              explore(f, vistor);
      }
  }

}
