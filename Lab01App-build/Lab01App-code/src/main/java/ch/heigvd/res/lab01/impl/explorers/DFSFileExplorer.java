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
 */
public class DFSFileExplorer implements IFileExplorer {

  @Override
  public void explore(File rootDirectory, IFileVisitor vistor) {
      // visit the File
     vistor.visit(rootDirectory);
     //test if it Directory 
  if(rootDirectory.isDirectory()){
      // get list of all files in Directory 
      File fileList[] = rootDirectory.listFiles();
      if(fileList!=null){
      Arrays.sort(fileList); // sort the list
         for(File file: fileList) {
             if(file.isFile()){
                 // visit if it is the file
                  vistor.visit(file); 
             }else{
                 // explore if it is a Directory
                 explore(file, vistor);
             }
         }
      }    
      
  }
  }

}
