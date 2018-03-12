package ch.heigvd.res.lab01.impl.explorers;

import ch.heigvd.res.lab01.interfaces.IFileExplorer;
import ch.heigvd.res.lab01.interfaces.IFileVisitor;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * This implementation of the IFileExplorer interface performs a depth-first
 * exploration of the file system and invokes the visitor for every encountered
 * node (file and directory). When the explorer reaches a directory, it visits all
 * files in the directory and then moves into the subdirectories.
 *
 * @author Olivier Liechti
 * @author Christophe Joyet
 */

public class DFSFileExplorer implements IFileExplorer {

<<<<<<< HEAD
  @Override
  public void explore(File rootDirectory, IFileVisitor vistor) {
=======
    @Override
    public void explore(File rootDirectory, IFileVisitor vistor) {
>>>>>>> f97ef05725c91e591f4ab579d5896b1500bf67af

    vistor.visit(rootDirectory);

    if (rootDirectory.isDirectory()) {
      //create an array of all files contained in rootDirectory
      File[] files = rootDirectory.listFiles();
      Arrays.sort(files);
      if (files != null) {
        //recursive exploring
        for (File file : files) {
          if (file.isFile()) explore(file, vistor);
          if (file.isDirectory()) explore(file, vistor);
        }
      }

    }
  }
}