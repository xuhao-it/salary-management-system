package com.xuhao.payroll.util;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BackgroundAnimation extends Region {
    private Canvas canvas;
    private GraphicsContext gc;
    private List<Particle> particles;
    private Random random;
    private AnimationTimer timer;

    public BackgroundAnimation() {
        canvas = new Canvas();
        gc = canvas.getGraphicsContext2D();
        particles = new ArrayList<>();
        random = new Random();

        // 将Canvas添加到Region
        getChildren().add(canvas);

        // 绑定Canvas大小到Region大小
        canvas.widthProperty().bind(widthProperty());
        canvas.heightProperty().bind(heightProperty());

        // 当Canvas大小改变时重新初始化粒子
        canvas.widthProperty().addListener((obs, old, newVal) -> initParticles());
        canvas.heightProperty().addListener((obs, old, newVal) -> initParticles());

        // 启动动画
        startAnimation();
    }

    private void initParticles() {
        particles.clear();
        int numParticles = 50;
        for (int i = 0; i < numParticles; i++) {
            particles.add(new Particle());
        }
    }

    private void startAnimation() {
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                draw();
            }
        };
        timer.start();
    }

    private void draw() {
        if (canvas.getWidth() <= 0 || canvas.getHeight() <= 0)
            return;

        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.setFill(Color.rgb(30, 30, 30, 0.1));
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        for (Particle particle : particles) {
            particle.update();
            particle.draw(gc);
        }
    }

    private class Particle {
        private double x, y;
        private double speedX, speedY;
        private double size;
        private Color color;

        public Particle() {
            reset();
        }

        private void reset() {
            x = random.nextDouble() * canvas.getWidth();
            y = random.nextDouble() * canvas.getHeight();
            speedX = -1 + random.nextDouble() * 2;
            speedY = -1 + random.nextDouble() * 2;
            size = 2 + random.nextDouble() * 3;
            color = Color.rgb(200, 200, 200, 0.5 + random.nextDouble() * 0.5);
        }

        public void update() {
            x += speedX;
            y += speedY;

            if (x < 0 || x > canvas.getWidth())
                speedX *= -1;
            if (y < 0 || y > canvas.getHeight())
                speedY *= -1;
        }

        public void draw(GraphicsContext gc) {
            gc.setFill(color);
            gc.fillOval(x - size / 2, y - size / 2, size, size);

            // 绘制连接线
            for (Particle other : particles) {
                double distance = Math.sqrt(Math.pow(x - other.x, 2) + Math.pow(y - other.y, 2));
                if (distance < 100) {
                    gc.setStroke(Color.rgb(200, 200, 200, 0.1));
                    gc.setLineWidth(0.5);
                    gc.strokeLine(x, y, other.x, other.y);
                }
            }
        }
    }
}