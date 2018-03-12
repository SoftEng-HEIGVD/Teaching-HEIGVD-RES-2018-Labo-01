package ch.heigvd.res.lab01.impl.explorers;

import ch.heigvd.res.lab01.interfaces.IFileExplorer;
import ch.heigvd.res.lab01.interfaces.IFileVisitor;
import java.io.File;
import java.io.IOException;
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

    /*
     *@brief : Explore every subdirectory of a given File with a given visitor
     */
    @Override
    public void explore(File rootDirectory, IFileVisitor vistor)  {

        vistor.visit(rootDirectory);
        if (rootDirectory.listFiles() != null)
            try {
                dfs(rootDirectory, vistor);
            } catch (IOException ioe) {
                throw new RuntimeException();
            }
    }

    /*
    * @brief recursively go through all subdirectories of a given directory
    *
     */
    private void dfs(File root, IFileVisitor visitor) throws IOException {
        File[] files = root.listFiles();

        if (files == null)
            return;
        Arrays.sort(files); //Sort files before visiting them
        for (File file : files) {
            visitor.visit(file);
            dfs(file, visitor);
        }
    }

}
