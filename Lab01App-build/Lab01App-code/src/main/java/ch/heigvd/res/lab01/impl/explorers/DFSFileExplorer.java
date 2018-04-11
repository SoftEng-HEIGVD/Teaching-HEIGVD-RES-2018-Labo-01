package ch.heigvd.res.lab01.impl.explorers;

import ch.heigvd.res.lab01.interfaces.IFileExplorer;
import ch.heigvd.res.lab01.interfaces.IFileVisitor;
import java.io.File;
import java.util.Arrays;

/**
 * This implementation of the IFileExplorer interface performs a depth-first
 * exploration of the file system and invokes the visitor for every encountered
 * node (file and directory). When the explorer reaches a directory, it visits
 * all files in the directory and then moves into the subdirectories.
 *
 * @author Olivier Liechti modify by : Olivier Kopp
 */
public class DFSFileExplorer implements IFileExplorer {

   @Override
   public void explore(File rootDirectory, IFileVisitor vistor) {
      //sanity check
      if (rootDirectory == null) {
         return;
      }
      //visit the rootDirectory
      vistor.visit(rootDirectory);
      //list all file inside the rootDirectory, if it's a file, return null
      File[] list = rootDirectory.listFiles();
      if (list == null) {
         return;
      }
      Arrays.sort(list);
      //for each subfile or subdirectory with call the same function
      for (File f : list) {
         explore(f, vistor);
      }
   }

}
