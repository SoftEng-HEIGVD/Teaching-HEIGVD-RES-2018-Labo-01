package ch.heigvd.res.lab01.impl.explorers;

import ch.heigvd.res.lab01.interfaces.IFileExplorer;
import ch.heigvd.res.lab01.interfaces.IFileVisitor;
import java.io.File;
import java.util.ArrayList;

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
       // First directory
    vistor.visit(rootDirectory);

    // If root isn't a directory, we do nothing
    if(!rootDirectory.isDirectory())
      return;

    // We store the list of files and directories which are in the root directory
    File[] listOfFiles = rootDirectory.listFiles();
    // Use of an arraylist of Files to store directories
    ArrayList<File> listOfDirectories = new ArrayList<File>();

    // Visit the file or add the directory
    for(File file : listOfFiles)
      if(file.isFile()) // if it's a file
        vistor.visit(file);
      else // if it's a directory
        listOfDirectories.add(file);

    // Browse the directories and explore them
    for(int i = 0; i < listOfDirectories.size(); ++i)
      explore(listOfDirectories.get(i), vistor);
  }

}
