

import java.util.List;

public class FileTreeStringConverter implements Convertable<FileTree,String> {

	private static final String CRNL = "\r\n";
	private static final String SPACE = "    ";
	private static final String CHILD_SYMBOL = "└";
	private FileTree fileTree;
	private String converted;
	

	public FileTreeStringConverter() {		
		this.converted = "";
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
	
	@Override
	public String convert(FileTree source) {
		setFileTree(source);
		converted += fileTree.getRootNode().getFile().getAbsolutePath() +CRNL;
		convertFileNodeRecursively(fileTree.getRootNode());
		return converted;
	}

	public void convertFileNodeRecursively(final FileNode parentNode) {
		List<FileNode> children = parentNode.getChildren();
		
		for (FileNode childNode : children) {
			converted += getDepthSpace(childNode.getDepth()) + childNode.getFile().getName() + CRNL;
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

	

}
