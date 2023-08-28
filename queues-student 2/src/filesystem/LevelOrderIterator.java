package filesystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.NoSuchElementException;
//import java.util.Queue;
import structures.*;


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
	UnboundedQueueInterface<File> queue;
	public LevelOrderIterator(File rootNode) throws FileNotFoundException {
        	// TODO 1
			if (rootNode.exists()){
				queue = new structures.Queue<File>();
				queue.enqueue(rootNode);
				// LevelOrderIterator iter = new LevelOrderIterator(rootNode);
				// if (rootNode.isDirectory()){
					
				// }
				//recursiveHelper(rootNode);
					
			}
			else {
				throw new FileNotFoundException();
			}

	}

	public void recursiveHelper(File file){
		File[] listFiles;
		if (file.isDirectory()){
			listFiles = file.listFiles();
			for (int i = 0; i < listFiles.length ;i++){
				queue.enqueue(listFiles[i]);
			}
			for (int i = 0; i < listFiles.length ;i++){
				if (listFiles[i].isDirectory()){
					recursiveHelper(listFiles[i]);
				}
			}
		}
		else{
			queue.enqueue(file);	
		}
	}
	
	@Override
	public boolean hasNext() {
        	// TODO 2
            return (!queue.isEmpty());
	}

	@Override
	public File next() throws NoSuchElementException {
        	// TODO 3
			if (!hasNext())
				throw new NoSuchElementException();
			File deqFile = queue.dequeue();
			if (deqFile.isDirectory()){
				File[] childrenNodes = deqFile.listFiles();
				Arrays.sort(childrenNodes);
				for ( int i = 0; i<childrenNodes.length;i++){
					queue.enqueue(childrenNodes[i]);
				}
			}
			System.out.println("Visited deqFile: " + deqFile);
			return deqFile;
			//return queue.dequeue();
	}

	@Override
	public void remove() {
		// Leave this one alone.
		throw new UnsupportedOperationException();		
	}

}
