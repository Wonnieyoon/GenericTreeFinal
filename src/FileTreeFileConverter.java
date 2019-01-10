

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileTreeFileConverter implements Convertable<FileTree,File>{

	private static final String CRNL = "\r\n";
	private static final String SPACE = "    ";
	private static final String CHILD_SYMBOL = "└";
	private final String saveFilePath; 
	private FileTree fileTree;
	private FileWriter fileWriter;
	private boolean initialized = false;

	public FileTreeFileConverter(String saveFilePath) {		
		this.saveFilePath = saveFilePath;
	}
	
	public FileTree getFileTree() {
		return fileTree;
	}

	public void setFileTree(FileTree fileTree) {
		if (fileTree == null) {
			throw new IllegalArgumentException();
		}
		this.fileTree = fileTree;
	}	
	
	public void initialize()
	{
		if(initialized)
			return;
		
		try {
			fileWriter = new FileWriter(saveFilePath);			
		} catch (IOException e) {
			e.printStackTrace();
		}			
		 
	}	
	
	@Override
	public File convert(FileTree source) {		
		setFileTree(source);
		initialize();		
		writeToFile(fileTree.getRootNode().getFile().getAbsolutePath() +CRNL);		
		convertFileNodeRecursively(fileTree.getRootNode());
		try {
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new File(saveFilePath);
	}

	public void convertFileNodeRecursively(final FileNode parentNode) {
		List<FileNode> children = parentNode.getChildren();
		
		for (FileNode childNode : children) {
			writeToFile(getDepthSpace(childNode.getDepth()) + childNode.getFile().getName() + CRNL);
			convertFileNodeRecursively(childNode);
		}
	}

	private String getDepthSpace(final int depth) {
		StringBuilder spaceBuilder = new StringBuilder();

		for (int i = 0; i < depth; i++) {
			spaceBuilder.append(SPACE);
		}
		return spaceBuilder.append(CHILD_SYMBOL).toString();
	}
	
	private void writeToFile(String data) {
		try {			
			fileWriter.write(data);
			fileWriter.flush();
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}
}
