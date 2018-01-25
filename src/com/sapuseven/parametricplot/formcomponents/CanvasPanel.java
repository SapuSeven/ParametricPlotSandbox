package com.sapuseven.parametricplot.formcomponents;

import com.sapuseven.parametricplot.SandboxFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CanvasPanel extends JPanel {
	private Timer timer;
	private double t = 0;
	private final ArrayList<Point> points = new ArrayList<>();
	private SandboxFrame frame;
	private int deletedPoints = 0;
	private int fps = 60;
	private double increment = 0.05;
	private final ActionListener timerAction = e -> redraw();
	private double n1 = 1, n2 = 1;
	private final int scale = 250;
	private int maxPoints = 1000;
	private float strokeWidth = 3;
	private boolean rainbow;

	public void init(SandboxFrame frame) {
		this.frame = frame;

		int timerDelay = 1000 / fps;
		timer = new Timer(timerDelay, timerAction);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;

		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);

		g2d.setColor(Color.WHITE);
		g2d.setStroke(new BasicStroke(strokeWidth));

		points.add(new Point((int) Math.round(scale * Math.sin(n1 * t)), (int) Math.round(scale * Math.sin(n2 * t + Math.PI / 2))));
		if (points.size() > 1) {
			while (points.size() >= maxPoints) {
				points.remove(0);
				deletedPoints++;
			}
			for (int i = 1; i < points.size(); i++) {
				Point p1 = points.get(i - 1);
				Point p2 = points.get(i);
				if (rainbow)
					g2d.setColor(Color.getHSBColor((i + deletedPoints) * 0.001f, 1, 1));
				g2d.drawLine(
						(int) Math.round(p1.getX()) + getWidth() / 2, (int) Math.round(p1.getY()) + getHeight() / 2,
						(int) Math.round(p2.getX()) + getWidth() / 2, (int) Math.round(p2.getY()) + getHeight() / 2
				);
			}
		}

		g2d.dispose();
	}

	private void redraw() {
		t += increment;
		repaint();
	}

	public void setN1(double n1) {
		this.n1 = n1;
		reset();
	}

	public void setN2(double n2) {
		this.n2 = n2;
		reset();
	}

	public void setMaxPoints(int maxPoints) {
		this.maxPoints = maxPoints;
	}

	public void reset() {
		t = 0;
		points.clear();
		deletedPoints = 0;
		if (!timer.isRunning())
			repaint();
	}

	public void setStrokeWidth(float strokeWidth) {
		this.strokeWidth = strokeWidth;
	}

	public void startStop() {
		if (timer.isRunning()) {
			timer.stop();
			frame.setStartButtonText("Start");
		} else {
			timer.start();
			frame.setStartButtonText("Stop");
		}
	}

	public void setIncrement(double increment) {
		this.increment = increment;
	}

	public void setFps(int fps) {
		this.fps = fps;

		timer.setDelay(1000 / fps);
	}

	public void setRainbow(boolean rainbow) {
		this.rainbow = rainbow;
	}
}
