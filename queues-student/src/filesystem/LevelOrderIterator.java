package filesystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.NoSuchElementException;
import structures.Queue;
//import java.util.Queue;



/**
 * An iterator to perform a level order traversal of part of a 
 * filesystem.
 */
public class LevelOrderIterator extends FileIterator<File> {
	
	/**
	 * Instantiate a new LevelOrderIterator, rooted at the rootNode.
	 * @param rootNode
	 * @throws FileNotFoundException if the rootNode does not exist
	 */
	Queue<File> queue;

	public LevelOrderIterator(File rootNode) throws FileNotFoundException { // iterator constructor
        	// TODO 1

			if (rootNode.exists()){ // if rootNode exists
				queue = new structures.Queue<File>(); // create new queue
				queue.enqueue(rootNode); // enqueue rootNode to queue
			}

			else { // otherwise, throwq exception
				throw new FileNotFoundException();
			}

	}
// attempted recursive method:

	// public void recursiveHelper(File file){
	// 	File[] listFiles;
	// 	if (file.isDirectory()){
	// 		listFiles = file.listFiles();
	// 		for (int i = 0; i < listFiles.length ;i++){
	// 			queue.enqueue(listFiles[i]);
	// 		}
	// 		for (int i = 0; i < listFiles.length ;i++){
	// 			if (listFiles[i].isDirectory()){
	// 				recursiveHelper(listFiles[i]);
	// 			}
	// 		}
	// 	}
	// 	else{
	// 		queue.enqueue(file);	
	// 	}
	// }
	
	@Override
	public boolean hasNext() { //method to check if queue has more nodes
        	// TODO 2
            return (!queue.isEmpty()); // return true or false if queue is empty
	}

	@Override
	public File next() throws NoSuchElementException { // get next in queue and enqueue children
        	// TODO 3

			if (!hasNext()) // if there is no next in queue
				throw new NoSuchElementException(); // throw exception

			File deqFile = queue.dequeue(); // create and intialize variable for removed file

			if (deqFile.isDirectory()){ // if the file is a directory

				File[] childrenNodes = deqFile.listFiles(); // create array of children files
				Arrays.sort(childrenNodes); // sort array alphabetically

				for ( int i = 0; i<childrenNodes.length;i++) { // go through array
					queue.enqueue(childrenNodes[i]); // enqueue each file
				}

			}
			
			return deqFile; // return deqeued file
			
	}

	@Override
	public void remove() {
		// Leave this one alone.
		throw new UnsupportedOperationException();		
	}

}
