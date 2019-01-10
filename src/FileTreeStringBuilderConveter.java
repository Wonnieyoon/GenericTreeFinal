

import java.util.List;

public class FileTreeStringBuilderConveter implements Convertable<FileTree,StringBuilder> {

	private static final String CRNL = "\r\n";
	private static final String SPACE = "    ";
	private static final String CHILD_SYMBOL = "��";

	private final StringBuilder converted;

	public FileTreeStringBuilderConveter(FileTree fileTree) {
		if (fileTree == null) {
			throw new IllegalArgumentException();
		}
		this.converted = new StringBuilder();

	}
	
	@Override
	public StringBuilder convert(FileTree source) {
		converted.append(source.getRootNode().getFile().getAbsolutePath()).append(CRNL);
		convertFileNodeRecursively(source.getRootNode());
		return converted;
	}

	public void convertFileNodeRecursively(final FileNode parentNode) {
		List<FileNode> children = parentNode.getChildren();
		
		for (FileNode childNode : children) {
			converted.append(getDepthSpace(childNode.getDepth()))
				.append(childNode.getFile().getName())
				.append(CRNL);
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
