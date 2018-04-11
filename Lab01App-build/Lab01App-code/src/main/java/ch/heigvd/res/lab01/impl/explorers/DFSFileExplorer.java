package ch.heigvd.res.lab01.impl.explorers;

import ch.heigvd.res.lab01.interfaces.IFileExplorer;
import ch.heigvd.res.lab01.interfaces.IFileVisitor;
import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * This implementation of the IFileExplorer interface performs a depth-first
 * exploration of the file system and invokes the visitor for every encountered
 * node (file and directory). When the explorer reaches a directory, it visits all
 * files in the directory and then moves into the subdirectories.
 * 
 * @author Olivier Liechti
 * @author Iando Rafidimalala
 */
public class DFSFileExplorer implements IFileExplorer {

  @Override
  public void explore(File rootDirectory, IFileVisitor visitor) {
    //visit then explore the node even if this node is a directory  
    visitor.visit(rootDirectory);
    
    if(rootDirectory.isDirectory()){
       
        //List all nodes on this directory and order the nodes according to the expected output
        File[] listNodes = rootDirectory.listFiles();
        Arrays.sort(listNodes);
        
        //Visit all files
        for(File node : listNodes){
            if(node.isFile()){
               visitor.visit(node);
            }else if(node.isDirectory()){
                explore(node, visitor);
            }
        }  
    }
  }

}
