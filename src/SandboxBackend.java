import javax.swing.*;

class SandboxBackend {

	private SandboxBackend() {
		int height = 600;
		int width = 600;
		SandboxFrame display = new SandboxFrame("ParametricPlotSandbox", width, height); // Initialize the GUI
	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}

		SandboxBackend sandbox = new SandboxBackend();
	}
}
