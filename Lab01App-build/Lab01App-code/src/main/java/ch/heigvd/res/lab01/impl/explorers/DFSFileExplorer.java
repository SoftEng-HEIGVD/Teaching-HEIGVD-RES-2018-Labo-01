package ch.heigvd.res.lab01.impl.explorers;

import ch.heigvd.res.lab01.interfaces.IFileExplorer;
import ch.heigvd.res.lab01.interfaces.IFileVisitor;
import java.io.File;
import java.nio.channels.FileLockInterruptionException;
import java.util.Arrays;

/**
 * This implementation of the IFileExplorer interface performs a depth-first
 * exploration of the file system and invokes the visitor for every encountered
 * node (file and directory). When the explorer reaches a directory, it visits all
 * files in the directory and then moves into the subdirectories.
 * 
 * @author Olivier Liechti
 * @author Jérémie Châtillon
 */
public class DFSFileExplorer implements IFileExplorer {

  @Override
  public void explore(File rootDirectory, IFileVisitor vistor) {


    if(rootDirectory == null)
      return;


    vistor.visit(rootDirectory);

    File[] lfs = rootDirectory.listFiles();

    if(lfs == null)
      return;

    // DFS explore on each directories ordered
    Arrays.sort(lfs);
    for (File lf: lfs){
      if(lf.isDirectory())
        explore(lf, vistor);
    }

    // Apply the visit method on each files
    for (File lf: lfs){
      if(lf.isFile())
        vistor.visit(lf);
    }

  }

}
