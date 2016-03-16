import java.io.*;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Comparator;

import distance.EditDist;
import tree.LblTree;

//job: understands how to compute a tree edit distance matrix
public class TreeEditDistanceMatrix extends TreeEditDistance{

    public TreeEditDistanceMatrix(Path homeDir, String lang) throws IOException {
        super(homeDir, lang);
    }

	public double[][] compute() throws Exception {
	 	File[] listOfFiles = getFiles();
	 	LblTree[] trees = new LblTree[listOfFiles.length];
	 	for (int i = 0; i < listOfFiles.length; i++) 
	 	{
            trees[i] = buildTree(listOfFiles[i]);
	 	}
	 	double[][] distmatrix = new double[listOfFiles.length][listOfFiles.length];
	 	for (int i = 0; i < listOfFiles.length; i++) 
	 	{
//	 		if ((i % 50) == 0){
//	 			System.out.println("TED in progress... " + i);
//	 		}
		 	for (int j = i; j < listOfFiles.length; j++) 
		 	{
		 		double dist = new EditDist(true).nonNormalizedTreeDist(trees[i],trees[j]);
		 		distmatrix[i][j] = dist;
		 		distmatrix[j][i] = dist;
		 	}
	 	}
        return  distmatrix;
	}

}
