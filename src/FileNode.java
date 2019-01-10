

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FileNode {
	private final File file;
	private final List<FileNode> children;
	private int depth;

	public int getDepth() {
		return depth;
	}

	public void setDepth(final int depth) {
		this.depth = depth;
	}

	public FileNode(final File file) {
		if (file == null) {
			throw new IllegalArgumentException();
		}
		this.file = file;
		this.children = new ArrayList<>();
	}
	
	public File getFile() {
		return file;
	}

	public FileNode addChild(final File child, final int depth) {
		FileNode childNode = new FileNode(child);
		addChild(childNode, depth);
		return childNode;
	}
	
	public boolean addChild(final FileNode child, final int depth) {
		if (child == null) {
			throw new IllegalArgumentException();
		}
		child.setDepth(depth);
		
		return this.children.add(child);
	}

	public boolean addChildren(Collection<FileNode> children) {
		if (children == null) {
			throw new IllegalArgumentException();
		}
		return this.children.addAll(children);
	}

	public List<FileNode> getChildren(){
		List<FileNode> cloned = new ArrayList<>();
		cloned.addAll(this.children);
		return cloned;
	}
	
	public void removeChilderen() {
		this.children.removeAll(children);
	}
	
}