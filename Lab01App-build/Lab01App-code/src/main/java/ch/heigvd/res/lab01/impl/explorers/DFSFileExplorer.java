package ch.heigvd.res.lab01.impl.explorers;

import ch.heigvd.res.lab01.interfaces.IFileExplorer;
import ch.heigvd.res.lab01.interfaces.IFileVisitor;
import java.io.File;
import java.util.LinkedList;

/**
 * This implementation of the IFileExplorer interface performs a depth-first
 * exploration of the file system and invokes the visitor for every encountered
 * node (file and directory). When the explorer reaches a directory, it visits all
 * files in the directory and then moves into the subdirectories.
 * 
 * @author Olivier Liechti
 * @author Yosra Harbaoui
 */
public class DFSFileExplorer implements IFileExplorer {

  @Override
  public void explore(File rootDirectory, IFileVisitor vistor) {
    /**
     * 1. explore the current directory 
     */ 
    vistor.visit(rootDirectory);
    
    /**
     * 2. find out if the current directory is empty
     */ 
    if(rootDirectory.listFiles() == null)
        return;
   
    /**
     * 3. if current directory has sub-directories/files, we store them into a list for later exploration
     */
    LinkedList<File> currentDirectoryFiles = new LinkedList<>();
    
    /**
     * 4. explore the current directory :
     *    visit files 
     *    add subdirectories to the list for later exploration
     */
    for (File f : rootDirectory.listFiles())
    {
        if(f.isDirectory()){
            // currentDirectoryFiles.add(f);
            explore(f, vistor);
        }
        else {
            vistor.visit(f);
        }
    }
    
    /**
     * 5. explore subdirectories of the current directory
     */
    /*for (File subDirectory : currentDirectoryFiles)
    {
        explore(subDirectory, vistor);
    }*/
  }
}
