package com.myfirst.example.internal;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

import com.myfirst.example.dialogs.FileTree;
import com.myfirst.example.dialogs.MyTitleAreaDialog;

public class DropHandler implements IHandler {
	private static final String PARM_MSG = "com.myfirst.example.msg";

	@Override
	public void addHandlerListener(IHandlerListener handlerListener) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		// TODO Auto-generated method stub
		String msg = event.getParameter(PARM_MSG);
		if (msg == null) {
			System.out.println("No message");
		} else if (msg.equals("Hello")) {
			
			IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
			MessageDialog.openInformation(
					window.getShell(),
					"Example",
					"Hello, Eclipse world");
		} else if (msg.equals("Title")) {
			MyTitleAreaDialog dialog = new MyTitleAreaDialog(HandlerUtil.getActiveWorkbenchWindow(event).getShell());
			dialog.create();
			if (dialog.open() == Window.OK) {
				System.out.println(dialog.getFirstName());
				System.out.println(dialog.getLastName());
			}
			System.out.println(msg + "<--msg");

		}else if(msg.equals("workspace")){
			FileTree fileTree = new FileTree(HandlerUtil.getActiveWorkbenchWindow(event).getShell());
			fileTree.create();
			
		}else {
			System.out.println("Check the Source");
		}
		return null;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isHandled() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void removeHandlerListener(IHandlerListener handlerListener) {
		// TODO Auto-generated method stub

	}

}
