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
  public void explore(File rootDirectory, IFileVisitor vistor) {
      
    // test if the root directory is not null 
    if (rootDirectory != null) {
        
        // visit the root Directory
            vistor.visit(rootDirectory);
            
            // text if it is the directory 
            if (rootDirectory.isDirectory()) {
                
                // retrieve the list of Elements from the directory
                File[] Elements = rootDirectory.listFiles();
                
                // check each elements
                for (File file : Elements) {
                    // If element is file
                    if (file.isFile()) {
                        //visit it
                        vistor.visit(file);
                    }
                }
                // if it is not a file it can be a directory 
                for (File file : Elements) {
                    // test if is a directory
                    if (file.isDirectory()) {
                        // explore it
                        explore(file, vistor);
                    }
                }

            }
        }
 
  }

}
