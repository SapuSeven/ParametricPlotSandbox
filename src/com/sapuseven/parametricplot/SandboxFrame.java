package com.sapuseven.parametricplot;

import com.sapuseven.parametricplot.formcomponents.CanvasPanel;

import javax.swing.*;
import java.awt.*;

public class SandboxFrame {
	private final String title;
	private final int width;
	private final int height;
	private JPanel root;
	private CanvasPanel canvas;
	private JButton start;
	private JSpinner n1spinner;
	private JSpinner n2spinner;
	private JSpinner maxPoints;
	private JSlider strokeWidth;
	private JCheckBox rainbow;
	private JButton reset;
	private JSpinner increment;
	private JSpinner fps;

	SandboxFrame(String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;

		createDisplay();
	}

	// Setup all GUI elements
	private void createDisplay() {
		JFrame frame = new JFrame(title);

		// Sliders & Spinners (top section)
		n1spinner.setModel(new SpinnerNumberModel(1.0, 0.01, 10.0, 0.01));
		n1spinner.addChangeListener(e -> canvas.setN1((double) n1spinner.getValue()));
		n2spinner.setModel(new SpinnerNumberModel(1.0, 0.01, 10.0, 0.01));
		n2spinner.addChangeListener(e -> canvas.setN2((double) n2spinner.getValue()));
		maxPoints.setModel(new SpinnerNumberModel(1000, 3, 10000, 1));
		maxPoints.addChangeListener(e -> canvas.setMaxPoints((int) maxPoints.getValue()));
		strokeWidth.addChangeListener(e -> canvas.setStrokeWidth(strokeWidth.getValue() / 10));
		increment.setModel(new SpinnerNumberModel(0.05, 0.001, 1, 0.01));
		increment.addChangeListener(e -> canvas.setIncrement((double) increment.getValue()));
		fps.setModel(new SpinnerNumberModel(60, 1, 1000, 1));
		fps.addChangeListener(e -> canvas.setFps((int) fps.getValue()));
		rainbow.addActionListener(e -> canvas.setRainbow(rainbow.isSelected()));

		// Buttons (bottom section)
		start.addActionListener(e -> canvas.startStop());
		reset.addActionListener(e -> canvas.reset());

		// The canvas is for plotting the function
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setMaximumSize(new Dimension(width, height));
		canvas.setMinimumSize(new Dimension(width, height));
		canvas.setFocusable(false);
		canvas.setBackground(Color.BLACK);
		canvas.init(this);

		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setContentPane(root);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public void setStartButtonText(String text) {
		start.setText(text);
	}
}