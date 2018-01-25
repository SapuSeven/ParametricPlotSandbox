package com.sapuseven.parametricplot;

import javax.swing.*;

class SandboxBackend {

	private SandboxBackend() {
		int height = 600;
		int width = 600;
		new SandboxFrame("ParametricPlotSandbox", width, height);
	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}

		new SandboxBackend();
	}
}
