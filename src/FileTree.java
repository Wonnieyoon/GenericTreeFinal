

import java.io.File;

public class FileTree {

	private final FileNode rootFileNode;
	private boolean initialized = false;

	public FileTree(final String rootDir) {
		if (rootDir == null) {
			throw new IllegalArgumentException();
		}
		
		if (!new File(rootDir).exists()) {
			throw new NullPointerException("�������� �ʴ� ���");
		}
		
		this.rootFileNode = new FileNode(new File(rootDir));
	}

	public FileTree(final File rootDir) {
		if (rootDir == null) {
			throw new IllegalArgumentException();
		}
		
		if (!rootDir.exists()) {
			throw new NullPointerException("�������� �ʴ� ���");
		}
		
		this.rootFileNode = new FileNode(rootDir);
	}

	public FileTree(final FileNode rootFileNode) {
		if (rootFileNode == null) {
			throw new IllegalArgumentException();
		}
		this.rootFileNode = rootFileNode;
	}

	public synchronized void initialize() {
		if (initialized) {
			return;
		}
		rootFileNode.removeChilderen();
		addChildrenRecursively(rootFileNode);
		this.initialized = true;
	}

	private void addChildrenRecursively(FileNode parentNode) {

		File[] childrenFile = parentNode.getFile().listFiles();
		
		int depth = parentNode.getDepth();
		
		for (File child : childrenFile) {
			if(child.isFile()) {
				continue;
			}
			addChildrenRecursively(parentNode.addChild(child, depth+1));
		}

	}
	
	public FileNode getRootNode() {
		if (!initialized) {
			initialize();
		}
		return this.rootFileNode;
	}
	
}
