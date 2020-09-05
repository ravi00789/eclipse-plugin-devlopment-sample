package com.myfirst.example.dialogs;

import java.io.File;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.events.ShellListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.core.resources.ResourcesPlugin;

public class FileTree extends Dialog {
	static String s = "";
	
	public FileTree(Shell parentShell) {
		super(parentShell);
	}

	public void create() {
		super.create();
		final Display display1 = Display.getDefault();
		final Shell shell1 = new Shell(display1);
		shell1.setText("Tree");
		shell1.setLayout(new FillLayout());
		final Tree tree = new Tree(shell1, SWT.BORDER | SWT.MULTI);
		File file = new File(ResourcesPlugin.getWorkspace().getRoot().getLocation().toString());
		File[] roots = file.listFiles();
		for (int i = 0; i < roots.length; i++) {
			TreeItem root = new TreeItem(tree, 0);
			root.setText(roots[i].getName().toString());
			root.setData(roots[i]);
			new TreeItem(root, 0);

		}
		


		tree.addListener(SWT.Expand, new Listener() {
			public void handleEvent(final Event event) {
				final TreeItem root = (TreeItem) event.item;
				TreeItem[] items = root.getItems();

				for (int i = 0; i < items.length; i++) {
					if (items[i].getData() != null)
						return;
					items[i].dispose();
				}
				File file = (File) root.getData();
				File[] files = file.listFiles();
				if (files == null)
					return;

				for (int i = 0; i < files.length; i++) {
					TreeItem item1 = new TreeItem(root, 0);
					item1.setText(files[i].getParent());
					s = item1.getText();
					 System.out.println("item.gettext:="+item1.getText());
					if (files[i].isHidden())
						continue;
					TreeItem item = new TreeItem(root, 0);
					item.setText(files[i].getName());

					item.setData(files[i]);
					
					if (files[i].isDirectory()) {
						new TreeItem(item, 0);
					}
					
				}
				
				//System.out.println("item.gettext:=" + s);
				TreeItem[] selection = tree.getSelection();
				for (int j = 0; j < selection.length; j++) {
					File file1 = (File) selection[j].getData();
					if (file1.isDirectory()) {
						s = file1.getParent();
						System.out.println("folder : " + file1.getAbsolutePath());
					} else if (file1.isFile()) {
						System.out.println("file : " + file1.getAbsolutePath());
						s = file1.getParent();
					}
				}
			}
		});

		Point size = tree.computeSize(300, SWT.DEFAULT);
		int width = Math.max(300, size.x);
		int height = Math.max(300, size.y);
		shell1.setSize(shell1.computeSize(width, height));

		Button button1 = new Button(shell1, SWT.PUSH);
		button1.setText("Export");
		button1.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				System.out.println("Export Button is Click:=" + s);

				new File(s);
				System.out.println("Export FolderUpload Object created:=" + s);
				System.out.println("Export Button File:=" + s);
				s.toLowerCase();
				System.out.println("Export Button is Click:=" + s);
				System.out.println("Export Button File:=" + s);
				
				System.out.println("File Tree Export Out");
			}

		});
		shell1.addShellListener(new ShellListener() {

			public void shellActivated(ShellEvent e) {
			}

			public void shellIconified(ShellEvent e) {
			}

			public void shellDeiconified(ShellEvent e) {
			}

			public void shellDeactivated(ShellEvent e) {
			}

			public void shellClosed(ShellEvent e) {
				TreeItem[] selection = tree.getSelection();
				for (int i = 0; i < selection.length; i++) {
					File file = (File) selection[i].getData();
					if (file.isDirectory()) {
					//	s = file.getName();
						System.out.println("folder : " + file.getName());
					} else if (file.isFile()) {
						System.out.println("file : " + file.getName());
						//s = file.getName();
					}
				}
			}

		});

		shell1.open();
		while (!shell1.isDisposed()) {

			if (!display1.readAndDispatch()) {
				display1.sleep();
			}
		}

	}

}