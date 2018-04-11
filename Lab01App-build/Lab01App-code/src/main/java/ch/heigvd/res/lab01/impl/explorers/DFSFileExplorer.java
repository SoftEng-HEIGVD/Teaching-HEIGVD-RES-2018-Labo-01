package ch.heigvd.res.lab01.impl.explorers;

import ch.heigvd.res.lab01.interfaces.IFileExplorer;
import ch.heigvd.res.lab01.interfaces.IFileVisitor;
import java.io.File;

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
//    throw new UnsupportedOperationException("The student has not implemented this method yet.");

    try {

      visitor.visit(rootDirectory);

      // get all the files from the directory
      File[] dirContent = rootDirectory.listFiles();
      // no alphab. sort needed, we move to subdirectories recursively first and then visit files.
      for (File file : dirContent){
        if (file.isDirectory() ) {
          explore(file, visitor);
        }
      }
      for (File file : dirContent){
        if (file.isFile() ) {
          visitor.visit(file);
        }
      }
    }
    catch (Exception e) {
      System.out.println("booom! " + e.getMessage());
    }

  }

}
