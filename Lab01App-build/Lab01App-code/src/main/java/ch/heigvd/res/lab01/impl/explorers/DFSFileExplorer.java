package ch.heigvd.res.lab01.impl.explorers;

import ch.heigvd.res.lab01.interfaces.IFileExplorer;
import ch.heigvd.res.lab01.interfaces.IFileVisitor;
import java.io.File;
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
  public void explore(File rootDirectory, IFileVisitor vistor) {
      if(rootDirectory==null)
          return;
      vistor.visit(rootDirectory);
      File[] files ;
          files=rootDirectory.listFiles();
          //if there is no files it should not work
      if(files==null)
          return;
      for(File file:files){
          if(file.isFile())
              //if he is a file we visit 
              vistor.visit(file);
          else
              //is directory so we explore
              explore(file, vistor);
      }
  }

}
