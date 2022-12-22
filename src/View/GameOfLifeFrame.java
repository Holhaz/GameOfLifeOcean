package View;

import Controller.MyProjectController;
import model.MyProjectData;
import service.OceanService;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GameOfLifeFrame extends JFrame {
    private static final int CELL_SIZE = 15;
    private static final Map<Integer, Color> CELL_COLORS = Map.of(0, Color.WHITE, 1, Color.BLUE,
            2, Color.RED, 3, Color.BLACK);
    private final MyProjectController controller = new MyProjectController(new OceanService());

    private final JPanel oceanPanel = new JPanel();
    private final JLabel predatorCountLabel = new JLabel();
    private final JLabel preyCountLabel = new JLabel();
    private final JButton nextButton = new JButton("Next");
    private final JButton resetButton = new JButton("Reset");
    private final JButton startButton = new JButton("Start");
    private final JTextField numberInput = new JTextField();

    public GameOfLifeFrame() {
        setTitle("My Project");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ocean panel
        oceanPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        add(oceanPanel, BorderLayout.CENTER);

        // control panel
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(4, 2));
        controlPanel.add(new JLabel("Count predator:"));
        controlPanel.add(predatorCountLabel);
        controlPanel.add(new JLabel("Count prey:"));
        controlPanel.add(preyCountLabel);
        controlPanel.add(nextButton);
        controlPanel.add(resetButton);
        controlPanel.add(new JLabel("Number of iterations:"));
        controlPanel.add(numberInput);
        controlPanel.add(startButton);
        add(controlPanel, BorderLayout.SOUTH);

        // event listeners
        nextButton.addActionListener(e -> updateOcean());
        resetButton.addActionListener(e -> resetOcean());
        startButton.addActionListener(e -> startInterval());
    }

    private void updateOcean() {
        MyProjectData data = controller.iterate();

        predatorCountLabel.setText(Integer.toString(data.predator));
        preyCountLabel.setText(Integer.toString(data.prey));

        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
        }

        oceanPanel.removeAll();
        oceanPanel.setLayout(new GridLayout(data.ocean.length, data.ocean[0].length));
        for (int[] row : data.ocean) {
            for (int cell : row) {
                JPanel cellPanel = new JPanel();
                cellPanel.setBackground(CELL_COLORS.get(cell));
                cellPanel.setPreferredSize(new Dimension(CELL_SIZE, CELL_SIZE));
                oceanPanel.add(cellPanel);
            }
        }

        oceanPanel.revalidate();
        oceanPanel.repaint();
    }

    private void resetOcean() {
        controller.reset();
        updateOcean();
    }

    private void startInterval() {
        int iterations = Integer.parseInt(numberInput.getText());
        final int[] i = {0};

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (i[0] >= iterations) {
                    timer.cancel();
                    return;
                }

                updateOcean();
                i[0]++;
            }
        }, 500, 300);
    }
}