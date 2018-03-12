package ch.heigvd.res.lab01.impl.explorers;

import ch.heigvd.res.lab01.interfaces.IFileExplorer;
import ch.heigvd.res.lab01.interfaces.IFileVisitor;
import java.io.File;
import java.lang.reflect.Array;
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
  public void explore(File rootDirectory, IFileVisitor vistor) {

      //check if the directory is not null
      if(!rootDirectory.isDirectory()){
          return;
      }

      vistor.visit(rootDirectory);

      //give a list of each file(s)/directory(ies) from the current directory
      File[] children = rootDirectory.listFiles();

      if(children == null){
          return;
      }

      Arrays.sort(children);

      //call the function recursively with each child from the current directory
      for (File child : children) {
          explore(child, vistor);
      }
  }
}
